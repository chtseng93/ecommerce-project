package com.example.ecommerceproject.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("user_order")
@Data
public class Order {
	@TableId(type = IdType.AUTO)
	@TableField("order_id")
	private Integer orderId;
	@TableField("user_id")
	private Integer userId;
	private Date orderDate;
	private String address;
	@TableField("total_price")
    private BigDecimal totalPrice;
	private String status;
	
	
	
	
}
