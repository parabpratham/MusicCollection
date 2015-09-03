<%@ include file="taglibs.jsp"%>

<html:html locale="true">
<head>
	<!-- begin the header for the application -->
	<title><tiles:getAsString name="title" />
	</title>
</head>
<body>
	<!-- begin the logo for the application -->
	<tiles:insert attribute='logo' />
	<!-- begin the body -->
	<tiles:insert attribute='body' />
	<!-- begin the footer for the application -->
	<tiles:insert attribute='footer' />
</body>
</html:html