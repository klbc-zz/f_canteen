package com.klbc.app.service;

import com.klbc.app.dao.Basedao;
import com.klbc.app.pojo.User;
import com.klbc.app.dao.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
	UserDaoImpl userDao = new UserDaoImpl();
	public User findByLoginNameAndPass(String name, String password) {
		// TODO 自动生成的方法存根
		return userDao.findByLoginNameAndPass(name,password);
	}

	@Override
	public Boolean selectUseName(String name) {
		Connection connection = null;
		Boolean rs  = false;
		try {
			connection = Basedao.getConnection();
			//业务层调用dao层，具体数据库操作
			rs = userDao.selectUseName(connection,name);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			Basedao.closeResource(null, connection,null);
		}
		return rs;
	}

	@Override
	public int addUser(User user) {
		Connection connection = null;
		int rs  = 0;
		try {
			connection = Basedao.getConnection();
			//业务层调用dao层，具体数据库操作
			rs = userDao.addUser(connection,user);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			Basedao.closeResource(null, connection,null);
		}
		return rs;
	}

}
