package com.klbc.sys.service;

import com.klbc.sys.bean.Order;

import java.util.List;

public interface OrderService {

	List<Order> find();

	Order findById(int parseInt);

	void update(Order order);

	List<Order> findMonth();

	List<Order> findWeek();

	List<Order> findDay();

}
