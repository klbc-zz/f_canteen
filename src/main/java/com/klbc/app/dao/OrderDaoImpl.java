package com.klbc.app.dao;

import com.klbc.app.pojo.Food;
import com.klbc.app.pojo.Order;
import com.klbc.app.pojo.OrderDetail;
import com.klbc.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void order(Order order, Map<Integer, Integer> shopCar,Integer userId) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
			
			//要让下面同时提交,手动提交事务
			connection.setAutoCommit(false);
			
		//2.sql语句
			String sql = "INSERT INTO f_order (order_code, total_price,order_time,user_id,status,disabled ) VALUES ( ?, ?,NOW(),? ,0,0)";
			System.out.println(sql);
		
		//3.集装箱   Statement.RETURN_GENERATED_KEYS获取主键id
			 preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			 //索引从1开始,是什么类型的就set什么类型
			 preparedStatement.setString(1, order.getOrderCode());
			 preparedStatement.setDouble(2,order.getTotalPrice() );
			 preparedStatement.setInt(3, userId);
			 
			 
			
		//4.执行语句,返回影响函数
			 int result = preparedStatement.executeUpdate();
			 
			 resultSet = preparedStatement.getGeneratedKeys();
			 
			 //指针下移一位，因为将表头信息也查出来了
			 resultSet.next();
			 Integer orderId = resultSet.getInt(1);
			 System.out.println("orderId:"+orderId);
			 
			 
			 
			 String sqlItem = "insert into order_detail (order_id,food_id,buy_num) values (?,?,?)";
			 preparedStatement = connection.prepareStatement(sqlItem);
			 
			 //保存订单明细,获取购物车中所有菜品得id
			  Set<Integer> foodIds =shopCar.keySet();
			  for(Integer foodId :foodIds) {
				  Integer buyNum = shopCar.get(foodId);
				 preparedStatement.setInt(1, orderId);
				 preparedStatement.setInt(2, foodId);
				 preparedStatement.setInt(3, buyNum);
				 
				 preparedStatement.addBatch();
				 
				  
//				  preparedStatement = connection.prepareStatement(sqlItem);
//				  preparedStatement.executeUpdate();
				  
			  }
			  //执行批处理
			  preparedStatement.executeBatch();
			  connection.commit();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		
	}

	@Override
	public List<Order> findDetails(Integer userId) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
			
		//2.sql语句
			String sql = "select * from f_order  where  user_id =? order by order_time desc";
			System.out.println(sql);
		//	select * from f_order  where disabled = 0 and status = 0 and userId =?
		
		//3.集装箱   Statement.RETURN_GENERATED_KEYS获取主键id
			 preparedStatement = connection.prepareStatement(sql);
			 //索引从1开始,是什么类型的就set什么类型
			preparedStatement.setInt(1, userId);
		//4.执行语句,返回影响函数
			 resultSet = preparedStatement.executeQuery();
			 List<Order> orders = new ArrayList<>();
			 //指针下移一位，因为将表头信息也查出来了
			 while(resultSet.next()) {
				 Order order = new Order();
				 order.setDisabled(resultSet.getInt("disabled"));
				 order.setId(resultSet.getInt("id"));
				 order.setOrderCode(resultSet.getString("order_code"));
				 order.setOrderTime(resultSet.getTimestamp("order_time"));
				 order.setPayTime(resultSet.getTimestamp("pay_time"));
				 order.setStatus(resultSet.getInt("status"));
				 order.setTotalPrice(resultSet.getDouble("total_price"));
				 order.setUpdateTime(resultSet.getTimestamp("update_time"));
				 order.setUserId(resultSet.getInt("user_id"));
				 orders.add(order);
			 }
			 return orders;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}

	@Override
	public List<OrderDetail> findByOrderId(Integer OrderId) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
			
		//2.sql语句
			String sql = "select order_detail.id detailId ,order_detail.*,food.* from order_detail " + 
					"	left join food on food.id=order_detail.food_id	" + 
					"	where order_detail.order_id = ? ";
			System.out.println(sql);
		
		//3.集装箱   Statement.RETURN_GENERATED_KEYS获取主键id
			 preparedStatement = connection.prepareStatement(sql);
			 //索引从1开始,是什么类型的就set什么类型
			 preparedStatement.setInt(1, OrderId);
			
		//4.执行语句,返回影响函数
			 resultSet = preparedStatement.executeQuery();
			 List<OrderDetail> orderDetails = new ArrayList<>();
			 //指针下移一位，因为将表头信息也查出来了
			 while(resultSet.next()) {
				 OrderDetail orderDetail = new OrderDetail();
				 orderDetail.setOrderId(resultSet.getInt("order_id"));
				 orderDetail.setBuyNum(resultSet.getInt("buy_num"));
				 orderDetail.setFoodId(resultSet.getInt("food_id"));
				 
				 Food food = new Food();
				 food.setFoodTypeId(resultSet.getInt("food_type_id"));
				 food.setFoodName(resultSet.getString("food_name"));
				 food.setImg(resultSet.getString("img"));
				 food.setPrice(resultSet.getDouble("price"));
				 food.setRemark(resultSet.getNString("remark"));
				 
				 orderDetail.setFood(food);
				 orderDetails.add(orderDetail);
			 }
			 return orderDetails;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}

	@Override
	public Order findById(int id) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
			
		//2.sql语句
			String sql = "select * from f_order where id=?";
			System.out.println(sql);
		
		//3.集装箱   Statement.RETURN_GENERATED_KEYS获取主键id
			 preparedStatement = connection.prepareStatement(sql);
			 //索引从1开始,是什么类型的就set什么类型
			preparedStatement.setInt(1, id);
			 
			 
		//4.执行语句,返回影响函数
			 resultSet = preparedStatement.executeQuery();
