package com.klbc.sys.dao;

import com.klbc.sys.bean.FoodType;
import com.klbc.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodTypeDaoImpl implements FoodTypeDao {

	@Override
	public List<FoodType> find(String keyword, String disabled) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
//		String sql = "select * from foodtype where foodtype_name like '%"+keyword+"%' and disabled=?";
		StringBuffer sql  = new StringBuffer("select * from food_type where 1=1");
		if(keyword!=null && !keyword.equals("")) {
			sql.append(" and food_type_name like '%" +keyword+ "%'");
			
		}
		if(disabled!=null && !disabled.equals("")) {
			sql.append(" and disabled="+ disabled);
		}
		
		System.out.println(sql);
		preparedStatement = connection.prepareStatement(sql.toString());

		resultSet = preparedStatement.executeQuery();
		
		List<FoodType> foodtypes = new ArrayList<>();
		while(resultSet.next()) {
			FoodType foodtype = new FoodType();
			foodtype.setId(resultSet.getInt("id"));
			foodtype.setTypeName(resultSet.getString("food_type_name"));
			foodtype.setCreationDate(resultSet.getTimestamp("creation_date"));
			foodtype.setModifyDate(resultSet.getTimestamp("modify_date"));
			foodtype.setDisabled(resultSet.getInt("disabled"));
			
			foodtypes.add(foodtype);
		}
		
		return foodtypes;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}

		return null;
	}

	@Override
	public FoodType findByFoodName(String foodTypeName) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select * from food_type where food_type_name=?";
		
		System.out.println(sql);
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, foodTypeName);
		resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			FoodType foodtype = new FoodType();
			foodtype.setId(resultSet.getInt("id"));
			foodtype.setTypeName(resultSet.getString("food_type_name"));
			foodtype.setCreationDate(resultSet.getTimestamp("creation_date"));
			foodtype.setModifyDate(resultSet.getTimestamp("modify_date"));
			foodtype.setDisabled(resultSet.getInt("disabled"));
			
			
			return foodtype;
		}
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

	
	@Override
	public void save(FoodType foodType2) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "insert into food_type(food_type_name,creation_date,disabled) VALUES(?,NOW(),0)";
		
		System.out.println(sql);
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, foodType2.getTypeName());
		
		int resultSetInt = preparedStatement.executeUpdate();
		
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		
	}

	@Override
	public FoodType findById(int id) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select * from food_type where id=?";
		
		System.out.println(sql);
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			FoodType foodtype = new FoodType();
			foodtype.setId(resultSet.getInt("id"));
			foodtype.setTypeName(resultSet.getString("food_type_name"));
			foodtype.setCreationDate(resultSet.getTimestamp("creation_date"));
			foodtype.setModifyDate(resultSet.getTimestamp("modify_date"));
			foodtype.setDisabled(resultSet.getInt("disabled"));
			
			
			return foodtype;
		}
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

	@Override
	public Object update(FoodType foodType2) {
			Connection connection =null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet =null;
		try {
			connection = JDBCUtil.getConnection();
			
			String sql = "update food_type set food_type_name = ? , modify_date=NOW() , disabled=? where id=?";
			
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, foodType2.getTypeName());
			preparedStatement.setInt(2, foodType2.getDisabled());
			preparedStatement.setInt(3, foodType2.getId());
			
			int resultSetInt = preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}

}
