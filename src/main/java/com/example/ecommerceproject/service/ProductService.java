package com.example.ecommerceproject.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ecommerceproject.entity.Product;
import com.example.ecommerceproject.exception.InternalServerErrorException;



public interface ProductService extends IService<Product>{
	
	
	public Product addProduct( String productName, String productType, String filePath,
			BigDecimal price, String description) throws InternalServerErrorException;
	
	public List<String> getProductTypes();
	
	public List<Product> getAllProducts();
	
	public void deleteProductbyId(Integer productId);
	
	public void updateProductStatusById(Integer productId);

	public Product getProductById(Integer productId);

	public Product updateProduct(Integer productId, String productName, String productType,
			BigDecimal price, String description, String filePath, String status);
	
//	public byte[] getProductImage(int productId);

	public String getProductFilePath(Integer productId);

}
