package com.klbc.app.servlet;

import com.klbc.app.pojo.Comment;
import com.klbc.app.pojo.Food;
import com.klbc.app.service.CommentService;
import com.klbc.app.service.CommentServiceImpl;
import com.klbc.app.service.FoodServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class commentServlet
 */
@WebServlet("/app/comment.do")
public class commentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public commentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post�������ݹ���������
		String userId = request.getParameter("userId");
		String foodId = request.getParameter("foodId");
		String content = request.getParameter("contents");
		String method = request.getParameter("method");
		System.out.println("comservlet:userId:"+userId+"  foodId:"+foodId +"  content:"+content +"  method:"+method);
		
		
		CommentService commentService = new CommentServiceImpl();
		if(method!=null && method.equals("index")) {
			
		
			FoodServiceImpl foodService = new FoodServiceImpl();
			Food food = foodService.findByFoodId(Integer.parseInt(foodId));
			System.out.println(food);
			
			//��������ʾ
			
			List<Comment> comments = commentService.findByFoodId(Integer.parseInt(foodId));
			System.out.println("commentservlet:"+comments);
			
			request.setAttribute("comments", comments);
			request.setAttribute("food", food);
	
			request.getRequestDispatcher("/WEB-INF/jsp/app/comment.jsp").forward(request, response);
		}
		//��������
		else if(method!=null && method.equals("submitTable")) {
			request.setCharacterEncoding("utf-8");//post�������ݹ���������
			commentService.comment(Integer.parseInt(userId),Integer.parseInt(foodId),content);
			response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
//			request.getRequestDispatcher("/WEB-INF/jsp/app/comment.jsp").forward(request, response);
		}

		
		
		
	}

}
