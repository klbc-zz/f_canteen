package com.klbc.sys.servlet;

import com.klbc.sys.bean.User;
import com.klbc.sys.service.UserService;
import com.klbc.sys.service.UserServiceImpl;
import com.klbc.sys.util.MD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Servlet implementation class foodServlet
 */
@WebServlet("/sys/staffList.do")
@MultipartConfig //标识一个servlet支持文件的上传
public class StaffServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("==============/sys/StaffServlet.do=============");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        UserService userService = new UserServiceImpl();


        String keyword = request.getParameter("keyword");


        int userRoleId = 1;
        int currentPageNo =1;
        int pageSize  =10;
        String method = request.getParameter("method");
        String id  = request.getParameter("id");
        String disabled = request.getParameter("disabled");
        //跳转页面和查询数据
        if(method !=null && !method.equals("") && method.equals("list")) {
            //List<Food> foods = foodService.find(keyword,foodTypeId);
            List<User> userList = userService.selectUserlist(keyword,userRoleId,currentPageNo,pageSize);
            request.setAttribute("keyword", keyword);
            request.setAttribute("users", userList);
            request.getRequestDispatcher("/WEB-INF/jsp/sys/staffList.jsp").forward(request, response);
            return;
        }else if(method !=null && !method.equals("") && method.equals("addPage")) {
            request.getRequestDispatcher("/WEB-INF/jsp/sys/staffAdd.jsp").forward(request, response);
            return;
        }else if(method !=null && !method.equals("") && method.equals("viewUpdate")) {
            System.out.println("************viewupdate**************");
            User user1 = userService.selectUserById(Integer.parseInt(id));
            request.setAttribute("user", user1);
            request.getRequestDispatcher("/WEB-INF/jsp/sys/staffUpdate.jsp").forward(request, response);
            return;
        }
        //获取前端信息
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("password");
        String okPassword = request.getParameter("okpassword");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        //String userRole = request.getParameter("userRole");

        //给用户赋值
        User user = new User();
        if(userCode!=null)user.setUserCode(userCode);else user.setUserCode("null");
        if(userName!=null)user.setUserName(userName);else user.setUserName("null");
        if(userPassword!=null)user.setUserPassword(userPassword);else user.setUserPassword("null");
        if(address!=null)user.setAddress(address);else user.setAddress("null");
        try {
            if(birthday!=null) user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
            else user.setBirthday(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(gender!=null) user.setGender(Integer.valueOf(gender));else user.setGender(0);
        if(phone!=null) user.setPhone(phone);else user.setPhone("null");
        user.setUserRole(1);
        user.setCreationDate(new Date());
        user.setUserRoleName("员工");

        //String foodName = request.getParameter("foodName");
        //String price = request.getParameter("price");
        //String remark = request.getParameter("remark");
//			String img = request.getParameter("img");
//			System.out.println("keyword:"+keyword+"     foodTypeId:"+foodTypeId);

        if(method !=null && !method.equals("") && method.equals("ajaxUserName")) {
            System.out.println("ajaxUserName");
            //查询输入的用户名是否存在
            int exist = userService.getUserByName(userName);

            if(exist==1) {//该用户已存在
                response.getWriter().print("fail");
            }else {response.getWriter().print("success");}

        }else if(method !=null && !method.equals("") && method.equals("addSubmit")) {

            //添加用户
            System.out.println("************添加员工***************");
            //判断密码是否正确
            if(user.getUserPassword()==null||user.getUserPassword().equals("")||!user.getUserPassword().equals(okPassword)
                    ||user.getUserName()==null){
                request.setAttribute("flag",1);
                request.getRequestDispatcher("/WEB-INF/jsp/sys/staffAdd.jsp").forward(request, response);
            }
            userPassword = MD5.convertMD5(userPassword);
            user.setUserPassword(userPassword);
            //保存用户相关信息到数据库
            if(userService.addUser(user)==0){
                request.setAttribute("flag",1);
                request.getRequestDispatcher("/WEB-INF/jsp/sys/staffAdd.jsp").forward(request, response);
            }
            else{
                request.setAttribute("flag",2);
                List<User> userList = userService.selectUserlist(null,userRoleId,currentPageNo,pageSize);
                request.setAttribute("keyword", keyword);
                request.setAttribute("users", userList);
                request.getRequestDispatcher("/WEB-INF/jsp/sys/staffList.jsp").forward(request, response);
            }

            //response.sendRedirect(getServletContext().getContextPath()+"/sys/userList.do?method=list");

        }else if(method !=null && !method.equals("") && method.equals("updateSubmit")) {
            //更新用户信息
            System.out.println("************updatesubmit**************");
            user.setId(Integer.valueOf(request.getParameter("id")));

            if(userService.updateUser(user)==0){
                request.setAttribute("flag",5);//没更改成功
                User user1 = userService.selectUserById(Integer.parseInt(id));
                request.setAttribute("user", user1);
                request.getRequestDispatcher("/WEB-INF/jsp/sys/staffUpdate.jsp").forward(request, response);
            }else{
                List<User> userList = userService.selectUserlist(null,userRoleId,currentPageNo,pageSize);
                request.setAttribute("keyword", keyword);
                request.setAttribute("users", userList);
                request.setAttribute("flag",6);
                request.getRequestDispatcher("/WEB-INF/jsp/sys/staffList.jsp").forward(request, response);
            }
        }
        else if(method !=null && !method.equals("") && method.equals("delete")) {
            //删除用户信息
            System.out.println("************delete**************");
            user.setId(Integer.valueOf(request.getParameter("id")));
            if(userService.delUser(user.getId())==0){
                request.setAttribute("flag",3);//没删成功
            }else request.setAttribute("flag",4);
            List<User> userList = userService.selectUserlist(null,userRoleId,currentPageNo,pageSize);
            request.setAttribute("keyword", keyword);
            request.setAttribute("users", userList);
            request.getRequestDispatcher("/WEB-INF/jsp/sys/staffList.jsp").forward(request, response);
        }

    }

}
