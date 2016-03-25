package fr.intech.s5.appusers.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.intech.s5.appusers.models.Model;

/**
 * 
 * @author Joris
 *
 */
public class Servlet {

	//Contructeur pour eviter la fatigue de Sonar!!
    private Servlet()
    {
    	//Rien a mettre
    }
    
	public static void checkAndRedirect(HttpServletRequest request, HttpServletResponse response, Object object, String pageName)
	{
		try {
			if(object == null)
			{
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
			else
				request.getRequestDispatcher("/WEB-INF/"+pageName).forward(request, response);
		} catch (Exception e) {
			Model.printErr(e);
		}
	}
	
	public static void redirectTo(HttpServletRequest request, HttpServletResponse response, String pageName)
	{
		try {
			request.getRequestDispatcher("/WEB-INF/"+pageName).forward(request, response);
		} catch (Exception e) {
			Model.printErr(e);
		}
	}
}
