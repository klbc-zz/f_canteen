package com.klbc.app.dao;

import com.klbc.app.pojo.Order;
import com.klbc.app.pojo.OrderDetail;

import java.util.List;
import java.util.Map;

public interface OrderDao {

	void order(Order order, Map<Integer, Integer> shopCar, Integer userId);

	List<Order> findDetails(Integer orderId);

	List<OrderDetail> findByOrderId(Integer OrderId);

	Order findById(int id);

	void pay(Order order);

	void deleteOrder(Order order);



}
