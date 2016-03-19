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
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
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
		
		String pseudo = (String)request.getParameter("pseudo");
		String password = (String)request.getParameter("password");
		
		try {
			if(Model.isUser(pseudo, password))
			{
				User user = Model.getUser(pseudo, password);
				request.setAttribute("user", user);
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
