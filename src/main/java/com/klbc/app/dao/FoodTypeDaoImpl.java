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
		//1.�������ݿ�
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql���
			String sql = "select * from food_type where disabled = 0";
			System.out.println(sql);
		
		//3.��װ��
			 preparedStatement = connection.prepareStatement(sql);
			 
			
		//4.ִ�����
			 resultSet = preparedStatement.executeQuery();
			 
			 
			List<FoodType> foodTypes = new ArrayList<>();//����ѯ��������Ϣ���з�װ
			while(resultSet.next()) {//ָ������һλ����Ϊ����ͷ��ϢҲ��ѯ�����ˣ�����ͷ��Ϣ����Ҫ��װ
				FoodType foodType = new FoodType();
				//����������ȡ��ֵ����ʲô���͵ľ�getʲô����
				foodType.setId(resultSet.getInt("id"));
				foodType.setTypeName(resultSet.getString("food_type_name"));
				foodType.setCreationDate(resultSet.getTimestamp("creation_date"));
//				food.setUpdateDate(resultSet.getTimestamp("modify_date"));
				foodType.setModifyDate(resultSet.getTimestamp("modify_date"));
				foodType.setDisabled(resultSet.getInt("disabled"));
				
				foodTypes.add(foodType);//��ӵ�������ȥ
//				System.out.println(foodTypes);
			}
			return foodTypes;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.�ر�
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
		
		
	}

}
