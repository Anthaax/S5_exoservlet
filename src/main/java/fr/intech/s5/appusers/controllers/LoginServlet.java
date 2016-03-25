package fr.intech.s5.appusers.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.intech.s5.appusers.beans.Telephone;
import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.models.IModel;
import fr.intech.s5.appusers.models.Model;
import fr.intech.s5.appusers.services.ConH;

/**
 * Servlet implementation class LoginServlet
 */
/**
 * 
 * @author Joris
 *
 */
@WebServlet(name="LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final transient IModel model;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        model = ConH.getModel();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMsg = "";
		request.setAttribute("message", errorMsg);
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
		
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		try {
			if(model.selectUserByLoginAndPassword(pseudo, password) != null)
			{
				User user = model.selectUserByLoginAndPassword(pseudo, password);
				Telephone tel = model.selectTelephone(user.getId());
				session.setAttribute("usersession", user);
				session.setAttribute("telsession", tel);
				request.getRequestDispatcher("/WEB-INF/auth.jsp").forward(request, response);
			}
			else
			{
				String errorMsg = "Pseudonyme ou mot de passe erroné.";
				request.setAttribute("message", errorMsg);
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			Model.printErr(e);
		}
	}

}
