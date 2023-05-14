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
//用户角色字典 0 管理员 1 员工 2 学生
/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/app/register.do")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");//post传递数据过来会乱码
        String method = request.getParameter("method");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String okpassword = request.getParameter("okpassword");
        String phone = request.getParameter("phone");

        System.out.println("method:"+method);
        UserService userService = new UserServiceImpl();
        if(method!=null &&method.equals("toPage")){
            //跳转到注册页面
            request.getRequestDispatcher("/WEB-INF/jsp/app/register.jsp").forward(request, response);
        }
        if(name==null||password==null||okpassword==null) {
            response.getWriter().print("<script language='javascript'>alert('数据不能为空')</script>");
            request.getRequestDispatcher("/WEB-INF/jsp/app/register.jsp").forward(request, response);
            return;
        } else  if(userService.selectUseName(name)){
            response.getWriter().print("<script language='javascript'>alert('已存在用户，请重新输入')</script>");
//            response.setHeader("refresh", "1;URL=/WEB-INF/jsp/app/register.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/app/register.jsp").forward(request, response);
            return;
        } else if(okpassword!=null&&!password.equals(okpassword)){
            response.getWriter().print("<script language='javascript'>alert('密码与确认密码不相同，请重新输入')</script>");
            request.getRequestDispatcher("/WEB-INF/jsp/app/register.jsp").forward(request, response);
            return;
        }

        if(method!=null &&method.equals("submitTable")) {
            //提交注册表单
            if(name != null && !name.equals("")) {

                User user  = new User();
                user.setUserName(name);
                password = MD5.convertMD5(password);
                user.setUserPassword(password);
                user.setPhone(phone);
                user.setUserRole(2);
                user.setUserRoleName("顾客");

                System.out.println("user"+user);
                int rs = userService.addUser(user);
                if(rs==0){

                }

                if(user !=null) {
                    //保存在session中的数据是在整个浏览器中有效，默认30分钟
                    request.getSession().setAttribute("session_user", user);
                    request.getSession().setAttribute("flag", 1);
                    //登陆成功 跳转到首页
                    response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
                }else {
                    //shibai 跳转到注册页面

                    request.getRequestDispatcher("/WEB-INF/jsp/app/register.jsp").forward(request, response);
                }
            }
        }
        else {
            //跳转到注册页面
            request.setAttribute("flag",2);
            request.getRequestDispatcher("/WEB-INF/jsp/app/register.jsp").forward(request, response);
        }



    }

}
