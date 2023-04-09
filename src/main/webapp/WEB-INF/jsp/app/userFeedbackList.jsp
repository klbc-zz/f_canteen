<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>订单详情</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app/index.css">
  <script type="text/javascript">
    function del(feedbackId){
      window.location.href = "${pageContext.request.contextPath}/sys/feedback.do?method=delete&feedbackId="+feedbackId;
    }

  </script>
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
        <a href="${pageContext.request.contextPath}/sys/feedback.do?method=toPage&userId=${session_user.id}">意见反馈</a>
      </li>
      <li class="bannerli">
        <a href="${pageContext.request.contextPath}/sys/feedback.do?method=userList&userId=${session_user.id}">我的反馈</a>
      </li>
    </c:if>
    <li class="bannerli">
      <a href="${pageContext.request.contextPath}/app/index.do">主页</a>
    </li>
  </ul>
</div>

<!--订单详情-->
<div class="orderItem">
  <h2>我的反馈列表</h2>


  <c:if test="${not empty feedbacks}">
    <c:forEach items="${feedbacks }" var="feedback">
      <div>
        <div>用户名：${feedback.userName }</div>
        <div>反馈内容：${feedback.content }</div>
        <div>反馈时间：${feedback.creationDate }</div>
        <div>反馈状态：${feedback.status }</div>
      </div>
      <div>
        <div class="orderBtn">
          <c:if test="${feedback.status == '未处理'}">
            <!-- 参数为订单id -->


          </c:if>
        </div>
        <input type="button" value="删除"  onclick="del(${feedback.id})">
      </div>
      <br>
      <hr>
    </c:forEach>

  </c:if>

</div>

</body>
</html>