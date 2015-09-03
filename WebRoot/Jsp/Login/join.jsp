<%@ include file="/Jsp/Base/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<%@ include file="/Jsp/Base/title.jsp"%>
	<body>
		<table width="100%">
			<%@ include file="/Jsp/Base/logo.jsp"%>
			<tr height="75%" align="center">
				<td>
					<html:form action="join.do" focus="email">
						<table align="left">
							<tr align="center">
								<td align="right">
									<bean:message key="join.email" />
								</td>
								<td align="left">
									&nbsp;
									<html:text property="email" size="15" maxlength="15" />
									&nbsp;
									<html:errors property="email" />
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="join.username" />
								</td>
								<td align="left">
									&nbsp;
									<html:text property="userName" size="15" maxlength="15" />
									&nbsp;
									<html:errors property="userName" />
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="join.password" />
								</td>
								<td align="left">
									&nbsp;
									<html:password property="password" size="15" maxlength="15" />
									&nbsp;
									<html:errors property="password" />
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="join.password2" />
								</td>
								<td align="left">
									&nbsp;
									<html:password property="password2"></html:password>
									&nbsp;
									<html:errors property="password2" />
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="join.firstname" />
								</td>
								<td align="left">
									&nbsp;
									<html:text property="firstName"></html:text>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="join.lastname" />
								</td>
								<td align="left">
									&nbsp;
									<html:text property="lastName"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:submit property="method">
										<bean:message key="join.button" />
									</html:submit>
								</td>
								<td align="center">
									<html:submit property="method">
										<bean:message key="join.cancel" />
									</html:submit>
								</td>
							</tr>
						</table>
					</html:form>
				</td>
			</tr>
			<%@ include file="/Jsp/Base/footer.jsp"%>
		</table>
	</body>
</html>
