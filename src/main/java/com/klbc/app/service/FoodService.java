package com.klbc.app.service;

import com.klbc.app.pojo.Food;

import java.util.List;

public interface FoodService {

	List<Food> findByFoodTypeId(Integer foodTypeId);

	Food findByFoodId(Integer foodid);

	int getFoodCount(int parseInt);

}
