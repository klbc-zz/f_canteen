package com.klbc.sys.service;

import com.klbc.sys.bean.Food;
import com.klbc.sys.dao.FoodDao;
import com.klbc.sys.dao.FoodDaoImpl;

import java.util.List;

public class FoodServiceImpl implements FoodService {
	
	FoodDao  foodDao = new FoodDaoImpl();
	
	
	@Override
	public List<Food> find(String keyword, String foodTypeId) {
		
		return foodDao.find(keyword,foodTypeId);
	}


	@Override
	public Food findById(int id) {
		// TODO 自动生成的方法存根
		return foodDao.findById(id);
	}


	@Override
	public void update(Food food) {
		foodDao.update(food);
		
	}


	@Override
	public Food findByFoodName(String foodName) {
		// TODO 自动生成的方法存根
		return foodDao.findByFoodName(foodName);
	}


	@Override
	public void save(Food food) {
		// TODO 自动生成的方法存根
		foodDao.save(food);
	}

}
