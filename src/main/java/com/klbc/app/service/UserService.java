package com.klbc.app.service;

import com.klbc.app.pojo.User;

public interface UserService {

	User findByLoginNameAndPass(String name, String password);
//	��ѯ�����Ƿ���������ݿ�
	Boolean selectUseName(String name);
//	ע�Ṧ��
    int addUser(User user);


}
