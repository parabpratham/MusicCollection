<%@ include file="/Jsp/Base/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<%@ include file="/Jsp/Base/title.jsp"%>
	<%
		String dataSource = path + "/home.do?method=checkLogin";
	%>
	<script type="text/javascript">
	var dataSource = '<%=dataSource%>';
		//For Login
		function validate(dataSource)
		{
		
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
	<body>
		<table width="100%" align="right" style="font-size: 5 px">
			<tr height="75%">
				<td align="right">
					<html:form action="home.do" focus="email">
						<!-- onsubmit="return validate(dataSource);" -->
						<table align="center" width="100%">
							<tr valign="top" bordercolor="black">
								<td align="right">
									<%@ include file="login_top.jsp"%>
								</td>
							</tr>
						</table>
					</html:form>
			</tr>
		</table>
	</body>
</html>