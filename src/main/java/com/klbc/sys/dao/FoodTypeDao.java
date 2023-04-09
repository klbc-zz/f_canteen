package com.klbc.sys.dao;

import com.klbc.sys.bean.FoodType;

import java.util.List;

public interface FoodTypeDao {

	List<FoodType> find(String keyword, String disabled);

	FoodType findByFoodName(String foodTypeName);

	void save(FoodType foodType2);

	FoodType findById(int id);

	Object update(FoodType foodType2);

}
