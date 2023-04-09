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
 * Servlet implementation class saleListService
 */
@WebServlet("/sys/saleList.do")
public class saleListService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public saleListService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("******************saleList.do**************");
		String method = request.getParameter("method");
		
		OrderService orderService = new OrderServiceImpl();
		
		if(method!=null && !method.equals("") && method.equals("month")) {
			List<Order> orders = orderService.findMonth();
			System.out.println(orders);
			request.setAttribute("orders", orders);	
			request.getRequestDispatcher("/WEB-INF/jsp/sys/saleList.jsp").forward(request, response);
		}else if(method!=null && !method.equals("") && method.equals("list")) {
			request.getRequestDispatcher("/WEB-INF/jsp/sys/saleList.jsp").forward(request, response);
		}else if(method!=null && !method.equals("") && method.equals("week")) {
			List<Order> orders = orderService.findWeek();
			System.out.println(orders);
			request.setAttribute("orders", orders);	
			request.getRequestDispatcher("/WEB-INF/jsp/sys/saleList.jsp").forward(request, response);
		}else if(method!=null && !method.equals("") && method.equals("day")) {
			List<Order> orders = orderService.findDay();
			System.out.println(orders);
			request.setAttribute("orders", orders);	
			request.getRequestDispatcher("/WEB-INF/jsp/sys/saleList.jsp").forward(request, response);
		}
	}

}
