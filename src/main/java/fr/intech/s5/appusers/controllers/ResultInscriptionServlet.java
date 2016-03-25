package fr.intech.s5.appusers.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.intech.s5.appusers.services.Servlet;

/**
 * Servlet implementation class ResultInscriptionServlet
 */
/**
 * 
 * @author Joris
 *
 */
@WebServlet(name="ResultInscriptionServlet", urlPatterns = "/resultinscription")
public class ResultInscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultInscriptionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = (String) request.getAttribute("message");
		
		Servlet.checkAndRedirect(request, response, message, "resultInscription.jsp");
		
	}

}
