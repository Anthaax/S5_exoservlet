<!-- Auteur Nicolas -->

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
		<div class="formInscription">
			<form class="form-horizontal" action="update" method="POST">
				<input type="hidden" class="form-control" id="id" name="id" value="<%out.print(user.getId()); %>" required>
				
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="nom">Nom* :</label>
					<div class="col-md-5">
						<input type="text" class="form-control" id="nom" name="nom" placeholder="Nom" value="<%out.print(user.getNom()); %>" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="prenom">Prénom* :</label>
					<div class="col-md-5">
						<input type="text" class="form-control" id="prenom" name="prenom" value="<%out.print(user.getPrenom()); %>" placeholder="Prénom" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="email">Email* :</label>
					<div class="col-md-5">
						<input type="email" class="form-control" id="email" name="email" value="<%out.print(user.getEmail()); %>" placeholder="Adresse email" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="telport">Tel. Portable* :</label>
					<div class="col-md-5">
						<input type="text" class="form-control" id="telport" name="telport" value="<%out.print(telephone.getTelPortable()); %>" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="telfix">Tel. Fixe :</label>
					<div class="col-md-5">
						<input type="text" class="form-control" id="telfix" name="telfix" value="<%out.print(telephone.getTelPortable()); %>">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 col-md-offset-2 control-label" for="login">Login* :</label>
					<div class="col-md-5">
						<input type="text" class="form-control" id="login" name="login" placeholder="Pseudo" value="<%out.print(user.getLogin()); %>" required>
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