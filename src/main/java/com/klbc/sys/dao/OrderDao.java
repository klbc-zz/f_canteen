package com.klbc.sys.dao;

import com.klbc.sys.bean.Order;

import java.util.List;

public interface OrderDao {

	List<Order> find();

	Order findById(int id);

	void update(Order order);

	List<Order> findMonth();

	List<Order> findWeek();

	List<Order> findDay();

}
