package com.klbc.app.servlet;

import com.klbc.app.service.UserServiceImpl;
import com.klbc.app.pojo.User;
import com.klbc.app.service.UserService;
import com.klbc.sys.util.MD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/app/login.do")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post传递数据过来会乱码
		String method = request.getParameter("method");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		System.out.println("method:"+method);
		
		if(method!=null &&method.equals("submitTable")) {
			//提交登陆表单
			if(name != null && !name.equals("")&&password!=null) {
				UserService userService = new UserServiceImpl();
				password = MD5.convertMD5(password);
				User user  = userService.findByLoginNameAndPass(name,password);
				System.out.println("user"+user);
				if(user !=null) {
					//保存在session中的数据是在整个浏览器中有效，默认30分钟
					request.getSession().setAttribute("session_user", user);
					request.getSession().setAttribute("flag", 0);
					//登陆成功 跳转到首页
					response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
				}else {
					//shibai 跳转到登录页面
					
					request.getRequestDispatcher("/WEB-INF/jsp/app/login.jsp").forward(request, response);
				}
			}
		}
		else {
			//跳转到登录页面
			
			request.getRequestDispatcher("/WEB-INF/jsp/app/login.jsp").forward(request, response);
		}
		
		
	}

}
