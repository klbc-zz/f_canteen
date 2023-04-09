<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>后台用户列表</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sys/index.css">
  <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript">
    //文档加载完后
    window.onload=function(){

      //获取后台保存的disabled值
      var foodTypeId = "${foodTypeId}";
      //遍历菜系是否删除状态的select标签中所有的option标签
      var disabledSelect = document.getElementById("foodTypeId")
      //获取下拉框中所有的option
      var options = disabledSelect.options;

      $.each(options,function(i,option){
        $(option).attr("selected",option.value == foodTypeId);
      });

    }

    function foodTypeChange(obj){
      //获取用户输入的关键字
      var keyword = $("#keyword").val();
      //获取被选择的
      var foodTypeId = obj.value;

      //发送请求
      window.location="${pageContext.request.contextPath}/sys/foodList.do?method=list&keyword="+keyword+"&foodTypeId="+foodTypeId;
    }

  </script>
  <c:if test="${requestScope.flag==2}">
    <script>
      alert("用户已被添加！");
    </script>
  </c:if>
  <c:if test="${requestScope.flag==3}">
    <script>
      alert("删除失败！");
    </script>
  </c:if>
  <c:if test="${requestScope.flag==4}">
    <script>
      alert("删除成功！");
    </script>
  </c:if>
  <c:if test="${requestScope.flag==6}">
    <script>
      alert("更改成功！");
    </script>
  </c:if>
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
    <li class="sysli">
      <a  href="${pageContext.request.contextPath}/sys/userList.do?method=list" >用户管理</a>
    </li>
    <li class="sysli">
      <a  href="${pageContext.request.contextPath}/sys/staffList.do?method=list" class="active" >员工管理</a>
    </li>
    <li class="sysli">
      <a  href="${pageContext.request.contextPath}/sys/foodTypeList.do?method=list">菜系管理</a>
    </li>
    <li class="sysli">
      <a  href="${pageContext.request.contextPath}/sys/foodList.do?method=list" >菜品管理</a>
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

    <!--        筛选-->
    <div>
      <form action="${pageContext.request.contextPath}/sys/userList.do" method="get">
        <input type="hidden" name="method" value="list">
        <input id="keyword" name="keyword" type="text" placeholder="请输入用户名" value="${keyword }">
        <input type="submit" value="搜索">
        <a href="${pageContext.request.contextPath}/sys/staffList.do?method=addPage"><input type="button"  value="添加" ></a>
      </form>
    </div><br>
    <!--        表显示-->
    <div>
      <table class="table1">
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
          <td>编号</td>
          <td>用户名</td>
          <td>性别</td>
          <td>电话</td>
          <td>角色</td>
          <td>创建时间</td>
          <td>修改时间</td>
          <%--                    <td>是否删除</td>--%>
          <td>操作</td>
        </tr>
        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">

        <c:choose>
          <c:when test="${not empty users }">
            <c:forEach items="${users}" var="user" varStatus="status">
              <tr class="TableDetail1">

                <td>${status.index+1 }&nbsp;</td>
                <td>${user.userName }&nbsp;</td>
                <c:if test="${ user.gender==0 }"><td> 女</td></c:if>
                <c:if test="${ user.gender==1 }"><td> 男</td></c:if>
                <td>${user.phone }&nbsp;</td>
                <td>${user.userRoleName }&nbsp;</td>
                <td><fmt:formatDate value="${user.creationDate }" pattern="yyyy-MM-dd HH:mm:ss"/>   </td>
                <td><fmt:formatDate value="${user.modifyDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                  <%--                                <td>--%>
                  <%--                                    <c:if test="${user.disabled==1 }">--%>
                  <%--                                        已删--%>
                  <%--                                    </c:if>--%>
                  <%--                                    <c:if test="${user.disabled==0 }">--%>
                  <%--                                        未删--%>
                  <%--                                    </c:if>--%>
                  <%--                                </td>--%>
                <td>
                  <a href="${pageContext.request.contextPath}/sys/userList.do?method=viewUpdate&id=${user.id }"  class="FunctionButton">更改</a>
                  <a href="${pageContext.request.contextPath}/sys/userList.do?method=delete&id=${user.id }"  class="FunctionButton">删除</a>
                    <%--                                    <c:if test="${food.disabled==1 }">--%>
                    <%--                                        <a href="${pageContext.request.contextPath}/sys/foodList.do?method=update&id=${food.id }&disabled=0" class="FunctionButton">上架</a>--%>
                    <%--                                    </c:if>--%>
                    <%--                                    <c:if test="${food.disabled==0 }">--%>
                    <%--                                        <a href="${pageContext.request.contextPath}/sys/foodList.do?method=update&id=${food.id }&disabled=1" class="FunctionButton">下架</a>--%>
                    <%--                                    </c:if>--%>
                </td>

              </tr>
            </c:forEach>

          </c:when>
          <c:when test="${ empty users }">
            没有你要找的数据！
          </c:when>
        </c:choose>



        </tbody>
      </table>
    </div>


  </div>

</div>
</body>
</html>