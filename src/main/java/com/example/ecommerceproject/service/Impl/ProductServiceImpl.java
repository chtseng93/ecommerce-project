package com.example.ecommerceproject.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.ecommerceproject.entity.Product;
import com.example.ecommerceproject.exception.InternalServerErrorException;
import com.example.ecommerceproject.exception.ResourceNotFoundException;
import com.example.ecommerceproject.mapper.primary.ProductMapper;
import com.example.ecommerceproject.service.ProductService;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
	
	@Autowired
    ProductMapper productMapper;


	@Override
	public Product addProduct( String productName, String productType,  String fileName,BigDecimal price, String description) throws InternalServerErrorException {
		Product product = new Product();
		product.setProductName(productName);
		product.setProductType(productType);
		product.setProductPrice(price);
		product.setDescription(description);
		product.setFilePath(fileName);

		productMapper.insert(product);
		Integer productId = product.getProductId();
		return productMapper.selectById(productId);
	}


	@Override
	public List<String> getProductTypes() {
		return productMapper.getProductTypes();
		
	}
	
	@Override
	public List<Product> getAllProducts(){
		return  productMapper.selectList(null);
	}
	
	public void deleteProductbyId(Integer productId) {
		QueryWrapper<Product> queryWrapper = new QueryWrapper<Product>();
		queryWrapper.eq("product_id", productId);
		productMapper.delete(queryWrapper);
	}


	@Override
	public Product getProductById(Integer productId) {
		return productMapper.selectById(productId);
	}


	@Override
	public Product updateProduct(Integer productId, String productName, String productType, 
		BigDecimal price, String description, String filePath, String status) {
		Product product = new Product();
		product.setProductName(productName);
		product.setProductType(productType);
		product.setProductPrice(price);
		product.setDescription(description);
		product.setFilePath(filePath);
		product.setStatus(status);
		QueryWrapper<Product> updateWrapper = new QueryWrapper<Product>();
		updateWrapper.eq("product_id", productId);
		productMapper.update(product,updateWrapper);
		return productMapper.selectById(productId);
	}


	
	@Override
	public String getProductFilePath(Integer productId) {
		Product product = productMapper.selectById(productId);
	
		if(product!=null) {
			return product.getFilePath();
		}
		return null;
	}


	@Override
	public void updateProductStatusById(Integer productId) {
		productMapper.updateProductStatusById(productId);
		
	}
	
	

}
