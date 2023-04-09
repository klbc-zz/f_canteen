package com.klbc.app.service;

import com.klbc.app.pojo.Comment;
import com.klbc.app.dao.CommentDaoImpl;

import java.util.List;

public class CommentServiceImpl implements CommentService {
	
	
	CommentDaoImpl commentDao = new CommentDaoImpl();
	@Override
	public List<Comment> findByFoodId(int foodid) {
		// TODO 自动生成的方法存根
		return commentDao.findByFoodId(foodid);
	}
	@Override
	public void comment(int userId, int foodId, String content) {
		// TODO 自动生成的方法存根
		commentDao.comment(userId,foodId,content);
	}

}
