package com.klbc.app.servlet;

import com.klbc.app.pojo.Order;
import com.klbc.app.service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class orderServlet
 */
@WebServlet("/app/order.do")
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
		System.out.println("==============order.do=============");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		String total = request.getParameter("total");
		String orderId = request.getParameter("orderId");
		String userId = request.getParameter("userId");
		
		
		OrderServiceImpl orderService = new OrderServiceImpl();
		
		
		if(method != null && method.equals("order")) {
			HttpSession session = request.getSession();
			
			//��ȡ���ﳵ(foodid,buynum)
			Map<Integer,Integer> shopCar = (Map<Integer, Integer>) session.getAttribute("shopCar");
			if(shopCar!=null && !shopCar.isEmpty()) {//Map����
			//�������ݵ����ݿ�			
			orderService.order(shopCar,total,Integer.parseInt(userId));			
			//�µ��ˣ�ɾ�����ﳵ����
			session.removeAttribute("shopCar");			
			//��ת����������ҳ��,��ѯ���ﳵ������δɾ���ö���
			//��Ҫ����id ������� �µ�ʱ�� ��Ʒ�� ���� ��� �ܽ��
//			����������id ������� �µ�ʱ��  �ܽ�� 
//			��Ʒ����Ʒ�� ��� 
//			������ϸ������
//			List<Order> orders = OrderServiceImpl.findByTableId(dinnerTableId);
			List<Order> orders = orderService.findDetails(Integer.parseInt(userId));
			System.out.println(orders);
			
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/WEB-INF/jsp/app/orderItem.jsp").forward(request, response);
			}else {
				response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
			}
			
		}
		
		else if(method != null && method.equals("pay")) {
			//���� �޸Ķ���״̬status=1 ����ʱ��
			Order order = orderService.findById(Integer.parseInt(orderId));
			
			order.setStatus(1);
			order.setPayTime(new Date());
			order.setUpdateTime(new Date());
			
			orderService.pay(order);

			//response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
			request.setAttribute("flag",1);
			List<Order> orders = orderService.findDetails(Integer.parseInt(userId));
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/WEB-INF/jsp/app/orderItem.jsp").forward(request, response);
			//response.sendRedirect(getServletContext().getContextPath()+"/app/order.do?method=list&userId="+order.getUserId());
			
			
		}
		
		else if(method != null && method.equals("delete")) {
			//ȡ������ ��������disabled=1 
			Order order = orderService.findById(Integer.parseInt(orderId));
			//����״̬ ��0 δ֧�� 1��֧�� 2�ѳ��� 3��ȡ��
			order.setDisabled(1);
			order.setStatus(3);
			order.setUpdateTime(new Date());
			orderService.deleteOrder(order);
			request.setAttribute("flag",3);
			List<Order> orders = orderService.findDetails(Integer.parseInt(userId));
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/WEB-INF/jsp/app/orderItem.jsp").forward(request, response);
			//response.sendRedirect(getServletContext().getContextPath()+"/app/order.do?method=list&userId="+order.getUserId());
			
		}
		else if(method != null && method.equals("list")) {
			//��ѯ���ж���
//			List<Order> orders = orderService.findAll();
//			System.out.println(orders);
//			
//			request.setAttribute("orders", orders);
			List<Order> orders = orderService.findDetails(Integer.parseInt(userId));
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/WEB-INF/jsp/app/orderItem.jsp").forward(request, response);
		}
	}

}
