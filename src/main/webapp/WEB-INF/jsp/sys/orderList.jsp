<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台订单列表</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sys/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<div id="banner">
    <div class="resname">
        <span >食堂后台管理</span>
    </div>
    <!--        登录-->
    <div >
        <c:if test="${not empty session_admin }">
            <ul class="bannerul">
                <span style="float: right">欢迎！${session_admin.userRoleName}:${session_admin.userName} </span>
            </ul>
        </c:if>
        <c:if test="${ empty session_admin }">

            <script type="text/javascript">
                alert("请先登录！！")
                window.location.href = '${pageContext.request.contextPath}/sys/loginsys.do';
            </script>
        </c:if>
    </div>
</div>

<div id="contentsys">
   <ul class="sysul">
        <li class="sysli">
            <a  href="${pageContext.request.contextPath}/sys/index.do" >主页</a>
        </li>
       <c:if test="${session_admin.userRole==0 }">
           <li class="sysli">
               <a  href="${pageContext.request.contextPath}/sys/userList.do?method=list">用户管理</a>
           </li>
           <li class="sysli">
               <a  href="${pageContext.request.contextPath}/sys/staffList.do?method=list">员工管理</a>
           </li>
       </c:if>
        <li class="sysli">
            <a  href="${pageContext.request.contextPath}/sys/foodTypeList.do?method=list">菜系管理</a>
        </li>
        <li class="sysli">
            <a  href="${pageContext.request.contextPath}/sys/foodList.do?method=list" >菜品管理</a>
        </li>
        <li class="sysli">
            <a href="${pageContext.request.contextPath}/sys/orderList.do?method=list" class="active">订单管理</a>
        </li>
        <li class="sysli">
           <a href="${pageContext.request.contextPath}/sys/saleList.do?method=list" >销量统计</a>
        </li>
       <li class="sysli">
           <a href="${pageContext.request.contextPath}/sys/feedback.do?method=list" >意见反馈</a>
       </li>
    </ul>


    <div class="sysright">

        <!--        筛选菜品-->
        <div>
            <form>
                <input type="text" placeholder="请输入订单编号">
                <input type="submit" value="搜索">

            </form>
        </div><br>
        <!--        菜品列表显示-->
        <div>
            <table class="table1">
                <thead>
                <tr align="center" valign="middle" id="TableTitle">
                    <td>订单编号</td>
                    <td>用户名</td>
                    <td>价格</td>
                    <td>下单时间</td>
                    <!-- <td>付款时间</td> -->
                    <td>状态</td>
                    <td>操作</td>
                </tr>
                </thead>
                <!--显示数据列表 -->
                <tbody id="TableData">
                
                <c:if test="${not empty orders}">
                	<c:forEach items="${orders }" var="order">
	                	<tr class="TableDetail1">
	                    <td>${order.orderCode }&nbsp;</td>
	                    <td>${order.user.userName }&nbsp;</td>
	
	                    <td>${order.totalPrice }&nbsp;</td>
	                    <td><fmt:formatDate value="${order.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <%-- <td><fmt:formatDate value="${order.payTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
	                    <c:if test="${order.status== 0}">
		                    <td>
		                        	未付款
		                    </td>
	                    </c:if>
	                    <c:if test="${order.status== 1}">
		                    <td>
		                        	已付款
		                    </td>
	                    </c:if>
                        <c:if test="${order.status== 2}">
                            <td>
                                     已出餐
                            </td>
                        </c:if>
	                    <td>
                            <c:if test="${order.status==1 }">
                                <a href="${pageContext.request.contextPath}/sys/orderList.do?method=update&id=${order.id }&disabled=0" class="FunctionButton">出餐</a>
                            </c:if>
<%--	                        <a href="${pageContext.request.contextPath}/sys/orderList.do?method=viewUpdate&id=${order.id }"  class="FunctionButton">更新</a>--%>
<%--	                        <c:if test="${order.disabled==1 }">--%>
<%--	                        	<a href="${pageContext.request.contextPath}/sys/orderList.do?method=update&id=${order.id }&disabled=0" class="FunctionButton">激活</a>--%>
<%--	                        </c:if>--%>
<%--	                        <c:if test="${order.disabled==0 }">--%>
<%--	                        <a href="${pageContext.request.contextPath}/sys/orderList.do?method=update&id=${order.id }&disabled=1" class="FunctionButton">删除</a>--%>
<%--	                        </c:if>--%>
	                    </td>
	                </tr>
                	
                	</c:forEach>
                
                </c:if>
                
                </tbody>
            </table>
        </div>


    </div>

</div>
</body>
</html>