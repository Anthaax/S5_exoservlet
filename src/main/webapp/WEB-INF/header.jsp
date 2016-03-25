<%@page import="fr.intech.s5.appusers.beans.Telephone"%>
<%@page import="fr.intech.s5.appusers.beans.User"%>
<%User user = (User)request.getSession().getAttribute("usersession");%>
<%Telephone telephone = (Telephone)request.getSession().getAttribute("telsession");%>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="login">App Users</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="login">Home</a></li>
				<%if(user == null){ %><li><a href="inscription">Inscription</a></li><%} %>
				<%if(user != null){ %><li><a href="auth">Profil</a></li><%} %>
				<li><a href="etudiants">Liste étudiants</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
<% 
if(user != null)
{
 %>
<div class="row navbar-fixed-top space">
	<div class="col-xs-8"></div>
	<div class="col-xs-2 welcome"><b>Bienvenue <%out.print(user.getLogin());%>!</b></div>
	<form class="form-horizontal" action="deconnexion" method="POST">
		<div class="form-group welcome">
			<div class="col-xs-2">
				<button type="submit" class="btn btn-danger btn-md btn-block">Déconnexion</button>
			</div>
		</div>
	</form>
</div>
<%}%>