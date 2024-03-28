package com.example.ecommerceproject.model.req;




import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class ProductReq {
	
	private int productId;
	private String productName;
    private String productPrice;
    private MultipartFile picture;
	private String productType;
	private String description;
}
