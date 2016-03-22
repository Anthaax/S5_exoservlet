package fr.intech.s5.appusers.controllers;

import java.io.IOException;
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
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		
		User user = new User();
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(password);
		
		if(Model.addUser(user))
		{
			request.setAttribute("message", "Vous avez été bien inscrit.");
		}
		else {
			request.setAttribute("message", "Une erreur est survenue lors de l'inscription.");
		}
		
		
		
		try {
			request.getRequestDispatcher("/WEB-INF/resultInscription.jsp").forward(request, response);
		} catch (Exception e) {
			Model.printErr(e);
		}
		
	}

}
