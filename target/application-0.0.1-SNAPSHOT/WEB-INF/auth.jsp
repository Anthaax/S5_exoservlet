<!DOCTYPE html>
<%@page import="fr.intech.s5.appusers.beans.User"%>
<html lang="en">
<head>
<%@ include file="head.jsp"%>
<title>Profil</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<h1>Profil</h1>
		<hr>
		<br>
		<%User user = (User)request.getAttribute("user"); %>
		<div class="formInscription">
			<form class="form-horizontal" action="#" method="POST">
				<input type="hidden" class="form-control" id="id" name="id" value="<%out.print(user.getId()); %>" required>
				
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="nom">Nom* :</label>
					<div class="col-md-5">
						<input type="text" disabled class="form-control" id="nom" name="nom" placeholder="Nom" value="<%out.print(user.getNom()); %>" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="prenom">Prénom* :</label>
					<div class="col-md-5">
						<input type="text" disabled class="form-control" id="prenom" name="prenom" value="<%out.print(user.getPrenom()); %>" placeholder="Prénom" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="dateNaiss">Date Naiss.* :</label>
					<div class="col-md-5">
						<input type="text" disabled class="form-control" id="dateNaiss" name="dateNaiss" value="<%out.print(user.getDateNaiss().toString()); %>" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="email">Email* :</label>
					<div class="col-md-5">
						<input type="email" disabled class="form-control" id="email" name="email" value="<%out.print(user.getEmail()); %>" placeholder="Adresse email" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="login">Login* :</label>
					<div class="col-md-5">
						<input type="text" disabled class="form-control" id="login" name="login" placeholder="Pseudo" value="<%out.print(user.getLogin()); %>" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="password">Password* :</label>
					<div class="col-md-5">
						<input type="password" disabled class="form-control" id="password" name="password" placeholder="Mot de passe" value="<%out.print(user.getPassword()); %>" required>
					</div>
				</div>
				<br>
				<div class="form-group">
					<div class="col-md-3 col-md-offset-5">
						<button type="submit" class="btn btn-info btn-lg btn-block">Modifier</button>
					</div>
				</div>
			</form>
		</div>
		<br>
	</div>
	<!-- /.container -->
	<%@ include file="loadScript.jsp"%>
</body>
</html>