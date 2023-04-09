package com.klbc.app.dao;

import com.klbc.app.pojo.FoodType;
import com.klbc.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodTypeDaoImpl implements FoodTypeDao {

	@Override
	public List<FoodType> findAll() {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql语句
			String sql = "select * from food_type where disabled = 0";
			System.out.println(sql);
		
		//3.集装箱
			 preparedStatement = connection.prepareStatement(sql);
			 
			
		//4.执行语句
			 resultSet = preparedStatement.executeQuery();
			 
			 
			List<FoodType> foodTypes = new ArrayList<>();//将查询出来的信息进行封装
			while(resultSet.next()) {//指针下移一位，因为将表头信息也查询出来了，而表头信息不需要封装
				FoodType foodType = new FoodType();
				//根据列名获取列值，是什么类型的就get什么类型
				foodType.setId(resultSet.getInt("id"));
				foodType.setTypeName(resultSet.getString("food_type_name"));
				foodType.setCreationDate(resultSet.getTimestamp("creation_date"));
//				food.setUpdateDate(resultSet.getTimestamp("modify_date"));
				foodType.setModifyDate(resultSet.getTimestamp("modify_date"));
				foodType.setDisabled(resultSet.getInt("disabled"));
				
				foodTypes.add(foodType);//添加到集合里去
//				System.out.println(foodTypes);
			}
			return foodTypes;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
		
		
	}

}
