package com.klbc.app.service;

import com.klbc.app.pojo.Comment;

import java.util.List;

public interface CommentService {

	List<Comment> findByFoodId(int parseInt);

	void comment(int parseInt, int parseInt2, String content);

}
