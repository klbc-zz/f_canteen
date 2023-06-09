<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>更新菜系</title>
    <link rel="stylesheet" type="text/css" href="../css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sys/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
    	function addSubmitTest(){
    		var foodTypeName = $("#foodTypeName").val();
    		if(foodTypeName != null && foodTypeName != "" && foodTypeName != "${foodType.typeName}"){
    			//没有重复返回true  保存菜系名称
    			jQuery.ajax({
    				   type: "POST",
    				   url: "${pageContext.request.contextPath}/sys/foodTypeList.do?&method=updateSubmit",
    				   data: "foodTypeName="+foodTypeName+"&id="+"${foodType.id}", 
    				   dataType:"text",
    				   async:false,
    				   success: function(msg){
    					   if(msg != null && msg == "success"){
    						   $("#message").html("保存成功！");
    		                	  $("#foodTypeName").val("");
    	                  }else if(msg != null && msg == "fail"){
    	                	  $("#message").html("菜系名称已存在，请重新取名！");
    	                	  $("#foodTypeName").val("");
    	                  }
    				   },error:function(){
    					   alert("数据加载异常");
    				   }
    			})
    		}else{
    	   	 	$("#message").html("菜系名称不能为空且不能和修改前一致！");
    		} 
    	}
    	
    </script>
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
            <a  href="${pageContext.request.contextPath}/sys/foodTypeList.do?method=list" class="active">菜系管理</a>
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
        <!-- from标签必须设置  enctype="multipart/form-data" 默认post -->
        <form id="form" action="${pageContext.request.contextPath}/sys/foodTypeList.do?method=list" method="post" enctype="multipart/form-data">
            <!--        <input type="hidden" name="method" value="addSubmit">-->
            <!--        <img src="../img/demo1.jpg" alt="picture" width="300" height="200">-->
            <!-- 本段标题（分段标题） -->
            <div >
                <a href="${pageContext.request.contextPath}/sys/foodTypeList.do?method=list">
                <img src="${pageContext.request.contextPath}/img/sys/back.jpg" style="width: 30px;height: 30px">
                </a>
            </div>
            <!-- 本段表单字段 -->
            <div class="foodadd">
                <h2>更新菜系</h2>
                <table cellpadding="0" cellspacing="0" class="mainForm">

                    <tr>
                        <td>菜系名</td>
                        <td>
                        	<input type="text"  value="${foodType.typeName }" id="foodTypeName" name="foodTypeName" class="InputStyle" /> *
                        	<label color="red" id="message"></label>
                        	<input type="hidden" id="id" name="id" value="${foodType.id }"/>
                        </td>
                    </tr>

                </table>
                <br>
                <div class="foodaddbtn">
                    <input type="button" onclick="addSubmitTest()" value="修改" class="FunctionButtonInput" >
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