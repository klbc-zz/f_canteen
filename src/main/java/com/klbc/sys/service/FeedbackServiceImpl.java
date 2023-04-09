package com.klbc.sys.service;

import com.klbc.sys.bean.Feedback;
import com.klbc.sys.dao.FeedbackDao;
import com.klbc.sys.dao.FeedbackDaoImpl;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    FeedbackDao feedbackDao = new FeedbackDaoImpl();
    @Override
    public int addFeedback(Feedback feedback) {
        return feedbackDao.addFeedback(feedback);
    }

    @Override
    public int updateFeedbackStatus(Feedback feedback) {
        return feedbackDao.updateFeedbackStatus(feedback);
    }

    @Override
    public List<Feedback> selectFeedbackList(Feedback feedback) {
        return feedbackDao.selectFeedbackList(feedback);
    }
}
