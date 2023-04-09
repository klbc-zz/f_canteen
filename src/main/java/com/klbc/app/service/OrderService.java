package com.klbc.app.service;

import com.klbc.app.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

	void order(Map<Integer, Integer> shopCar, String total, Integer userId);

	List<Order> findDetails(Integer userId);

//Í¨¹ý¶©µ¥id
	Order findById(int parseInt);

	void pay(Order order);

	void deleteOrder(Order order);





	
	

}
