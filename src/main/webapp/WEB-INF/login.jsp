<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="head.jsp"%>
<title>Connexion</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<h1>Connexion</h1>
		<hr>
		<%if(user == null) {%>
		<form class="form-signin" action="login" method="POST">
			<h2 class="form-signin-heading">Connectez vous...</h2>
			<label for="pseudo" class="sr-only">Pseudo</label> <input type="text" id="pseudo" class="form-control" name = "pseudo" placeholder="Pseudo" required autofocus> <label
				for="password" class="sr-only">Password</label> <input
				type="password" id="password" name = "password" class="form-control"
				placeholder="Password" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Se souvenir de moi...
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>
			<br>
			<p><a href="inscription">S'inscrire ?</a></p>
		</form>
		<%} else{ %>
		<h2 class="form-signin-heading">Vous actuellement connecté(e).</h2>
		<%} %>
		<br>
		<p id="msgErrorLogin"><% if(request.getAttribute("message") != null) out.println(request.getAttribute("message")); %></p>
	</div>
	<!-- /.container -->
	<%@ include file="loadScript.jsp"%>
</body>
</html>