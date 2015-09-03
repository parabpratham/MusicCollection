<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%
			String path = request.getContextPath();
		%>
		<title>Using multiple XMLHttpRequest objects</title>

		<style type="text/css">
.television {
	position: absolute;
	z-index: 200;
	background: #FF0000;
	color: #0000FF;
}

#target {
	position: absolute;
	background: #00FF00;
	color: #000000;
}
</style>

		<script language="javascript" src="<%=path%>/Jsp/Scripts/ajax.js"> </script>
		<script language="javascript"
			src="<%=path%>/Jsp/Scripts/libXmlRequest.js"> </script>
		<script language="javascript" src="<%=path%>/Jsp/Scripts/mouseajax.js"> </script>
		<script language="javascript" src="<%=path%>/Jsp/Scripts/XHConn.js"> </script>

	</head>
	<!-- <body onload="evaluate();"> -->
	<body>
		<hr>
		<h1>
			Buy a television by dragging it to the shopping cart
		</h1>
		<div id="targetDiv5"></div>
		<div id="television1" class="television"
			style="left: 50px; top: 150px; width: 80px; height: 80px;"
			onmousedown="handleDown(event,'<%=path%>');">
			Television1
		</div>
		<div id="television2" class="television"
			style="left: 550px; top: 150px; width: 80px; height: 80px;"
			onmousedown="handleDown(event,'<%=path%>');">
			Television2
		</div>
		<div id="target"
			style="left: 300px; top: 700px; width: 200px; height: 100px;">
			Shopping Cart
		</div>

	</body>
</html>