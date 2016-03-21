package fr.intech.project.application;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.models.IModel;
import fr.intech.s5.appusers.models.Model;
import fr.intech.s5.appusers.models.ModelImp;
import junit.framework.Assert;

public class ModelImpTest {

	@BeforeClass
	public static void init() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("emarket");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		String nom = "NSENGUET TOSSAM";
		String prenom = "Joris";
		String email = "nsenguetjoris@gmail.com";
		String login = "deviok";
		String password = "toto";
		Date dateNaiss = Date.valueOf(LocalDate.of(2016, 3, 16));
		User user = new User(nom, prenom, email, login, password, dateNaiss);
		User user2 = new User(nom, prenom, email, login, password, dateNaiss);
		
		em.persist(user2);
				
		et.commit();
		
		em.close();
		emf.close();		
	}
	
	@Test
	public void selectAllUser()
	{
		IModel model = new ModelImp();
		
		Collection<User> user = model.selectAllUser();
		assertEquals(1,user.size());

	}
}
