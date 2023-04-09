package com.klbc.sys.dao;

import com.klbc.sys.bean.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

	User findByLoginNameAndPass(String name, String password);

	//得到登录的用户
	public User getLoginUser(Connection connection, String userCode) throws SQLException;

	//修改密码
	public int updatePwd(Connection connection, int id, String pwd) throws SQLException;

	//根据用户名或者角色查询用户数
	public int getUserCount(Connection connection, String userName, int userRole) throws SQLException;
 
	//通过条件查询-userList
	public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws SQLException;

	//添加用户信息
	public int addUser(Connection connection, User user) throws SQLException;

	//通过userID删除对象
	public int deleteUserById(Connection connection, Integer delId)throws Exception;

	//修改用户名信息
	public int modify(Connection connection, User user) throws SQLException;

	//通过userId获取user
	public User getUserById(Connection connection, String id) throws Exception;
	//根据名字判断是否存在这个用户
	int getUserByName(Connection connection, String userName)throws Exception;

	

}
