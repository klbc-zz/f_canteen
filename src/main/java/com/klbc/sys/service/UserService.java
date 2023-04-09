package com.klbc.sys.service;

import com.klbc.sys.bean.User;

import java.util.List;

public interface UserService {
	//
	User findByLoginNameAndPass(String name, String password);
	User selectUserById(Integer id);
	//��ѯ�û��б�
	List<User> selectUserlist(String userName, int userRole, int currentPageNo, int pageSize);
	//����Ա������
	int addUser(User user);
	//ɾ��Ա��
	int delUser(Integer id);
	//�޸�Ա����Ϣ
	int updateUser(User user);
	int getUserByName(String userName);
}
