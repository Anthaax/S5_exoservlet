package fr.intech.project.application;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.intech.s5.appusers.beans.Telephone;
import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.models.IModel;
import fr.intech.s5.appusers.models.ModelImp;

public class ModelImpTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	@BeforeClass
	public static void init() {
		
		emf = Persistence.createEntityManagerFactory("appuser");
		em = emf.createEntityManager();
		String nom = "NSENGUET TOSSAM";
		String prenom = "Joris";
		String email = "nsenguetjoris@gmail.com";
		String login = "deviok";
		String password = "toto";
		EntityTransaction et = em.getTransaction();
		
		String telfix = "0662147351";
		String telPortable = "0606060606";
		
		et.begin();
		
		
		User user4 = new User(nom, prenom, email, login, password);
		Telephone tel = new Telephone(telfix, telPortable, user4);
		
		em.persist(user4);
		em.persist(tel);
				
		et.commit();	
	}
	
	@Test
	public void selectAllUser()
	{
		IModel model = new ModelImp(em);
		
		Collection<User> user = model.selectAllUser();
		assertEquals(1,user.size());
	}
	
	@Test
	public void selectUserByID()
	{
		IModel model = new ModelImp(em);
		
		User user = model.selectUserById(1);
		assertEquals("Joris", user.getPrenom());
	}
	
	@Test
	public void selectTelephoneByID()
	{
		IModel model = new ModelImp(em);
		
		Telephone telephone = model.selectTelephone(2);
		assertEquals(2, telephone.getId());
	}
	
	@Test
	public void selectUserByLoginAndPassword()
	{
		IModel model = new ModelImp(em);
		
		User user = model.selectUserByLoginAndPassword("deviok", "toto");
		assertEquals("Joris", user.getPrenom());
	}
	
	@Test
	public void deleteTelephoneTest()
	{
		IModel model = new ModelImp(em);
		
		Telephone telephone = model.selectTelephone(2);		
		assertEquals(true, model.deleteTelephone(telephone));
		
		em.getTransaction().begin();
		
		em.persist(telephone);
		
		em.getTransaction().commit();
		
	}
	
	@AfterClass
	public static void CloseConnection()
	{
		em.close();
		emf.close();
	}
}
