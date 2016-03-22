package fr.intech.project.application;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.models.IModel;
import fr.intech.s5.appusers.models.ModelImp;

public class ModelImpTest {

	@BeforeClass
	public static void init() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("appuser");
		EntityManager em = emf.createEntityManager();
		String nom = "NSENGUET TOSSAM";
		String prenom = "Joris";
		String email = "nsenguetjoris@gmail.com";
		String login = "deviok";
		String password = "toto";
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		
		User user4 = new User(nom, prenom, email, login, password);
		
		em.persist(user4);
				
		et.commit();
		
		em.close();
		emf.close();		
	}
	
	@Test
	public void selectAllUser()
	{
		/*IModel model = new ModelImp();
		
		Collection<User> user = model.selectAllUser();
		assertEquals(1,user.size());*/

	}
}
