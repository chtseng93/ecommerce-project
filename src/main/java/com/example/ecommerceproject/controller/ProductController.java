package com.example.ecommerceproject.controller;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.ecommerceproject.entity.Product;
import com.example.ecommerceproject.exception.ImageRetrievalException;

import com.example.ecommerceproject.exception.ResourceNotFoundException;
import com.example.ecommerceproject.model.res.ProductRes;
import com.example.ecommerceproject.service.ProductService;



import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController{
	@Autowired
	private ProductService productService;
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${product.file.path}")
	private String filePath;
	
	@Value("${product.image.file.path}")
	private String clientFilePath;

	@PostMapping("/add/Product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductRes> addNewProduct(
    		@RequestParam("name") String name,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("type") String productType,
            @RequestParam("price") String productPrice,
            @RequestParam("productDesc") String productDesc,
            @RequestParam("imageName") String imageName
    		) throws IOException {
		log.info("/add/Product");
		BigDecimal price = new BigDecimal(productPrice); 
		String fullPathName = !(StringUtils.isBlank(imageName)) ? filePath + imageName : null;
		this.saveFileToServer(picture.getBytes(), fullPathName, null);
		Product newProduct = productService.addProduct(name, productType, fullPathName, price, productDesc);
        ProductRes response = new ProductRes(newProduct.getProductId(),newProduct.getProductName(), newProduct.getProductPrice(), newProduct.getProductType(),newProduct.getDescription(),newProduct.getStatus());
        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/get/productTypes")
	public ResponseEntity<List<String>> getAllProductTypes(){
		return ResponseEntity.ok(productService.getProductTypes());
		
	}
	
	
    @GetMapping("/get/allProducts")
    public ResponseEntity<List<ProductRes>> getAllProducts() throws SQLException {
    	log.info("/get/allProducts");
        List<Product> products = productService.getAllProducts();
        List<ProductRes> productRess = new ArrayList<>();
        for (Product product : products) {
        	String base64Photo = this.getBase64FileFromServer(product.getFilePath());
            ProductRes productRes =
            		new ProductRes(product.getProductId(), product.getProductName(), product.getProductPrice(),base64Photo,product.getProductType(),product.getDescription(),product.getFilePath().replace(filePath, clientFilePath),product.getStatus());
            productRess.add(productRes);
            
        }
        
        return ResponseEntity.ok(productRess);
    }
    
    
	@GetMapping("/get/product/{productId}")
	public ResponseEntity<Optional<ProductRes>> getProductById(@PathVariable int productId) {
		return Optional.of(productService.getProductById(productId)).map(product -> {
			String base64Photo = this.getBase64FileFromServer(product.getFilePath());
			ProductRes productRes = new ProductRes(product.getProductId(), product.getProductName(), product.getProductPrice(),base64Photo, product.getProductType(),product.getDescription(),product.getFilePath().replace(filePath, clientFilePath),product.getStatus());
			return ResponseEntity.ok(Optional.of(productRes));
		}).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}



	  @PutMapping("/update/{productId}")
	  @PreAuthorize("hasRole('ADMIN')")
	  public ResponseEntity<ProductRes> updateProduct(@PathVariable int productId,
  		  @RequestParam(required = false) String productName,
          @RequestParam(required = false) MultipartFile picture,
          @RequestParam(required = false) String productType,
          @RequestParam(required = false) String productPrice,
          @RequestParam(required = false) String description,
          @RequestParam(required = false) String status,
          @RequestParam(required = false) String imageName) throws SQLException, IOException {
		  log.info("/update/"+productId);
		  log.info("productName:"+productName);
		  log.info("picture:"+picture);
		  log.info("productType:"+productType);
		  log.info("productPrice:"+productPrice);
		  log.info("description:"+description);
		  log.info("imageName:"+imageName);
		BigDecimal price = productPrice != null ? new BigDecimal(productPrice) : null;
		String preFilePath = productService.getProductFilePath(productId);
		String fullPathName = "";
		// need to update picture
		if (picture != null && !picture.isEmpty() && !StringUtils.isBlank(imageName)) {
			fullPathName = filePath + imageName;
			this.saveFileToServer(picture.getBytes(), fullPathName, preFilePath);
		}else {
			fullPathName = preFilePath;
		}
		log.info("updateProduct-fullPathName"+fullPathName);
		Product updatedProduct = productService.updateProduct(productId, productName, productType, price,
				description, fullPathName,status);

		ProductRes response = new ProductRes(updatedProduct.getProductId(), updatedProduct.getProductName(),
				updatedProduct.getProductPrice(), this.getBase64FileFromServer(updatedProduct.getFilePath()),
				updatedProduct.getProductType(), updatedProduct.getDescription(),updatedProduct.getFilePath().replace(filePath, clientFilePath),updatedProduct.getStatus());
		log.info("updateProduct-response:" + response);
		return ResponseEntity.ok(response);
	  }

	public void saveFileToServer(byte[] picturebytes, String fullFilePath, String preFilePath) throws IOException {
		
		//the product already has an image so need to delete it before update
		if(!StringUtils.isBlank(preFilePath)) {
			File ogFile = new File(preFilePath);
			if (ogFile != null) ogFile.delete();
		}
		
		FileOutputStream fout = new FileOutputStream(fullFilePath);
		fout.write(picturebytes);
		fout.flush();
		fout.close();

	}
	
	public String getBase64FileFromServer(String filePath){
		
		byte[] fileBytes;
		String base64Photo ="";
		try {
			if (!StringUtils.isBlank(filePath)) {
				fileBytes = Files.readAllBytes(Paths.get(filePath));
				if (fileBytes != null && fileBytes.length > 0) {
					base64Photo = Base64.encodeBase64String(fileBytes);
				}
			}
		} catch (IOException e) {
			throw new ImageRetrievalException(e.getMessage());
		}
		
	    return base64Photo; 
				
	}


}
