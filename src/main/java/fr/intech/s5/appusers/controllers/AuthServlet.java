package fr.intech.s5.appusers.controllers;

import java.io.IOException;
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
 * Servlet implementation class AuthServlet
 */
@WebServlet(name="AuthServlet", urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("usersession");
		
		try {
			if(user == null)
			{
				request.setAttribute("message", "");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
			else
				request.getRequestDispatcher("/WEB-INF/auth.jsp").forward(request, response);
		} catch (Exception e) {
			Model.printErr(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (Exception e) {
			Model.printErr(e);
		}
		
	}

}
