<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@page import="com.c2s.music.user.form.UserForm"%><%@ include
	file="/Jsp/Base/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<%@ include file="/Jsp/Base/title.jsp"%>
	<script language="javascript"
		src="<%=request.getContextPath()%>/Jsp/Scripts/playermouseajax.js"> </script>
	<%
		String path = request.getContextPath();
		UserForm userForm = null;
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
				<div id="cont_b_rtb_adds_d">
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
				</div>
			</div>
			<div id="cont_bo_ltb_d">
				<div id="cont_bo_ltb_adds_d">
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
				<%@ include file="/Jsp/Albums/listalbums.jsp"%>
				<div id="footer_d" class="footer_d">
					<%@ include file="/Jsp/Base/div_footer.jsp"%>
				</div>
			</div>
		</div>
		<div id="cont_bo_ltb_player_d"
			style="visibility: visible; width: 300px">
			<div id="cont_bo_ltb_playlist_d div-table">
				<div class="div-table-row" id="cont_bo_ltb_playlist_item_d">
					<div class="div-table-col">
						<ol id="cont_bo_ltb_playlist_item_ol">
						</ol>
					</div>
				</div>
				<div class="div-table-row">
					<div id="cont_bo_ltb_playerwindow_d" class="div-table-col">
						<img id="top_b_d_logo_img"
							src="<%=request.getContextPath()%>/Images/300x250_radio_2.gif"
							name="webLogo" />
						<!-- 
						<div id="playerwindow_play">
						</div>
						<div id="playerwindow_pause">
						</div>
						<div id="playerwindow_next">
						</div>
						<div id="playerwindow_prev">
						</div>
						<div id="playerwindow_shuffle">
						</div>
						<div id="playerwindow_replay">
						</div>
						 -->
					</div>
				</div>
			</div>
	</body>
</html>
