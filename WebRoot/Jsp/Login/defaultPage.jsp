<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<%
			String path = request.getContextPath();
		%>
	</head>
	<style type="text/css">
body {
	padding: 0px;
	background: #FFF;
	margin-left: 50px;
	margin-right: 50px;
}

#container {
	
}

#level0 {
	background: #FC0;
	width: 100%;
}

#level1 {
	margin-left: 143px;
	padding-left: 9px;
	background: #FFF;
}

#level2 {
	background: #FFF;
	position: relative;
	width: inherit;
}

#main {
	margin-right: 9em;
	border-right: 1px solid #FC0;
	padding-right: 9px;
	background: #FFF;
}

#topBar {
	background: #FFF;
}

#advBar {
	background: #FC0;
	clear: right;
}

#tipDay {
	float: right;
	width: 175px;
	background: #FFF3AC;
	position: relative;
}

#lftBar {
	position: inherit;
	float: left;
	width: 143px;
	top: 9px;
	left: 9px;
	width: 143px;
}

#rgtBar {
	position: inherit;
	float: right;
	width: 9em;
	top: 0;
	right: 0;
}

#main h1 {
	margin: 0;
	padding-left: .3em;
	font: 1.25em Verdana, Helvetica, Arial;
	color: #609;
	background: #FC0;
}

#main dt {
	font-weight: bold;
	font-size: 120%;
	margin-top: .8em
}

#rgtBar h3 {
	margin: 0;
	padding: 3px;
	background: #FC0;
	font-weight: bold;
	font-size: 1em;
	text-align: center;
}

#tipTit {
	float: right;
	width: 7em;
	text-align: right;
	position: relative;
	font: .8em/ 1.8em Arial, Geneva, sans-serif;
	background: red;
}

.note {
	color: red
}

#topBar form {
	float: right;
	width: 10.5em;
	text-align: right;
	margin: 0;
	line-height: .7em;
}

#topBar p {
	text-align: right;
	margin: 0 0.5em;
	font-weight: bold;
}

#advBar img {
	margin-bottom: 1em;
}

#advBar p {
	text-align: right;
	margin: 0 0.5em;
	font-weight: bold;
}

#advBar form {
	float: right;
	width: 10.5em;
	text-align: right;
	margin: 0;
	line-height: .7em;
}

#topBar input {
	font-size: .8em;
}

#topBar b {
	display: block;
	text-align: left;
	font: bold .8em/ 1.7em Arial, Geneva, sans-serif;
}

.stip {
	text-align: center;
	padding: 5px;
	width: inherit;
}

.div-table {
	display: table;
	border: 1px solid #666666;
	border-spacing: 5px; /*cellspacing:poor IE support for  this*/
	position: relative;
	margin: 10px;
	width: 100%;
}

.div-table-row {
	display: table-row;
	clear: both;
	margin: 10px;
	width: 100%;
}

.div-table-col {
	float: left; /*fix for  buggy browsers*/
	display: table-column;
	background-color: #ccc;
	margin: 10px;
	width: 100%;
}
</style>
	<body>
		<div id="lftBar">
			The WebReference logo goes on the top. Some other boxes with news and
			advertisement follows and then text links to Developer.com.
		</div>
		<div id="level0">
			<div id="level1">
				<div id="topBar">
					<p>
						<%@ include file="login_top.jsp"%>
					</p>
					<!-- START AD -->
					<img src="<%=path%>/Images/AlbumImage/album2.jpg" width="80"
						height="50" />
					<!-- END AD -->
					<div id="advBar">
						asds
					</div>
				</div>
				<div id="level2">
					<div id="rgtBar">
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
					<div id="level3">
						<div id="main">
							<div id="tipTit">
								Tip of the Day
							</div>
							<h1>
								WebReference: Dev the Web&#153;
							</h1>
							<div id="tipDay">
								This is the "Tip of the day" box.
							</div>
							<dl>
								<dt>
									<a href="#">HTTP for HTML Authors, Part III</a>
								</dt>
								<dd>
									We examine two of the most important functions of HTTP that
									HTML authors will be interested in: redirecting and form
									submission. By Stephanos Piperoglou. 0316
								</dd>
								<dt>
									<a href="#">HTTP for HTML Authors, Part III</a>
								</dt>
								<dd>
									We examine two of the most important functions of HTTP that
									HTML authors will be interested in: redirecting and form
									submission. By Stephanos Piperoglou. 0316
								</dd>
							</dl>
							<h1>
								This is the main2.
							</h1>
							<dl>
								<dt>
									<a href="#">HTTP for HTML Authors, Part III</a>
								</dt>
								<dd>
									We examine two of the most important functions of HTTP that
									HTML authors will be interested in: redirecting and form
									submission. By Stephanos Piperoglou. 0316
								</dd>
								<dt>
									<a href="#">HTTP for HTML Authors, Part III</a>
								</dt>
								<dd>
									We examine two of the most important functions of HTTP that
									HTML authors will be interested in: redirecting and form
									submission. By Stephanos Piperoglou. 0316
								</dd>
							</dl>
							And the HTML code looks like this:
							<br>
							<br>
							<div class="stip">
								<div class="div-table">
									<%
										for (int i = 1; i <= 2; i++)
										{
									%><div class="div-table-row">
										<%
											for (int j = 1; j <= 5; j++)
												{
										%>
										<div class="div-table-col">
											<div id="<%=i * j%>"
												style="padding-top: 0px; padding-bottom: 0px;">
												<img src="<%=path%>/Images/AlbumImage/album<%=i * j%>.jpg"
													width="80" height="50">
											</div>
										</div>
										<%
											}
										%>
									</div>

									<%
										}
									%>
								</div>
							</div>
							<div class="stip">
								<div class="div-table">
									<%
										for (int i = 1; i <= 2; i++)
										{
									%><div class="div-table-row">
										<%
											for (int j = 1; j <= 5; j++)
												{
										%>
										<div class="div-table-col">
											<div id="<%=i * j%>"
												style="padding-top: 0px; padding-bottom: 0px;">
												<img src="<%=path%>/Images/AlbumImage/album<%=i * j%>.jpg"
													width="80" height="50">
											</div>
										</div>
										<%
											}
										%>
									</div>

									<%
										}
									%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>