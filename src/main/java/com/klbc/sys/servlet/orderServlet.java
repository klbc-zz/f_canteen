package com.klbc.sys.servlet;

import com.klbc.sys.bean.Order;
import com.klbc.sys.service.OrderService;
import com.klbc.sys.service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class orderServlet
 */
@WebServlet("/sys/orderList.do")
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("**************orderList.do**********");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		String id = request.getParameter("id");
		String disabled = request.getParameter("disabled");
		
		OrderService orderService = new OrderServiceImpl();
		if(method!=null && !method.equals("") && method.equals("list")) {
			List<Order> orders = orderService.find();
			System.out.println(orders);
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/WEB-INF/jsp/sys/orderList.jsp").forward(request, response);
		}else if(method!=null && !method.equals("") && method.equals("update")) {
			Order order = orderService.findById(Integer.parseInt(id));
			System.out.println(order);
			order.setDisabled(Integer.parseInt(disabled));
			//¸ÄÎªÒÑÅäËÍ
			order.setStatus(2);
			orderService.update(order);
			//response.sendRedirect(getServletContext().getContextPath()+"/sys/orderList.do?method=list");
			List<Order> orders = orderService.find();
			request.setAttribute("orders", orders);
			request.setAttribute("flag", 2);
			request.getRequestDispatcher("/WEB-INF/jsp/sys/orderList.jsp").forward(request, response);
			
		}
		
		
		
		
	}

}
