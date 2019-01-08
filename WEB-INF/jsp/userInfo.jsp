<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>修改信息</title>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/login.css" rel="stylesheet"/>
</head>
<body>
<!-- 页面顶部-->
<header id="top">
    <div class="top">
        <img src="../images/header/logo.png" alt=""/>
        <span>用户信息</span>
    </div>
</header>
<div id="infos">
    <div id="info" class="info">
        <form action="/userInfo" method="post" enctype="multipart/form-data">
			<%String sucess=(String)request.getAttribute("sucess"); %>
			<%
			if(sucess!=null){
			%>
				<script type="text/javascript">alert("<%=sucess%>")</script>
			<%	
			}
			%>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td align="right">上传头像：</td>
					<td align="left"><input type="file" class="inputwidth100" name="image" ></td>
				</tr>
				<tr>
					<td align="right">邮箱地址：</td>
					<td align="left"><input type="text" class="inputwidth100" name="email"
					value=${user.email }
					></td>
				</tr>
				<tr>
					<td align="right">真实姓名：</td>
					<td align="left"><input type="text" class="inputwidth100" name="realname"
					value=${user.realname }></td>
				</tr>
				<tr>
					<td align="right">手机号码：</td>
					<td align="left"><input type="text" class="inputwidth100" name="phone"
					value=${user.phone }></td>
				</tr>
				<tr >
					<td align="right">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
					<td align="left" style="line-height: 20px;" valign="middle">
						<input type="radio" name="gender" value="man" id="man" style="width: 15px;"
						<c:if test="${user.gender == 1}" >
						checked='checked'
						</c:if>
						>
						
						<label for="man" style="line-height: 20px">男</label>
						<input type="radio" name="gender" value="woman" id="woman" style="width: 15px;"
						<c:if test="${user.gender == 0}" >
						checked='checked'
						</c:if>>
						<label for="woman">女</label> </td>
				</tr>
				<%-- <tr>
					<td align="right">所在地区：</td>
					<td align="left"><input type="text" name="address" class="inputwidth100"
					value=${user.address }></td>
				</tr> --%>
				<tr>
					<td colspan="2" height="60px"><input type="submit" style="width: 200px;height: 40px" value="修改" class="updatebutton1"></td>
				</tr>
			</table>
		</form>
    </div>
</div>
<!--错误提示-->
<div id="showResult"></div>
<!-- 页面底部-->
<div class="foot_bj">
</div>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../jquery/jquery.cookie.js"></script>
<script>
    $("#username").blur(function(){
        var data = $("#username").val();
        if (data == null || data == "") {
            $("#showResult").text("用户名不能为空！");
            $("#showResult").css("color","red");
            return false;
        }
        $.ajax({
            type:"POST",
            url:"/checkUsername.html",
            data:"username="+data,
            beforeSend:function(XMLHttpRequest)
            {
                $("#showResult").text("正在查询");

            },
            success:function(msg)
            {
                if(msg ==="yes"){
                    $("#showResult").text("该用户名可以被使用");
                }else if(msg === 'no'){
                    $("#showResult").text("该用户名不存在");
                    $("#showResult").css("color","red");
                }else {
                    $("#showResult").text("系统异常！");
                    $("#showResult").css("color","red");
                }
            },
            error:function()
            {
                //错误处理
            }
        });
    });
</script>
<script>
    $('#bt-login').click(function(){
    	var url="handle_login.do";
    	var username=$("#username").val();
    	var password=$("#password").val();
    	var data="username="+username+"&password="+password;
    	//提交并处理ajax异步请求
        $.ajax({
        	"url":url,
        	"data":data,
        	"type":"POST",
        	"dataType":"json",
        	"success":function(obj){//obj是得到的data转换成json
        		if(obj.state==0){//失败
        			alert(obj.message);
        		}else{
        			//alert("登录成功！");
        			location.href="../main/index.do";
        			//location.href="login.do";
        		}
        	}
        });
    });
</script>
<script type="text/javascript">

    $(document).ready(function () {
        if ($.cookie("rmbUser") == "true") {
            $("#ck_rmbUser").attr("checked", true);
            $("#username").val($.cookie("username"));
            $("#password").val($.cookie("password"));
        }
    });

    //记住用户名密码
    function Save() {
        if ($("#ck_rmbUser").attr("checked")) {
            var str_username = $("#username").val();
            console.log(str_username);
            var str_password = $("#password").val();
            $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
            $.cookie("username", str_username, { expires: 7 });
            $.cookie("password", str_password, { expires: 7 });
        }
        else {
            $.cookie("rmbUser", "false", { expire: -1 });
            $.cookie("username", "", { expires: -1 });
            $.cookie("password", "", { expires: -1 });
        }
    };
</script>
</body>
</html>