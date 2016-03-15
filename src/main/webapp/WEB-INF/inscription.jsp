<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="head.jsp"%>
<title>Inscription</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<h1>Formulaire d'inscription</h1>
		<hr>
		<br>
		<div class="formInscription">
			<form class="form-horizontal" action="inscription" method="POST">
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="nom">Nom* :</label>
					<div class="col-md-5">
						<input type="text" class="form-control" id="nom" name="nom" placeholder="Nom" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="prenom">Prénom* :</label>
					<div class="col-md-5">
						<input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="dateNaiss">Date Naiss.* :</label>
					<div class="col-md-5">
						<input type="text" class="form-control" id="dateNaiss" name="dateNaiss" value="12/02/1995" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="email">Email* :</label>
					<div class="col-md-5">
						<input type="email" class="form-control" id="email" name="email" placeholder="Adresse email" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="login">Login* :</label>
					<div class="col-md-5">
						<input type="text" class="form-control" id="login" name="login" placeholder="Pseudo" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="password">Password* :</label>
					<div class="col-md-5">
						<input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" required>
					</div>
				</div>
				<br>
				<div class="form-group">
					<div class="col-md-3 col-md-offset-5">
						<button type="submit" class="btn btn-success btn-lg btn-block">S'inscrire</button>
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