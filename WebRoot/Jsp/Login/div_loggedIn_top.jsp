<form action="login.do">
	<div class="div-table">
		<div class="div-table-row">
			<div class="div-table-col">
				Welcome
				<%=userForm.getFirstName()%>
				<%=userForm.getLastName()%>
			</div>
			<div class="div-table-col">
				<input type="submit" name="method" id="method"
					value="<bean:message key="login.logoff" />"
					onclick="validate(dataSource);">
				<input type="hidden" name="org.apache.struts.taglib.html.TOKEN"
					id="org.apache.struts.taglib.html.TOKEN"
					value="<%=session.getAttribute(org.apache.struts.Globals.TRANSACTION_TOKEN_KEY)%>" />
			</div>
		</div>
		<div class="div-table-row">
			<div class="div-table-col">
				<%=userForm.getLastLogin()%>
			</div>
			<div class="div-table-col">
				<a href="">Forgot your password?</a>
			</div>
		</div>
	</div>
</form>