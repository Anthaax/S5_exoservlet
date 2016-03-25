package fr.intech.s5.appusers.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.intech.s5.appusers.models.Model;
/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet(name="UpdateResultServlet", urlPatterns = "/updateresult")
public class UpdateResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateResultServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String message = (String) request.getAttribute("message");
		try {
			if(message == null)
			{
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
			else
				request.getRequestDispatcher("/WEB-INF/updateResult.jsp").forward(request, response);
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
