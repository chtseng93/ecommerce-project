package com.example.ecommerceproject.service;

import java.util.List;



import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ecommerceproject.entity.Order;
import com.example.ecommerceproject.entity.OrderVo;
import com.example.ecommerceproject.model.req.OrderReq;

public interface OrderService extends IService<Order>{

	public OrderVo getOrderByOrderId(Integer orderId);

	public List<Order> getAllOrders();
	
	
	public List<OrderVo> getAllOrdersWithItems();

	public Integer saveOrder(OrderReq orderReq);

	public List<OrderVo> getAllOrdersByUserId(int userId);

	public void updateOrderStatusById(Integer orderId, String orderStatus);

}
