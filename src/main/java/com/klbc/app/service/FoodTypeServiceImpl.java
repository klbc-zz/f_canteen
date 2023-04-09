package com.klbc.app.service;

import com.klbc.app.pojo.FoodType;
import com.klbc.app.dao.FoodTypeDaoImpl;

import java.util.List;

public class FoodTypeServiceImpl implements FoodTypeService {
	
	//service层调用dao层
	private FoodTypeDaoImpl foodTypeDao = new FoodTypeDaoImpl();
	@Override
	public List<FoodType> findAll() {
		// TODO 自动生成的方法存根
		return foodTypeDao.findAll();
	}

}
