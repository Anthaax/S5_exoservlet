package fr.intech.project.application;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.models.Model;
import fr.intech.s5.appusers.services.MD5;

/**
 * 
 * @author Joris
 *
 */
public class ModelTest {

	
	@Before
	public void theBefore()
	{
		int id = 0;
		String nom = "NSENGUET TOSSAM";
		String prenom = "Joris";
		String email = "nsenguetjoris@gmail.com";
		String login = "deviok";
		String password = "toto";
		LocalDate dateNaiss = LocalDate.of(2016, 3, 16);
		User user = new User(id, nom, prenom, email, login, password, dateNaiss);
		
		Model.addUser(user);
	}
	
	@After
	public void theAfter()
	{
		Model.deleteTable();
	}
	
	@Test
	public void isUserTest()
	{
		assertEquals(true, Model.isUser("deviok", "toto"));
	}
	
	@Test
	public void addUserTest()
	{
		int id = 1;
		String nom = "PEUCH";
		String prenom = "Guillaume";
		String email = "gp@gmail.com";
		String login = "Clement";
		String password = "benoit";
		LocalDate dateNaiss = LocalDate.of(1995, 10, 18);
		User user = new User(id, nom, prenom, email, login, password, dateNaiss);
		
		assertEquals(true, Model.addUser(user));
		User userRecup = Model.getUser(login, password);
		assertNotNull(userRecup);
		assertEquals(nom, userRecup.getNom());
		assertEquals(prenom, userRecup.getPrenom());
		assertEquals(email, userRecup.getEmail());
		assertEquals(login, userRecup.getLogin());
		assertEquals(MD5.crypt(password), userRecup.getPassword());
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final LocalDate localDate = LocalDate.parse(userRecup.getDateNaiss().toString(), dtf);
		assertEquals(LocalDate.of(1995, 10, 18).toString(), localDate.toString());
	}
	
	@Test
	public void getUserTest()
	{
		User user = Model.getUser("deviok", "toto");
		
		assertNotNull(user);
		assertEquals("NSENGUET TOSSAM", user.getNom());
		assertEquals("Joris", user.getPrenom());
		assertEquals("nsenguetjoris@gmail.com", user.getEmail());
		assertEquals("deviok", user.getLogin());
		assertEquals(MD5.crypt("toto"), user.getPassword());
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final LocalDate localDate = LocalDate.parse(user.getDateNaiss().toString(), dtf);
		assertEquals(LocalDate.of(2016, 3, 16), localDate);
	}
	
	@Test
	public void getAllUsersTest()
	{
		int id = 1;
		String nom = "PEUCH";
		String prenom = "Guillaume";
		String email = "gp@gmail.com";
		String login = "Clement";
		String password = "benoit";
		LocalDate dateNaiss = LocalDate.of(1995, 10, 18);
		User user = new User(id, nom, prenom, email, login, password, dateNaiss);
		Model.addUser(user);
		
		assertEquals(2, Model.getAllUsers().size());
	}
	
	@Test
	public void getUserTestByID()
	{
		User user = Model.getUserWithID(0);
		
		assertNotNull(user);
		assertEquals("NSENGUET TOSSAM", user.getNom());
		assertEquals("Joris", user.getPrenom());
		assertEquals("nsenguetjoris@gmail.com", user.getEmail());
		assertEquals("deviok", user.getLogin());
		assertEquals(MD5.crypt("toto"), user.getPassword());
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final LocalDate localDate = LocalDate.parse(user.getDateNaiss().toString(), dtf);
		assertEquals(LocalDate.of(2016, 3, 16), localDate);
	}
	
	@Test
	public void deleteUserTest()
	{
		int id = 2;
		String nom = "Fimes";
		String prenom = "Guillaume";
		String email = "fg@gmail.com";
		String login = "guillaume";
		String password = "guillaume";
		LocalDate dateNaiss = LocalDate.of(1995, 10, 18);
		User user = new User(id, nom, prenom, email, login, password, dateNaiss);
		
		Model.addUser(user);
		
		Model.deleteUser(user.getId());
		user = Model.getUserWithID(2);
		assertEquals(null, user);
		
		
	}

	@AfterClass
	public static void tear() {
		Model.deleteTable();
	}
}
