package com.klbc.app.dao;

import com.klbc.app.pojo.Food;

import java.util.List;

public interface FoodDao {

	List<Food> findByFoodTypeId(Integer foodTypeId);

	Food findByFoodId(Integer foodid);

	int getFoodCount(int foodid);

}
