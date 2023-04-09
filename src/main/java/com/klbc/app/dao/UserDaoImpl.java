package com.klbc.app.dao;

import com.klbc.util.JDBCUtil;
import com.mysql.jdbc.StringUtils;
import com.klbc.app.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//dao层：得到用户登录
public class UserDaoImpl implements UserDao{

    @Override
    public User findByLoginNameAndPass(String name, String password) {
        Connection connection =null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet =null;
        //1.连接数据库
        try {
            connection = JDBCUtil.getConnection();

            //2.sql语句
            String sql = "select * from user where user_name=? and user_password = ? ";
            System.out.println(sql);

            //3.集装箱   Statement.RETURN_GENERATED_KEYS获取主键id
            preparedStatement = connection.prepareStatement(sql);
            //索引从1开始,是什么类型的就set什么类型
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            //4.执行语句,返回影响函数
            resultSet = preparedStatement.executeQuery();
//			 List<User> users = new ArrayList<>();
            //指针下移一位，因为将表头信息也查出来了
            while(resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
//                user.setName(resultSet.getString("username"));
                user.setUserName(resultSet.getString("user_name"));

//                user.setPassword(resultSet.getString("password"));
                user.setUserPassword(resultSet.getString("user_password"));
                user.setPhone(resultSet.getString("phone"));
//				 users.add(user);
                return user;
            }
//			 return users;

        } catch (SQLException e) {

            e.printStackTrace();
        }finally {
            //5.关闭
            JDBCUtil.close(resultSet, preparedStatement, connection);
        }
        return null;
    }

    @Override
    public Boolean selectUseName(Connection connection,String userName)  throws SQLException{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from user  where user_name =?");
            Object[] params = {userName};
            System.out.println("sql ----> " + sql.toString());
            rs = Basedao.execute(pstm, connection, sql.toString(), params, rs);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("user_code"));
                user.setUserName(rs.getString("user_name"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setUserRole(rs.getInt("user_role"));
                user.setUserRoleName(rs.getString("user_role_name"));
                userList.add(user);
            }
            //关闭资源,connection暂时不用关，业务层关
            Basedao.closeResource(pstm, null,rs);
            if(userList.size()!=0) return true;
            return false;
        }
        return false;
    }

    @Override
    public User getLoginUser(Connection connection, String userCode) throws SQLException {

        PreparedStatement pst = null;
        ResultSet rs = null;
        User user = null;

        if (connection != null) {
            String sql = "select * from user where userCode=?";
            Object[] params = {userCode};
            //查询数据库
            rs = Basedao.execute(pst, connection, sql, params, rs);
            //ResultSet内容给User，返回
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("user_code"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPassword(rs.getString("user_password"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("user_role"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            //关闭资源,connection暂时不用关，业务层关
            Basedao.closeResource(pst, null,rs);
        }
        return user;
    }

    //修改密码
    @Override
    public int updatePwd(Connection connection, int id, String pwd) throws SQLException {
        PreparedStatement pstm = null;
        int update = 0;
        if (connection != null){
            String sql = "update smbms_user set userPassword = ? where id = ?";
            Object[] params = {pwd,id};
            update = Basedao.update(pstm, connection, sql, params);
            Basedao.closeResource(pstm,null,null);
        }
        return update;
    }

    //根据用户名或者角色查询用户数
    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        ArrayList<Object> list = new ArrayList<Object>();

        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from user u, role r where u.user_role = r.id");

            if (!StringUtils.isNullOrEmpty(userName)) {
                sql.append(" and u.userName like ?");
                list.add("%"+ userName + "%");   //index:0
            }
            if (userRole > 0) {
                sql.append(" and u.userRole = ?");
                list.add(userRole);   //index:1
            }
            Object[] params = list.toArray();  //转换成数组
            System.out.println("UserDaoImp->getUserCount:" + sql.toString());//调试

            rs = Basedao.execute(pstm,connection,sql.toString(),params, rs);
            if (rs.next()) {
                count = rs.getInt("count");//从结果集中获取数量
            }
            //关闭资源
            Basedao.closeResource(pstm, null, rs);
        }
        return count;
    }

    //通过条件查询-userList
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*,r.roleName as userRoleName from user u,user_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");
            }
            if(userRole > 0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo-1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            System.out.println("sql ----> " + sql.toString());
            rs = Basedao.execute(pstm, connection, sql.toString(), params, rs);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setUserRole(rs.getInt("userRole"));
                user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(user);
            }
            //关闭资源,connection暂时不用关，业务层关
            Basedao.closeResource(pstm, null,rs);
        }
        return userList;
    }

    //添加用户信息
    @Override
    public int addUser(Connection connection, User user) throws SQLException {
        PreparedStatement pstm = null;
        int updateRows = 0;
        if (connection != null) {
            String sql = "insert into user (user_code,user_name,user_password,user_role,gender,birthday,phone,address,creation_date,created_by) values(?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {user.getUserCode(), user.getUserName(), user.getUserPassword(), user.getUserRole(), user.getGender(), user.getBirthday(), user.getPhone(), user.getAddress(), user.getCreationDate(), user.getCreatedBy()};
            updateRows = Basedao.update(pstm,connection,sql,params);
            Basedao.closeResource(pstm, null,null);
        }
        return updateRows;
    }

    //通过用户id删除用户
    public int deleteUserById(Connection connection, Integer delId) throws SQLException {
        PreparedStatement pstm = null;
        int updateRows = 0;
        if (connection != null) {
            String sql = "delete from user where id=?";
            Object[] params = {delId};
            updateRows = Basedao.update(pstm,connection,sql,params);
            Basedao.closeResource(pstm, null,null);
        }
        return updateRows;
    }

    //修改用户信息
    public int modify(Connection connection, User user) throws SQLException {
        int updateRows = 0;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "update user set user_name=?,gender=?,birthday=?,phone=?,address=?,user_role=?,modify_by=?,modify_date=? where id =?";
            Object[] params = {user.getUserName(), user.getGender(), user.getBirthday(),
                    user.getPhone(), user.getAddress(), user.getUserRole(), user.getModifyBy(),
                    user.getModifyDate(), user.getId()};
            updateRows = Basedao.update(pstm,connection,sql,params);
            Basedao.closeResource(pstm, null,null);
        }
        return updateRows;
    }

    //通过userId获取user
    @Override
    public User getUserById(Connection connection, String id) throws Exception {
        // TODO Auto-generated method stub
        User user = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select u.*,r.role_name as user_role_name from user u,role r where u.id=? and u.user_role = r.id";
            Object[] params = {id};
            rs = Basedao.execute(pstm, connection, sql, params, rs);
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("user_code"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPassword(rs.getString("user_password"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("user_role"));
                user.setCreatedBy(rs.getInt("created_by"));
                user.setCreationDate(rs.getTimestamp("creation_date"));
                user.setModifyBy(rs.getInt("modify_by"));
                user.setModifyDate(rs.getTimestamp("modify_date"));
                user.setUserRoleName(rs.getString("user_role_name"));
            }
            Basedao.closeResource(pstm, null,null);
        }
        return user;
    }

}
