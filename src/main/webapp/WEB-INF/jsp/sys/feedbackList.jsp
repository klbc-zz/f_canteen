<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台意见反馈列表</title>
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
            alert("更改状态成功！");
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
            <a href="${pageContext.request.contextPath}/sys/orderList.do?method=list">订单管理</a>
        </li>
        <li class="sysli">
            <a href="${pageContext.request.contextPath}/sys/saleList.do?method=list" >销量统计</a>
        </li>
        <li class="sysli">
            <a href="${pageContext.request.contextPath}/sys/feekback.do?method=list" class="active" >意见反馈</a>
        </li>
    </ul>

    <div class="sysright">

        <!--        筛选-->
        <div>
            <form action="${pageContext.request.contextPath}/sys/userList.do" method="get">
                <input type="hidden" name="method" value="list">
                <input id="keyword" name="keyword" type="text" placeholder="请输入用户名" value="${keyword }">
                <input type="submit" value="搜索">
<%--                <a href="${pageContext.request.contextPath}/sys/userList.do?method=addPage"><input type="button"  value="添加" ></a>--%>
            </form>
        </div><br>
        <!--        列表显示-->
        <div>
            <table class="table1">
                <thead>
                <tr align="center" valign="middle" id="TableTitle">
                    <td>编号</td>
                    <td>投诉用户</td>
                    <td>投诉内容</td>
                    <td>投诉时间</td>
                    <td>状态</td>
                    <%--                    <td>是否删除</td>--%>
                    <td>操作</td>
                </tr>
                </thead>
                <!--显示数据列表 -->
                <tbody id="TableData">

                <c:choose>
                    <c:when test="${not empty feedbacks }">
                        <c:forEach items="${feedbacks}" var="feedback" varStatus="status">
                            <tr class="TableDetail1">

                                <td>${status.index+1 }&nbsp;</td>
                                <td>${feedback.userName }&nbsp;</td>
                                <td>${feedback.content }&nbsp;</td>
                                <td><fmt:formatDate value="${feedback.creationDate }" pattern="yyyy-MM-dd HH:mm:ss"/>   </td>
                                <td>${feedback.status }&nbsp;</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/sys/feedback.do?method=update&id=${feedback.id }"  class="FunctionButton">更改状态</a>
                                </td>

                            </tr>
                        </c:forEach>

                    </c:when>
                    <c:when test="${ empty feedbacks }">
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