<form action="login.do" class="top_b_d_user_form" target="email">
	<div class="div-table login_table">
		<div class="div-table-row">
			<div class="div-table-col">
				<bean:message key="login.email" />
			</div>
			<div class="div-table-col">
				<input type="text" id="email" name="email" maxlength="15" size="15"
					value="" onchange="return checkEmail(this);"
					style="text-transform: uppercase">
			</div>
			<div class="div-table-col">
				<bean:message key="login.password" />
			</div>
			<div class="div-table-col">
				<input type="password" id="password" name="password" maxlength="15"
					size="15" value="" onchange="return checkNull(this);">
			</div>
			<div class="div-table-col">
				<input type="submit" name="method" id="method"
					value="<bean:message key="login.button.signon" />"
					onclick="validate(dataSource);">
			</div>
		</div>
		<div class="div-table-row">
			<div class="div-table-col">
				<input type="checkbox" id="loggedin" align="right">
			</div>
			<div class="div-table-col">
				Keep me logged in
			</div>
			<div class="div-table-col">
				<a href="">Forgot your password?</a>
			</div>
			<div class="div-table-col">
				<input type="submit" name="method" id="method"
					value="<bean:message key="join.button" />"
					onclick="validate(dataSource);">
				<input type="hidden" name="org.apache.struts.taglib.html.TOKEN"
					id="org.apache.struts.taglib.html.TOKEN"
					value="<%=session.getAttribute(org.apache.struts.Globals.TRANSACTION_TOKEN_KEY)%>" />
			</div>
		</div>
	</div>
</form>
