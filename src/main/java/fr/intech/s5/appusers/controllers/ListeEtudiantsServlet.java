package fr.intech.s5.appusers.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.models.IModel;
import fr.intech.s5.appusers.models.Model;
import fr.intech.s5.appusers.services.ConH;

/**
 * Servlet implementation class InscriptionServlet
 */
/**
 * 
 * @author Joris
 *
 */
@WebServlet(name="ListeEtudiantsServlet", urlPatterns = "/etudiants")
public class ListeEtudiantsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final transient IModel model;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeEtudiantsServlet() {
        super();
        model = ConH.getModel();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	Collection<User> users = model.selectAllUser();
    	request.setAttribute("users", users);
    	
    	try {
			request.getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);
		} catch (Exception e) {
			Model.printErr(e);
		}
		
	}

}
