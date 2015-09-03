<table align="right" border="0">
	<tr>
		<td align="right">
			<bean:message key="login.email" />
		</td>
		<td align="right">
			&nbsp;
			<input type="text" id="email" name="email" maxlength="15" size="15"
				value="" onchange="return checkEmail(this);">
		</td>
		<td align="left">
			&nbsp;
			<bean:message key="login.password" />
		</td>
		<td align="left">
			&nbsp;
			<input type="password" id="password" name="password" maxlength="15"
				size="15" value="" onchange="return checkNull(this);">
		</td>
		<td align="center">
			<input type="submit" name="method" id="method"
				value="<bean:message key="login.button.signon" />"
				onclick="validate(dataSource);">
		</td>
	</tr>
	<tr>
		<td align="right">
			<input type="checkbox" id="loggedin" align="right">
		</td>
		<td align="right">
			&nbsp; Keep me logged in
		</td>
		<td align="left" colspan="2">
			&nbsp;
			<a href="">Forgot your password?</a>
		</td>
		<td align="center">
			<input type="submit" name="method" id="method"
				value="<bean:message key="join.button" />"
				onclick="validate(dataSource);">
		</td>
	</tr>
</table>