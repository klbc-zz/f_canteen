package com.klbc.app.service;

import com.klbc.app.pojo.FoodType;
import com.klbc.app.dao.FoodTypeDaoImpl;

import java.util.List;

public class FoodTypeServiceImpl implements FoodTypeService {
	
	//service�����dao��
	private FoodTypeDaoImpl foodTypeDao = new FoodTypeDaoImpl();
	@Override
	public List<FoodType> findAll() {
		// TODO �Զ����ɵķ������
		return foodTypeDao.findAll();
	}

}
