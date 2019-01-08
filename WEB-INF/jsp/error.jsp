<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>错误页面</title>
</head>
<body>
	<%String username=(String)request.getSession().getAttribute("username"); %>
	<%
	if(username!=null){
	%>
		<script type="text/javascript">alert("页面崩溃了,用户<%=username%>不存在,请联系管理员")</script>
	<%
	}
	%>
	<b>用户未登录或已过期，请重新登录</b><br/>
	<a href="/user/login.do">登录</a><br/>
	<a href="mailto:123456@qq.com">联系管理员</a><br/>
</body>
</html>