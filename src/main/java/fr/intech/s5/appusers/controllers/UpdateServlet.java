package fr.intech.s5.appusers.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.intech.s5.appusers.beans.Telephone;
import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.models.IModel;
import fr.intech.s5.appusers.models.Model;
import fr.intech.s5.appusers.services.ConH;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet(name="UpdateServlet", urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IModel model;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    			
		} catch (Exception e) {
			Model.printErr(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	model = ConH.getModel();
      	 
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		
		String telFix = request.getParameter("telfix");
		String telport = request.getParameter("telport");
		
		User user = (User)request.getSession().getAttribute("usersession");
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setEmail(email);
		user.setLogin(login);
		
		Telephone tel = (Telephone)request.getSession().getAttribute("telsession");
		tel.setTelFix(telFix);
		tel.setTelPortable(telport);
		
		try {
			if(model.modifyUser(user) && model.modifyTelephone(tel))
			{
				request.setAttribute("message", "La mise à jour s'est bien déroulée.");
			}
			else
				request.setAttribute("message", "Echec lors de la mise à jour.");
			
			request.getRequestDispatcher("/WEB-INF/updateResult.jsp").forward(request, response);
		} catch (Exception e) {
			Model.printErr(e);
		}
    	
	}

}
