package com.example.ecommerceproject.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("orderitem")
@Data
public class OrderItem {
	@TableId(type = IdType.AUTO)
	@TableField("item_id")
	private Integer itemId;
	@TableField("order_id")
	private Integer orderId;
	@TableField("product_id")
	private Integer productId;
	private int quantity;
	@TableField("product_price")
	private BigDecimal productPrice;
	@TableField(exist = false)
	private String productName;
	
	

}
