<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@page import="com.c2s.music.user.form.UserForm"%>


<%@ include file="/Jsp/Base/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<%@ include file="/Jsp/Base/title.jsp"%>
	<%
		String path = request.getContextPath();
		UserForm userForm = null;
		Object errorMessage = request.getAttribute("error");
		Object attribute = request.getAttribute("user");
		boolean isUserPesent = false;
		if (attribute != null)
		{
			userForm = (UserForm) attribute;
			isUserPesent = true;
		}
	%>
	<body>
		<div id="top_b_d" class="top_b_d">
			<%@ include file="/Jsp/Base/div_logo.jsp"%>
			<div id="top_b_d_user">
				<%
					if (!isUserPesent)
					{
				%>
				<%@ include file="/Jsp/Login/div_login_top.jsp"%>
				<%
					}
					else
					{
				%>
				<%@ include file="/Jsp/Login/div_loggedIn_top.jsp"%>
				<%
					}
				%>
			</div>
		</div>
		<div id="cont_bo_d">
			<div id="cont_bo_rtb_d">
				<h3>
					<a href="#">Newsletters</a>
				</h3>
				Here goes a form to suscribe to the WebReference newsletter.
				<h3>
					<a href="#">Experts</a>
				</h3>
				Text links for this section.
				<h3>
					<a href="#">Sitemap</a>
				</h3>
				Text links for this section.
				<h3>
					<a href="#">Reference</a>
				</h3>
				Text links for this section.
				<h3 class="note">
					A_Long_String
				</h3>
			</div>
			<div id="cont_bo_ltb_d">

				<dl>
					<dt>
						Menu
					</dt>
					<dd>
						Menu and links will apeear here
					</dd>
					<dt>
						categories
					</dt>
					<dd>

					</dd>
					<dt>
						Adv
					</dt>
					<dd>
						adv
					</dd>
				</dl>

			</div>
			<br>
			<br>
			<div id="cont_bo_main_d">
				<div id="cont_bo_main_search_d">
					<form action="">
						<input type="text">
						<input type="button" value="Search">
						<br>
						Top Searches: umbrella fashion sale decor carnival Sarees pen
						drives
						<br>
						<br>
						<div id="cont_bo_main_adv_d" align="center">
							<img src="<%=path%>/Images/728x90_radio_2.gif"></img>
						</div>
					</form>
				</div>
				<br>
				Critical Error Occured :
				<br>
				<%=errorMessage != null ? errorMessage : ""%>
				<a
					href="http://localhost:11090/MusicCollection/home.do?method=homePageList">Back</a>
				<div id="footer_d" class="footer_d">
					<%@ include file="/Jsp/Base/div_footer.jsp"%>
				</div>
	</body>
</html>
