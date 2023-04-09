<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理中心</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sys/index.css">

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
            <a  href="${pageContext.request.contextPath}/sys/index.do" class="active">主页</a>
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
            <a  href="${pageContext.request.contextPath}/sys/foodList.do?method=list">菜品管理</a>
        </li>
        <li class="sysli">
            <a href="${pageContext.request.contextPath}/sys/orderList.do?method=list">订单管理</a>
        </li>
        <li class="sysli">
            <a href="${pageContext.request.contextPath}/sys/saleList.do?method=list" >销量统计</a>
        </li>
        <li class="sysli">
            <a href="${pageContext.request.contextPath}/sys/feedback.do?method=list" >意见反馈</a>
        </li>
    </ul>

    <div class="sysright">
        <span>欢迎来到食堂点餐系统后台管理中心！</span>
    </div>

</div>
</body>
</html>