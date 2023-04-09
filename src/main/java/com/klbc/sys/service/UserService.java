package com.klbc.sys.service;

import com.klbc.sys.bean.User;

import java.util.List;

public interface UserService {
	//
	User findByLoginNameAndPass(String name, String password);
	User selectUserById(Integer id);
	//查询用户列表
	List<User> selectUserlist(String userName, int userRole, int currentPageNo, int pageSize);
	//增加员工服务
	int addUser(User user);
	//删除员工
	int delUser(Integer id);
	//修改员工信息
	int updateUser(User user);
	int getUserByName(String userName);
}
