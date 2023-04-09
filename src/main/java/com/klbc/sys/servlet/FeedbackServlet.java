package com.klbc.sys.servlet;


import com.klbc.sys.bean.Feedback;
import com.klbc.sys.service.FeedbackService;
import com.klbc.sys.service.FeedbackServiceImpl;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;


/**
 * Servlet implementation class foodServlet
 */
@WebServlet("/sys/feedback.do")
public class FeedbackServlet extends HttpServlet{
    FeedbackService feedbackService = new FeedbackServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的相对地址
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String servletPath = req.getServletPath();
        Feedback feedback = new Feedback();
        String id = req.getParameter("id");
        String userId = req.getParameter("userId");
        String userName = req.getParameter("userName");
        String content = req.getParameter("content");
        String status = req.getParameter("status");
        String creationDate = req.getParameter("creationDate");
        String disabled = req.getParameter("disabled");
        String method = req.getParameter("method");
        if(!StringUtils.isNullOrEmpty(id)) feedback.setId(Integer.valueOf(id));
        if(!StringUtils.isNullOrEmpty(userId)) feedback.setUserId(Integer.valueOf(userId));
        if(!StringUtils.isNullOrEmpty(userName)) feedback.setUserName(userName);
        else feedback.setUserName("");
        if(!StringUtils.isNullOrEmpty(content)) feedback.setContent(content);
        if(!StringUtils.isNullOrEmpty(status)) feedback.setStatus(status);
        if(!StringUtils.isNullOrEmpty(disabled)) feedback.setStatus(disabled);
        //if(StringUtils.isNullOrEmpty(creationDate)) feedback.setCreationDate(creationDate);
        if ("add".equals(method)){
            // 添加
            add(req, resp,feedback);
        }else if ("delete".equals(method)){
            // 删除
            delete(req,resp,feedback);
        }else if ("update".equals(method)){
            // 修改
            update(req,resp,feedback);
        }else if ("list".equals(method)){
            // 查询
            list(req,resp,feedback);
        }else if ("userList".equals(method)){
            // 查询
            userList(req,resp,feedback);
        }else if("toPage".equals(method)){
            toPage(req,resp,feedback);
        }
    }


    public void toPage(HttpServletRequest req, HttpServletResponse resp,Feedback feedback) throws ServletException, IOException {
        // 执行添加的业务逻辑

        req.setAttribute("userId", feedback.getId());
        req.getRequestDispatcher("/WEB-INF/jsp/app/userFeedback.jsp").forward(req, resp);

    }

    public void add(HttpServletRequest req, HttpServletResponse resp,Feedback feedback) throws ServletException, IOException {
        // 执行添加的业务逻辑
        int re = feedbackService.addFeedback(feedback);
        if(re>0){

            req.setAttribute("flag", 1);
            req.getRequestDispatcher("/WEB-INF/jsp/app/userFeedback.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("/WEB-INF/jsp/app/userFeedback.jsp").forward(req, resp);
        }

    }

    public void delete(HttpServletRequest req, HttpServletResponse resp,Feedback feedback){
        // 执行删除的业务逻辑


    }

    public void update(HttpServletRequest req, HttpServletResponse resp,Feedback feedback){
        // 执行更新的业务逻辑
    }
    public void userList(HttpServletRequest req, HttpServletResponse resp,Feedback feedback) throws ServletException, IOException {
        // 执行查询的业务逻辑
        List<Feedback> feedbacks = feedbackService.selectFeedbackList(feedback);
        req.setAttribute("feedbacks", feedbacks);
        req.getRequestDispatcher("/WEB-INF/jsp/app/userFeedbackList.jsp").forward(req, resp);
    }
    public void list(HttpServletRequest req, HttpServletResponse resp,Feedback feedback) throws ServletException, IOException {
        // 执行查询的业务逻辑
        List<Feedback> feedbacks = feedbackService.selectFeedbackList(feedback);
        req.setAttribute("feedbacks", feedbacks);
        req.getRequestDispatcher("/WEB-INF/jsp/sys/feedbackList.jsp").forward(req, resp);
    }

}
