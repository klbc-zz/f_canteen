package com.klbc.sys.dao;

import com.klbc.sys.bean.Feedback;

import java.util.List;

public interface FeedbackDao {
    int addFeedback(Feedback feedback);
    int updateFeedbackStatus(Feedback feedback);
    List<Feedback> selectFeedbackList(Feedback feedback);

}
