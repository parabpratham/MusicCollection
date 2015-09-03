<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String userName = "" + request.getAttribute("userName");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title><bean:message key="html.title" /></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<bean:message key="loggedin.msg" arg0='<%=userName%>' />
		<br>
		<div>
			<b class="subTopic"> 1. <a
				href="<%=basePath%>/Jsp/Tutorial/Index.jsp">Introduction</a> </b>
			<br>
			<br>
		</div>
		<div>
			<b class="subTopic"> 2. <a
				href="<%=basePath%>/Jsp/Tutorial/Controller.jsp">Controller</a> </b>
			<br>
			<br>
		</div>
		<div>
			<b class="subTopic"> 3. <a
				href="<%=basePath%>/Jsp/Tutorial/Model.jsp">Model</a> </b>
			<br>
			<br>
		</div>
		<div>
			<b class="subTopic"> 1. <a
				href="<%=basePath%>/Jsp/Tutorial/View.jsp">View</a> </b>
			<br>
			<br>
		</div>



	</body>
</html>
