package com.klbc.sys.dao;

import com.klbc.sys.bean.Feedback;
import com.klbc.sys.bean.Food;
import com.klbc.sys.bean.FoodType;
import com.klbc.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDaoImpl implements FeedbackDao {
    @Override
    public int addFeedback(Feedback feedback) {
        Connection connection =null;
        PreparedStatement pstmt = null;
        ResultSet resultSet =null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into feedback(user_id,user_name,content,status,disabled,creation_date) values(?,?,?,?,0,NOW())";
            System.out.println(sql);
            pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, feedback.getUserId());
            pstmt.setString(2, feedback.getUserName());
            pstmt.setString(3, feedback.getContent());
            pstmt.setString(4, feedback.getStatus());
            //pstmt.setInt(5, feedback.getDisabled());
            int resultSetInt = pstmt.executeUpdate();
            resultSet = pstmt.getGeneratedKeys();
            int id = -1;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(resultSet, pstmt, connection);
        }
        return -1;
    }

    @Override
    public int updateFeedbackStatus(Feedback feedback) {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update feedback set status=? where id=?";
            System.out.println(sql);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, feedback.getStatus());
            preparedStatement.setInt(2, feedback.getId());
            int resultSetInt = preparedStatement.executeUpdate();


        } catch (SQLException e) {

            e.printStackTrace();
        }finally {
            JDBCUtil.close(resultSet, preparedStatement, connection);
        }
        return 0;
    }

    @Override
    public List<Feedback> selectFeedbackList(Feedback feedback) {
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        try {
            connection = JDBCUtil.getConnection();

            StringBuffer sql =new StringBuffer();
            sql.append("select * from feedback where 1=1");
//			sql.append("select * from food where 1=1")
            if(feedback.getUserName()!=null && !feedback.getUserName().equals("")) {//模糊查询
                sql.append(" and user_name LIKE '%"+feedback.getUserName()+"%'");
            }
            if(feedback.getStatus()!=null && !feedback.getStatus().equals("")) {
                sql.append(" and status="+feedback.getStatus());
            }
            if(feedback.getUserId()!=null && !feedback.getUserId().equals("")) {
                sql.append(" and user_id="+feedback.getUserId());
            }
            System.out.println(sql);
            preparedStatement = connection.prepareStatement(sql.toString());

            resultSet = preparedStatement.executeQuery();

            List<Feedback> feedbacks = new ArrayList<>();
            while(resultSet.next()) {
//				Food food = new Food(foodName, Integer.parseInt(foodTypeId), Double.parseDouble(price), remark, newName.toString(), 0);
                Feedback feedback1 = new Feedback();
                feedback1.setId(resultSet.getInt("id"));
                feedback1.setCreationDate(resultSet.getTimestamp("creation_date"));
                feedback1.setUserId(resultSet.getInt("user_id"));
                feedback1.setContent(resultSet.getString("content"));
                feedback1.setStatus(resultSet.getString("status"));
                feedback1.setUserName(resultSet.getString("user_name"));
                feedback1.setDisabled(resultSet.getInt("disabled"));

                feedbacks.add(feedback1);
            }
            System.out.println(feedbacks);
            return feedbacks;

        } catch (SQLException e) {

            e.printStackTrace();
        }finally {
            JDBCUtil.close(resultSet, preparedStatement, connection);
        }


        return null;
    }
}
