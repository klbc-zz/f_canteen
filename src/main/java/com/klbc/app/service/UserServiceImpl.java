package com.klbc.app.service;

import com.klbc.app.dao.Basedao;
import com.klbc.app.pojo.User;
import com.klbc.app.dao.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
	UserDaoImpl userDao = new UserDaoImpl();
	public User findByLoginNameAndPass(String name, String password) {
		// TODO �Զ����ɵķ������
		return userDao.findByLoginNameAndPass(name,password);
	}

	@Override
	public Boolean selectUseName(String name) {
		Connection connection = null;
		Boolean rs  = false;
		try {
			connection = Basedao.getConnection();
			//ҵ������dao�㣬�������ݿ����
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
			//ҵ������dao�㣬�������ݿ����
			rs = userDao.addUser(connection,user);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			Basedao.closeResource(null, connection,null);
		}
		return rs;
	}

}
