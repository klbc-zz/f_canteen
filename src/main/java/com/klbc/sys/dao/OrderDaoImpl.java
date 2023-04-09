package com.klbc.sys.dao;

import com.klbc.sys.bean.Food;
import com.klbc.sys.bean.Order;
import com.klbc.sys.bean.OrderDetail;
import com.klbc.sys.bean.User;
import com.klbc.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

	@Override
	public List<Order> find() {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select f_order.*,user_name from f_order left join user on f_order.user_id=user.id ";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		
		
		resultSet = preparedStatement.executeQuery();
		
		List<Order> orders = new ArrayList<>();
		
		while(resultSet.next()) {
			Order order = new Order();
			order.setDisabled(resultSet.getInt("disabled"));
			order.setId(resultSet.getInt("id"));
			order.setOrderCode(resultSet.getString("order_code"));
			order.setOrderTime(resultSet.getTimestamp("order_time"));
			order.setPayTime(resultSet.getString("pay_time"));
			order.setStatus(resultSet.getInt("status"));
			order.setTotalPrice(resultSet.getDouble("total_price"));
			order.setDisabled(resultSet.getInt("disabled"));
			
			User user = new User();
			user.setUserName(resultSet.getString("user_name"));
			
			order.setUser(user);
			orders.add(order);
	
		}
		return orders;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
	
		return null;
	}

	@Override
	public Order findById(int id) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select * from f_order where id=? order by order_time desc";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		
		
		
		while(resultSet.next()) {
			Order order = new Order();
			order.setDisabled(resultSet.getInt("disabled"));
			order.setId(resultSet.getInt("id"));
			order.setOrderCode(resultSet.getString("order_code"));
			order.setOrderTime(resultSet.getTimestamp("order_time"));
			order.setPayTime(resultSet.getString("pay_time"));
			order.setStatus(resultSet.getInt("status"));
			order.setTotalPrice(resultSet.getDouble("total_price"));
			order.setDisabled(resultSet.getInt("disabled"));

			return order;
		}
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

	@Override
	public void update(Order order) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "update f_order set total_price=?,status=?,update_time=NOW(),disabled=? where id=?";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDouble(1, order.getTotalPrice());
		preparedStatement.setInt(2, order.getStatus());
		preparedStatement.setInt(3, order.getDisabled());
		preparedStatement.setInt(4, order.getId());
		int resultSetInt = preparedStatement.executeUpdate();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		
	}

	@Override
	public List<Order> findMonth() {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select food_name,sum(order_detail.buy_num)count,DATE_FORMAT(pay_time,'%Y-%m')month  from order_detail " +
				"right join food on food.id=order_detail.food_id " + 
				"left join f_order on f_order.id=order_detail.order_id where f_order.status=1" +
				" GROUP BY food_name,month ORDER BY month,count desc";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		
		resultSet = preparedStatement.executeQuery();
		
		
		List<Order> orders = new ArrayList<>();
		while(resultSet.next()) {
			Order order = new Order();
			order.setPayTime(resultSet.getString("month"));
	
//			Food food = new Food(foodName, Integer.parseInt(foodTypeId), Double.parseDouble(price), remark, newName.toString(), 0);
			Food food = new Food();
			food.setFoodName(resultSet.getString("food_name"));
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBuyNum(resultSet.getInt("count"));

			order.setFood(food);
			order.setOrderDetail(orderDetail);
			orders.add(order);
			
		}
		return orders;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

	@Override
	public List<Order> findWeek() {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select food_name,sum(order_detail.buy_num)count,DATE_FORMAT(pay_time,'%Y-%m µÚ%uÖÜ')week  from order_detail " +
				"right join food on food.id=order_detail.food_id " + 
				"left join f_order on f_order.id=order_detail.order_id where f_order.status=1 " +
				"GROUP BY food_name,week ORDER BY week,count desc";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		
		resultSet = preparedStatement.executeQuery();
		
		
		List<Order> orders = new ArrayList<>();
		while(resultSet.next()) {
			Order order = new Order();
			order.setPayTime(resultSet.getString("week"));
	
//			Food food = new Food(foodName, Integer.parseInt(foodTypeId), Double.parseDouble(price), remark, newName.toString(), 0);
			Food food = new Food();
			food.setFoodName(resultSet.getString("food_name"));
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBuyNum(resultSet.getInt("count"));

			order.setFood(food);
			order.setOrderDetail(orderDetail);
			orders.add(order);
			
		}
		return orders;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

	@Override
	public List<Order> findDay() {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select food_name,sum(order_detail.buy_num)count,DATE_FORMAT(pay_time,'%Y-%m-%d')day  from order_detail " +
				"right join food on food.id=order_detail.food_id " + 
				"left join f_order on f_order.id=order_detail.order_id where f_order.status=1 " +
				"GROUP BY food_name,day ORDER BY day,count desc";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		
		resultSet = preparedStatement.executeQuery();
		
		
		List<Order> orders = new ArrayList<>();
		while(resultSet.next()) {
			Order order = new Order();
			order.setPayTime(resultSet.getString("day"));
	
//			Food food = new Food(foodName, Integer.parseInt(foodTypeId), Double.parseDouble(price), remark, newName.toString(), 0);
			Food food = new Food();
			food.setFoodName(resultSet.getString("food_name"));
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBuyNum(resultSet.getInt("count"));

			order.setFood(food);
			order.setOrderDetail(orderDetail);
			orders.add(order);
			
		}
		return orders;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

}
