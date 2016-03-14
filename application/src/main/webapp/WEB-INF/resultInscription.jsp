<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="head.jsp"%>
<title>Etat inscription</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<h1><% out.println(request.getAttribute("message")); %></h1>
		<hr>
		
		<br>
	</div>
	<!-- /.container -->
	<%@ include file="loadScript.jsp"%>
</body>
</html>