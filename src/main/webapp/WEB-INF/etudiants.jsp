<!DOCTYPE html>
<%@page import="fr.intech.s5.appusers.services.ConH"%>
<%@page import="fr.intech.s5.appusers.models.IModel"%>
<%@page import="java.util.Collection"%>
<html lang="en">
<head>
<%@ include file="head.jsp"%>
<title>Liste des utilisateurs</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<div class="starter-template">
			<h1>Liste des utilisateurs inscrits</h1>
			<hr>
			<br>

			<div class="formInscription2">
				<table class="table table-hover">
					<thead>
						<th>Nom</th>
						<th>Prénom</th>
						<th>Email</th>
						<th>Tel. Portable</th>
						<th>Tel. Fixe</th>
						<th>Login</th>
						<th>Supp.</th>
					<thead>
					<tbody>
						<% IModel model = ConH.getModel();
							Collection<User> users = (Collection<User>)request.getAttribute("users"); 
							for(User u : users)
							{
								Telephone t = model.selectTelephone(u.getId());
								out.print("<tr>");
								out.println("<td>"+u.getNom()+"</td>");
								out.println("<td>"+u.getPrenom()+"</td>");
								out.println("<td>"+u.getEmail()+"</td>");
								out.println("<td>"+t.getTelPortable()+"</td>");
								out.println("<td>"+t.getTelFix()+"</td>");
								out.println("<td>"+u.getLogin()+"</td>");
								if(user != null && user.getLogin().equals("admin"))out.println("<td><form action=\"suppression\" method=\"POST\"><input type=\"text\" hidden name = \"id\" value=\""+u.getId()+"\"/><button type=\"submit\" class=\"btn btn-danger btn-xs\" aria-label=\"Top Align\"><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span> </button></a></form></td>");
								out.print("</tr>");
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- /.container -->
	<%@ include file="loadScript.jsp"%>
</body>
</html>