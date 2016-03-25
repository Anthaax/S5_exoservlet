package fr.intech.s5.appusers.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import fr.intech.s5.appusers.models.ModelImp;
/**
 * 
 * @author Joris
 *
 */
public class ConH {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("appuser");
	private static EntityManager em = emf.createEntityManager();
	
	//Contructeur pour eviter la fatigue de Sonar!!
    private ConH()
    {
    	//Rien a mettre
    }
    
	public static ModelImp getModel()
	{
		return new ModelImp(em);
	}
}
