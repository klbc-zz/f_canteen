package com.klbc.sys.service;

import com.klbc.sys.bean.Feedback;

import java.util.List;

public interface FeedbackService {
    int addFeedback(Feedback feedback);
    int updateFeedbackStatus(Feedback feedback);
    List<Feedback> selectFeedbackList(Feedback feedback);
}
