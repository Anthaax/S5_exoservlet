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
	public void modifyUserTest(){
		IModel model = new ModelImp(em);
		User user=model.selectUserById(1);
		user.setNom("nouveauJoris");
		model.modifyUser(user);
		User user2=model.selectUserById(1);
		assertEquals("nouveauJoris",user2.getNom());
	}
	@Test
	public void modifyTelephoneTest(){
		IModel model = new ModelImp(em);
		Telephone tel=model.selectTelephone(1);
		tel.setTelPortable("0101010101");
		model.modifyTelephone(tel);
		Telephone tel2=model.selectTelephone(1);
		assertEquals("0101010101",tel2.getTelPortable());
	}
	@Test
	public void selectAllUser()
	{
		IModel model = new ModelImp(em);
		
		Collection<User> user = model.selectAllUser();
		assertEquals(1,user.size());
	}
	
	@Test
	public void selectUserByIDTest()
	{
		IModel model = new ModelImp(em);
		
		User user = model.selectUserById(1);
		assertEquals("Joris", user.getPrenom());
	}
	
	@Test
	public void selectTelephoneByUserIDTest()
	{
		IModel model = new ModelImp(em);
		
		Telephone telephone = model.selectTelephone(1);
		assertEquals(1, telephone.getUserID().getId());
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
		
		Telephone telephone = model.selectTelephone(1);		
		assertEquals(true, model.deleteTelephone(telephone));
		
		em.getTransaction().begin();
		
		Telephone tel = new Telephone(telephone.getTelFix(), telephone.getTelPortable(), telephone.getUserID());
		
		em.persist(tel);
		
		em.getTransaction().commit();
		
	}
	@Test
	public void deleteUserTest()
	{
		IModel model = new ModelImp(em);
		
		User user = model.selectUserById(1);
		Telephone telephone = model.selectTelephone(1);
		assertEquals(true, model.deleteUser(user));
		
		/*em.getTransaction().begin();
		User u = new User(user.getNom(),user.getPrenom(), user.getEmail(), user.getLogin(), user.getPassword());
		Telephone tel = new Telephone(telephone.getTelFix(), telephone.getTelPortable(), telephone.getUserID());
		
		em.persist(u);
		
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		
		em.persist(tel);
		
		em.getTransaction().commit();*/
	}
	
	@AfterClass
	public static void CloseConnection()
	{
		em.close();
		emf.close();
	}
}
