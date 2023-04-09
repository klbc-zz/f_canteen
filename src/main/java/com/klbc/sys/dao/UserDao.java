package com.klbc.sys.dao;

import com.klbc.sys.bean.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

	User findByLoginNameAndPass(String name, String password);

	//�õ���¼���û�
	public User getLoginUser(Connection connection, String userCode) throws SQLException;

	//�޸�����
	public int updatePwd(Connection connection, int id, String pwd) throws SQLException;

	//�����û������߽�ɫ��ѯ�û���
	public int getUserCount(Connection connection, String userName, int userRole) throws SQLException;
 
	//ͨ��������ѯ-userList
	public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws SQLException;

	//����û���Ϣ
	public int addUser(Connection connection, User user) throws SQLException;

	//ͨ��userIDɾ������
	public int deleteUserById(Connection connection, Integer delId)throws Exception;

	//�޸��û�����Ϣ
	public int modify(Connection connection, User user) throws SQLException;

	//ͨ��userId��ȡuser
	public User getUserById(Connection connection, String id) throws Exception;
	//���������ж��Ƿ��������û�
	int getUserByName(Connection connection, String userName)throws Exception;

	

}
