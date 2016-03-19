package fr.intech.s5.appusers.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.models.Model;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet(name="InscriptionServlet", urlPatterns = "/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
		} catch (Exception e) {
			Model.printErr(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String dateNaiss = request.getParameter("dateNaiss");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String message = "";
		
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final LocalDate localDate = LocalDate.parse(dateNaiss, dtf);
		
		User user = new User();
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(password);
		user.setDateNaiss(localDate);
		
		
		if(Model.addUser(user))
		{
			message = "Vous avez été bien inscrit.";
		}
		else {
			message = "Une erreur est survenue lors de l'inscription.";
		}
		
		request.setAttribute("message", message);
		
		try {
			request.getRequestDispatcher("/WEB-INF/resultInscription.jsp").forward(request, response);
		} catch (Exception e) {
			Model.printErr(e);
		}
		
	}

}