//			 List<Order> orders = new ArrayList<>();
			 //指针下移一位，因为将表头信息也查出来了
			 while(resultSet.next()) {
				 Order order = new Order();
				 order.setDisabled(resultSet.getInt("disabled"));
				 order.setId(resultSet.getInt("id"));
				 order.setUserId(resultSet.getInt("user_id"));
				 order.setOrderCode(resultSet.getString("order_code"));
				 order.setOrderTime(resultSet.getTimestamp("order_time"));
				 order.setPayTime(resultSet.getTimestamp("pay_time"));
				 order.setStatus(resultSet.getInt("status"));
				 order.setTotalPrice(resultSet.getDouble("total_price"));
				 order.setUpdateTime(resultSet.getTimestamp("update_time"));
//				 orders.add(order);
				 return order;
			 }
//			 return orders;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		
		return null;
	}

	@Override
	public void pay(Order order) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
			
		//2.sql语句
			String sql = "update f_order set status=?,order_code=?,order_time=?,pay_time=?,total_price=?,update_time=?,disabled=? where id=?";
			System.out.println(sql);
			
			
			Date payDate = order.getPayTime() != null?new Date(order.getPayTime().getTime()):null;
			Date updateDate = order.getUpdateTime() != null ?new Date(order.getUpdateTime().getTime()):null;
		//3.集装箱   Statement.RETURN_GENERATED_KEYS获取主键id
			 preparedStatement = connection.prepareStatement(sql);
			 //索引从1开始,是什么类型的就set什么类型
			preparedStatement.setInt(1, order.getStatus());
			preparedStatement.setString(2, order.getOrderCode());
			preparedStatement.setDate(3, new Date(order.getOrderTime().getTime()));
			preparedStatement.setDate(4, payDate);
			preparedStatement.setDouble(5, order.getTotalPrice());
			preparedStatement.setDate(6, updateDate);
			preparedStatement.setInt(7, order.getDisabled());
			preparedStatement.setInt(8, order.getId());
			 
			 
		//4.执行语句,返回影响函数
			 int result = preparedStatement.executeUpdate();
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		
	}

	@Override
	public void deleteOrder(Order order) {
		// TODO 自动生成的方法存根
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
			
		//2.sql语句
			String sql = "update f_order set disabled=?,status=? where id=?";
			System.out.println(sql);
			
			
			Date payDate = order.getPayTime() != null?new Date(order.getPayTime().getTime()):null;
			Date updateDate = order.getUpdateTime() != null ?new Date(order.getUpdateTime().getTime()):null;
		//3.集装箱   Statement.RETURN_GENERATED_KEYS获取主键id
			 preparedStatement = connection.prepareStatement(sql);
			 //索引从1开始,是什么类型的就set什么类型
			
			preparedStatement.setInt(1, order.getDisabled());
			preparedStatement.setInt(2, order.getStatus());
			preparedStatement.setInt(3, order.getId());
			 
			 
		//4.执行语句,返回影响函数
			 int result = preparedStatement.executeUpdate();
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
	}

}
