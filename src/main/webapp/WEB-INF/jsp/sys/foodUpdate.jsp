<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>更新新菜品</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sys/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
window.onload=function(){
	
	//获取后台保存的disabled值
	var foodTypeId = "${food.foodTypeId}";
	//遍历菜系是否删除状态的select标签中所有的option标签
	var disabledSelect = document.getElementById("foodTypeId")
	//获取下拉框中所有的option
	var options = disabledSelect.options;
	
	$.each(options,function(i,option){
		$(option).attr("selected",option.value == foodTypeId);
	});
	
}

function ajaxFoodName(){//页面局部刷新
	//获取用户输入的菜品名
	var flag = true;
	$("#message").html("");
	var foodName = $("#foodName").val();
	
	var baginFoodName = "${food.foodName}";
	
	if(foodName == null && foodName==""){
		$("#message").html("菜品名字不能为空");
		flag=false;	
	}else if (foodName!=baginFoodName){
		
	
		jQuery.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/sys/foodList.do?method=ajaxFoodName",
			   data: "foodName="+foodName,
			   dataType:"text",
			   /* async:false, */
			   success: function(msg){
				   if(msg != null && msg == "success"){
					   $("#message").html("保存成功！");
	                	  $("#foodName").val("");
                  }else if(msg != null && msg == "fail"){
                	  $("#message").html("菜品名称已存在，请重新取名！");
                	  $("#foodName").val("");
                	  flag=false;	
                  }
			   },error:function(){
				   alert("数据加载异常");
			   }
		})
	
	}
	return flag;
}


function ajaxPrice(){
	var flag = true;
	$("#message").html("");
	var price = $("#price").val();
	if(price == null || price==""){
		$("#message").html("菜品价格不能为空");
		flag=false;		
	}
	else if( !/^(([1-9]\d*[.]\d\d?)|([0][.]\d\d?)|([1-9]\d*)|0)$/.test(price)){
		//校验价格 价格有小数 价格是整数 价格为0
		//2.22
		//([1-9]\d*[.]\d\d?)|([0][.]\d\d?)|([1-9]\d*)|0
		$("#message").html("请输入正确的菜品价格！");
		$("#price").val("");
		flag=false;	
	}
	return flag;
	}
	
	



function addSubmitTest(){
	if(!ajaxFoodName() || !ajaxPrice()){
		return false;
	}
	
	
	
	
	if(imgName != null || imgName!=""){
		
		//获取的是上传文件的本地路径
		var imgName = $("#img").val();
		//找到最后.的索引值
		var extStar = imgName.lastIndexOf(".");
		//获取.jpg   //kai zhong  索引
		var ext  = imgName.substring(extStar,imgName.length).toLowerCase();
		 if(ext.match(/.png|.jpg|.jpeg|.bmp|.jif/i) == null){
		//表明用户上传的文件不符合要求
		$("#message").html("请上传png,jpg,jpeg,bmp,jif格式的菜品图片");
		return false;
	}
	
	document.getElementById("form").submit();
	
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
            <a  href="${pageContext.request.contextPath}/sys/foodTypeList.do?method=list">菜系管理</a>
        </li>
        <li class="sysli">
            <a  href="${pageContext.request.contextPath}/sys/foodList.do?method=list" class="active">菜品管理</a>
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
        <form id="form" action="${pageContext.request.contextPath}/sys/foodList.do" method="post" enctype="multipart/form-data">
                   <input type="hidden" name="method" value="updateSubmit">
            <!--        <img src="../img/demo1.jpg" alt="picture" width="300" height="200">-->
            <!-- 本段标题（分段标题） -->
            <div >
                <a href="${pageContext.request.contextPath}/sys/foodList.do?method=list"><img src="${pageContext.request.contextPath}/img/sys/back.jpg" style="width: 30px;height: 30px"></a>&nbsp;

            </div>
            <!-- 本段表单字段 -->
            <div class="foodadd">
                <h2>更新菜品</h2>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td>菜系 </td>
                        <td>
                            <select name="foodTypeId" id="foodTypeId" name="cid" style="width: 150px">
                               <c:forEach items="${foodTypes }" var="foodType">
                    
			                    	<option value="${foodType.id}">${foodType.typeName}</option>
			                    </c:forEach>
                            </select>
                            *
                            <input type="hidden" name="id" value="${food.id }" />
                             	<label color="red" id="message"></label>
                        </td>
                    </tr>
                    <tr>
                        <td>菜名</td>
                        <td><input type="text" id="foodName" name="foodName" value="${food.foodName }" onblur="ajaxFoodName()"/> *</td>
                    </tr>
                    <tr>
                        <td>价格</td>
                        <td><input type="text" placeholder="最多两位小数"  onblur="ajaxPrice()" id="price" name="price" value="${food.price }"/> *</td>
                    </tr>
                    <tr>
                        <td>简介</td>
                        <td><textarea name="remark" value="" id="remark" >${food.remark }</textarea></td>
                    </tr>
                    <tr>
                        <td>菜品图片</td>
                        <td>
                            <input type="file"  id="img" name="img" /> *
                        </td>
                    </tr>
                    <tr>
                        <td  colspan="2">
                            <img id="preview" src="${pageContext.request.contextPath}/upload/food/${food.img}"
                                 width=200px height=100px style="diplay:none" />
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