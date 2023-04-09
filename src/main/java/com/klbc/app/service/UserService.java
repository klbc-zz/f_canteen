package com.klbc.app.service;

import com.klbc.app.pojo.User;

public interface UserService {

	User findByLoginNameAndPass(String name, String password);
//	查询名字是否存在于数据库
	Boolean selectUseName(String name);
//	注册功能
    int addUser(User user);


}
