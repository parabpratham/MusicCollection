<%@ include file="/Jsp/Base/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<%@ include file="/Jsp/Base/title.jsp"%>
	<%
		String dataSource = path + "/home.do?method=checkLogin";
		String attempt = "" + request.getAttribute("attempt");
	%>
	<script type="text/javascript">
	var dataSource = '<%=dataSource%>';
	//For Login
		function validate(dataSource)
		{
		
			alert(document.getElementById('method').value);
			if(document.getElementById('method').value=='SignUp')
				return true;
				
			var emailValue=document.getElementById("email").value;
			emailValue= (emailValue=="")?'null':emailValue;
			
			var pwdValue=document.getElementById("password").value;
			pwdValue= (pwdValue=="")?'null':pwdValue;
			
			dataSource = dataSource+'&email='+emailValue+'&pwd='+pwdValue;
			var isValid = getXMLForErrors(dataSource);
			alert("4"+isValid);
			return isValid;
		}
		
	</script>
	<body style="width: 100%; height: 100%;">
		<table width="100%" style="margin: 10px; margin-right: 100px"
			align="center">
			<tr>
				<%@ include file="/Jsp/Base/logo.jsp"%>
				<%
					if (!"second".equalsIgnoreCase(attempt))
					{
				%>
				<td align="right">
					<html:form action="home.do" focus="email">
						<!-- onsubmit="return validate(dataSource);"> -->
						<%@ include file="login_top.jsp"%>
					</html:form>
				</td>
				<%
					}
					else
					{
				%>
				<td>
					&nbsp;
				</td>
				<%
					}
				%>
			</tr>
			<tr>
				<td width="1000 px">
					<hr>
				</td>
			</tr>
			<%
				if ("second".equalsIgnoreCase(attempt))
				{
			%>
			<tr>
				<td>
					<%@ include file="login.jsp"%>
				</td>
			</tr>
			<%
				}
				else
				{
			%>
			<tr>
				<td>
				<%@ include file="defaultPage.jsp"%>
				</td>
			</tr>
			<%
				}
			%>
			<%@ include file="/Jsp/Base/footer.jsp"%>
		</table>
	</body>
</html>