package com.klbc.sys.service;

import com.klbc.sys.bean.FoodType;

import java.util.List;

public interface FoodTypeService {

	List<FoodType> find(String keyword, String disabled);

	FoodType findByFoodName(String foodTypeName);

	void save(FoodType foodType2);

	FoodType findById(int id);

	void update(FoodType foodType2);

}
