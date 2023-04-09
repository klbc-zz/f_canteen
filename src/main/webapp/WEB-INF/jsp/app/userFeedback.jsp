<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" xmlns:bothmargin-top="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>用户反馈页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        function comment(){
            var contents = document.getElementById("contents").value;
            document.getElementById("commentform").submit();
        }
        function submitTable() {

        }
    </script>
     <c:if test="${requestScope.flag==1}">
        <script>
            alert("反馈成功")
        </script>
    </c:if>
</head>
<body>
<div id="banner">
    <div class="resname">
        <span >食堂订餐</span>
    </div>
    <ul class="bannerul">
        <c:if test="${not empty session_user }">
            <li class="bannerli">
                <a href="${pageContext.request.contextPath}/app/loginout.do">退出</a>
            </li>
        </c:if>
        <c:if test="${ empty session_user }">
            <li class="bannerli">
                <a href="${pageContext.request.contextPath}/app/register.html">注册</a>
            </li>
            <li class="bannerli">
                <a href="${pageContext.request.contextPath}/app/login.do">登录</a>
            </li>

        </c:if>
        <c:if test="${not empty session_user }">
            <li class="bannerli">
                <a href="${pageContext.request.contextPath}/app/order.do?method=list&userId=${session_user.id}">订单</a>
            </li>

            <li class="bannerli">
                <a href="${pageContext.request.contextPath}/app/feedback.do?method=toPage&userId=${session_user.id}">意见反馈</a>
            </li>
            <li class="bannerli">
                <a href="${pageContext.request.contextPath}/app/feedback.do?method=userList&userId=${session_user.id}">我的反馈</a>
            </li>
        </c:if>
        <li class="bannerli">
            <a href="${pageContext.request.contextPath}/app/index.do">主页</a>
        </li>
    </ul>
</div>

<div id="content" class="clearfix">




    <!--    右-->
    <div class="right">


        <div>
            <a href="${pageContext.request.contextPath}/app/index.do"><img src="${pageContext.request.contextPath}/img/app/back.jpg" style="width: 30px;height: 30px"></a>
        </div>

        <div style="margin: auto;width: 700px">
            <h2 style="text-align: center">意见反馈</h2>
            <div class="feedbackForm" >
                <form method="post" action="${pageContext.request.contextPath}/sys/feedback.do" id="feedbackform">
                    <input type="hidden"  name="method" value="add">
                    <input type="hidden"  name="userId" value="${session_user.id}">
                    <input type="hidden"  name="userName" value="${session_user.userName}">
                    <center> <font color="red" id="message"></font></center>
                    <input type="text" name="content" id="content" placeholder="反馈内容"><br>
<%--                    <input type="password" name="password"  id="password" placeholder="密码"><br>--%>

                    <input type="submit" name="login" value="提交"  id="login" onclick="submitTable()">
                </form>
            </div>


        </div>


    </div>
</div>
<!--        分页-->


<div class="footer">
    <a href="#">管理中心</a>
</div>
</body>
</html>