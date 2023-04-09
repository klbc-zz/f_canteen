package com.klbc.app.dao;

import com.klbc.app.pojo.Comment;

import java.util.List;

public interface CommentDao {

	List<Comment> findByFoodId(int foodid);

	void comment(int userId, int foodId, String content);

}
