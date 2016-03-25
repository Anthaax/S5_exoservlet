package fr.intech.s5.appusers.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import fr.intech.s5.appusers.models.ModelImp;

public class ConH {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("appuser");
	private static EntityManager em = emf.createEntityManager();
	
	public static ModelImp getModel()
	{
		return new ModelImp(em);
	}
	
	/*public static void closeConnection()
	{
		em.close();
	}*/
}
