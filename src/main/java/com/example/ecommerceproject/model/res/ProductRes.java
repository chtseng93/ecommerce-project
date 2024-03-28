package com.example.ecommerceproject.model.res;

import java.math.BigDecimal;


import lombok.Data;
@Data
public class ProductRes {
	
	private int productId;
	private String productName;
    private BigDecimal productPrice;
    private String picture;
	private String productType;
	private String description;
	private String filePath;
	private String status;
	
	
	public ProductRes(int productId, String productName, BigDecimal productPrice, String picture, String productType,
			String description, String filePath, String status) {
		
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.picture = picture;
		this.productType = productType;
		this.description = description;
		this.filePath = filePath;
		this.status = status;
	}


	public ProductRes(int productId, String productName, BigDecimal productPrice, String productType,
			String description,String status) {
		
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productType = productType;
		this.description = description;
		this.status = status;
	}
	
	
	

	
	
	
	

	

}
