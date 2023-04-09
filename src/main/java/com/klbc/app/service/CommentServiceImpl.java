package com.klbc.app.service;

import com.klbc.app.pojo.Comment;
import com.klbc.app.dao.CommentDaoImpl;

import java.util.List;

public class CommentServiceImpl implements CommentService {
	
	
	CommentDaoImpl commentDao = new CommentDaoImpl();
	@Override
	public List<Comment> findByFoodId(int foodid) {
		// TODO �Զ����ɵķ������
		return commentDao.findByFoodId(foodid);
	}
	@Override
	public void comment(int userId, int foodId, String content) {
		// TODO �Զ����ɵķ������
		commentDao.comment(userId,foodId,content);
	}

}
