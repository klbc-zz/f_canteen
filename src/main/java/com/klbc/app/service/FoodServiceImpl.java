package com.klbc.app.service;

import com.klbc.app.pojo.Food;
import com.klbc.app.dao.FoodDaoImpl;

import java.util.List;

public class FoodServiceImpl implements FoodService {
	
	private FoodDaoImpl FoodDao = new FoodDaoImpl();
	@Override
	public List<Food> findByFoodTypeId(Integer foodTypeId) {
		// TODO �Զ����ɵķ������
		return FoodDao.findByFoodTypeId(foodTypeId);
	}
	@Override
	public Food findByFoodId(Integer foodid) {
		// TODO �Զ����ɵķ������
		return FoodDao.findByFoodId(foodid);
	}
	@Override
	public int getFoodCount(int foodid) {
		// TODO �Զ����ɵķ������
		return FoodDao.getFoodCount(foodid);
	}

}
