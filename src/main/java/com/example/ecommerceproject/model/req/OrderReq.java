package com.example.ecommerceproject.model.req;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

import com.example.ecommerceproject.entity.OrderItem;

import lombok.Data;

@Data
public class OrderReq {
	
	private Integer userId;
	private String address;
	private BigDecimal totalPrice;
	private Collection<OrderItem> orderItems = new HashSet<>();
	private String status;
	

}
