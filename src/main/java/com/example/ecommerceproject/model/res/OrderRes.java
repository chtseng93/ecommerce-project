package com.example.ecommerceproject.model.res;

import java.math.BigDecimal;
import java.util.Collection;

import java.util.HashSet;


import com.example.ecommerceproject.entity.OrderItem;


import lombok.Data;
@Data
public class OrderRes {

	private int orderId;
	private int userId;
	private String orderDate;
	private String address;
	private String status;
	private BigDecimal totalPrice;
	private Collection<OrderItem> orderItems = new HashSet<>();
	
	
	public OrderRes(int orderId, int userId, String orderDate, String address, String status, BigDecimal totalPrice,
			Collection<OrderItem> orderItems) {
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.address = address;
		this.status = status;
		this.totalPrice = totalPrice;
		this.orderItems = orderItems;
	} 
	
	
	
	
	
	
	

}
