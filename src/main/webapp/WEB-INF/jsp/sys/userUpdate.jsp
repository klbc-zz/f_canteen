<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改用户</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sys/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">

        function ajaxFoodName(){//页面局部刷新

            //获取用户输入
            var flag = true;
            $("#message").html("");
            var userName = $("#userName").val();
            console.log("aaaaaaaaaaaaaaaaaaadsasaaa:",userName)
            // alert(userName);
            if(userName == null && userName==""){
                $("#message").html("用户名字不能为空");
                flag=false;
            }
            else{
                jQuery.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/sys/userList.do?method=ajaxUserName",
                    data: "userName="+userName,
                    dataType:"text",
                    /* async:false, */
                    success: function(msg){
                        if(msg != null && msg == "success"){
                            $("#message").html("该用户名可用！");
                            // $("#userName").val("");
                        }else if(msg != null && msg == "fail"){
                            $("#message").html("用户名已存在，请重新选择！");
                            $("#userName").val("");
                            flag=false;
                        }
                    },error:function(){
                        alert("数据加载异常");
                    }
                })

            }
            return flag;
        }

        function ajaxPassword(){
            var flag = true;
            $("#message").html("");
            var password = $("#password").val();
            if(password == null || password==""){
                $("#message").html("密码不能为空");
                flag=false;
            }
            return flag;
        }
        function ajaxokPassword(){
            var flag = true;
            $("#message").html("");
            var password = $("#password").val();
            var okpassword = $("#okpassword").val();
            if(okpassword == null || okpassword==""){
                $("#message").html("密码不能为空");
                flag=false;
            }else if(okpassword!=password){
                $("#message").html("两次密码输入不一致");
                flag=false;
            }

            return flag;
        }

        function addSubmitTest(){
            if(!ajaxFoodName() || !ajaxPassword()){
                return false;
            }

            document.getElementById("form").submit();

        }


    </script>
    <c:if test="${requestScope.flag==1}">
        <script>
            alert("填写信息错误！请重新填写");
        </script>
    </c:if>
    <c:if test="${requestScope.flag==5}">
        <script>
            alert("更改失败请重新更改");
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
            <a  href="${pageContext.request.contextPath}/sys/userList.do?method=list" class="active">用户管理</a>
        </li>
        <li class="sysli">
            <a  href="${pageContext.request.contextPath}/sys/staffList.do?method=list" >员工管理</a>
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
        <!-- from标签必须设置  enctype="multipart/form-data" 默认post -->
        <form id="form" action="${pageContext.request.contextPath}/sys/userList.do" method="post" enctype="multipart/form-data">
            <input type="hidden" name="method" value="updateSubmit">
            <input type="hidden" name="id" value="${user.id }">
            <!--        <img src="../img/demo1.jpg" alt="picture" width="300" height="200">-->
            <!-- 本段标题（分段标题） -->
            <div >
                <a href="${pageContext.request.contextPath}/sys/userList.do?method=list"><img src="${pageContext.request.contextPath}/img/sys/back.jpg" style="width: 30px;height: 30px"></a>&nbsp;

            </div>
            <!-- 本段表单字段 -->
            <div class="useradd">
                <h2>修改用户</h2>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <label color="red" id="message"></label>
                    <tr>
                        <td>用户名</td>
                        <td><input type="text" id="userName" name="userName" value="${user.userName }" onblur="ajaxFoodName()"/> *
                        </td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td><input type="password"  onblur="ajaxPassword()" id="password" name="password" /> *</td>
                    </tr>
                    <tr>
                        <td>确认密码</td>
                        <td><input type="password"  onblur="ajaxokPassword()" id="okpassword" name="okpassword" /> *</td>
                    </tr>
                    <tr>
                        <td>电话</td>
                        <td><input type="text" name="phone"  value="${user.phone }" id="phone" /></td>
                    </tr>


                </table>
                <br>
                <div class="foodaddbtn">
                    <input type="button" onclick="addSubmitTest()" value="更改" class="FunctionButtonInput" >
                </div>
            </div>


            <!-- 表单操作 -->
            <!--        <div id="InputDetailBar">-->
            <!--            <input type="button" onclick="addSubmitTest()" value="添加" class="FunctionButtonInput">-->
            <!--            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>-->
            <!--        </div>-->
        </form>

    </div>
</div>
</body>
</html>