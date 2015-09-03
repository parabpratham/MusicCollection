<%@ include file="/Jsp/Base/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<%@ include file="/Jsp/Base/title.jsp"%>
	<body>
		<table>
			<%@ include file="/Jsp/Base/logo.jsp"%>
			<tr height="75%">
				<td>
					<!--  Enter Page Here -->
					<%
						String user = "" + request.getSession().getAttribute("userName");
						String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
					%>
					<br>
					<br>
					<bean:message key="welcome.message" arg0='<%=user%>' />
					<br>
					<br>
					<a href="<%=basePath%>/musiclist.do?method=list">continue</a>
				</td>
			</tr>
			<%@ include file="/Jsp/Base/footer.jsp"%>
		</table>
	</body>
</html>


