<%@page import="java.util.*"%>
<%@page import="java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>file</title>
</head>
<body>
	<% String url = (String)request.getAttribute("ftpPath");
	System.out.println("url_jsp:"+url);
	%>
	
	<% List<String> files = (List<String>)request.getAttribute("files");
		System.out.println("files:"+files);
	%>
	<% List<String> dirs = (List<String>)request.getAttribute("dirs");
		System.out.println("dirs:"+dirs);
	%>
	<a href="../ftp/file.do?type=-1">../</a><br/>
	<%
	if(dirs.size() != 0){
	for(String dir:dirs){
	%>
	<a href="<%=url %>/<%=dir %>"><%=dir %></a><a href="<%=url %>/<%=dir %>?type=1">下载</a><br/>
	<%
	}}
	%>
	
	<%
	if(files.size() != 0){
	for(String file:files){
	%>
	<a href="<%=url %>?type=0&&name=<%=file %>"><%=file %></a><br/>
	<%
	}}
	%>
</body>
