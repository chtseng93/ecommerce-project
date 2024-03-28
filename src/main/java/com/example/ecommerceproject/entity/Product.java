package com.example.ecommerceproject.entity;

import java.math.BigDecimal;
import java.sql.Blob;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("product")
@Data
public class Product {
	@TableId(type = IdType.AUTO)
	@TableField("product_id")
	private Integer productId;
	@TableField("name")
	private String productName;
	@TableField("price")
    private BigDecimal productPrice;
//    private byte[] picture;
    @TableField("product_type")
	private String productType;
    private String description;
    @TableField("file_path")
    private String filePath;
    private String status;
   

}
