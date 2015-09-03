
<div align="center">
	<div align="center">
		<html:errors property="login" />
	</div>
	<table align="center">
		<tr align="center">
			<td align="right">
				<bean:message key="login.email" />
			</td>
			<td align="left">
				&nbsp;
				<input type="text" id="email" name="email" maxlength="15" size="15"
					value="" onchange="return checkEmail(this)">
				&nbsp;
				<html:errors property="email" />
				<div id="emailerror" style="display: none; font-size: -1;" />
			</td>

		</tr>
		<tr align="center">
			<td align="right">
				<bean:message key="login.password" />
			</td>
			<td align="left">
				&nbsp;
				<input type="password" id="password" name="password" maxlength="15"
					size="15" value="">
				<html:errors property="password" />
				<div id="passworderror" style="display: none; font-size: -1;" />
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<input type="submit" name="method" id="method"
					value="<bean:message key="login.button.signon" />"
					onclick="validate(dataSource);">
				or
				<a href="<%=path%>/home.do?method=<bean:message key="join.button"/>">
					<bean:message key="join.button" /> </a>
			</td>
		</tr>
		<tr align="center">
			<td align="right">
				<input type="checkbox" id="loggedin" align="right">
			</td>
			<td align="left">
				&nbsp;
				<bean:message key="login.forgot.password" />
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				&nbsp;
				<a
					href="<%=path%>/home.do?method=<bean:message key="login.forgot.password.link"/>"><bean:message
						key="login.forgot.password" />
				</a>
			</td>
		</tr>
	</table>
	<div>