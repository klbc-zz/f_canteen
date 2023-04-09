package com.klbc.sys.service;

import com.klbc.app.dao.Basedao;
import com.klbc.sys.bean.User;
import com.klbc.sys.dao.UserDao;
import com.klbc.sys.dao.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
	
	UserDao userDao = new UserDaoImpl();
	@Override
	public User findByLoginNameAndPass(String name, String password) {
		
		return userDao.findByLoginNameAndPass(name,password);
	}

	@Override
	public User selectUserById(Integer id) {
		Connection connection = null;
		User rs  = null;
		try {
			connection = Basedao.getConnection();
			//业务层调用dao层，具体数据库操作
			rs = userDao.getUserById(connection, String.valueOf(id));
		}  catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Basedao.closeResource(null, connection,null);
		}
		return rs;
	}

	@Override
	public List<User> selectUserlist(String userName, int userRole, int currentPageNo, int pageSize) {
		Connection connection = null;
		List<User> rs  = new ArrayList<>();
		try {
			connection = Basedao.getConnection();
			//业务层调用dao层，具体数据库操作
			rs = userDao.getUserList(connection,userName,userRole,currentPageNo,pageSize);
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

	@Override
	public int delUser(Integer id) {
		Connection connection = null;
		int rs  = 0;
		try {
			connection = Basedao.getConnection();
			//业务层调用dao层，具体数据库操作
			rs = userDao.deleteUserById(connection,id);
		}  catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Basedao.closeResource(null, connection,null);
		}
		return rs;
	}

	@Override
	public int updateUser(User user) {
		Connection connection = null;
		int rs  = 0;
		try {
			connection = Basedao.getConnection();
			//业务层调用dao层，具体数据库操作
			user.setModifyDate(new Date());
			rs = userDao.modify(connection,user);
		}  catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Basedao.closeResource(null, connection,null);
		}
		return rs;
	}

	@Override
	public int getUserByName(String userName) {
		Connection connection = null;
		int rs  = 0;
		try {
			connection = Basedao.getConnection();
			//业务层调用dao层，具体数据库操作
			rs = userDao.getUserByName(connection, userName);
		}  catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Basedao.closeResource(null, connection,null);
		}
		return rs;
	}

}
