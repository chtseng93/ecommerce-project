package com.example.ecommerceproject.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
@Data
public class OrderVo extends Order{
	
	@TableField(exist = false)
	private Collection<OrderItem> orderItems = new HashSet<>();

	
	
	

}
