package fr.intech.project.application;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.models.Model;

public class ModelTest {

	
	@BeforeClass
	public static void init() {
		//Model.Connect();
		String nom = "NSENGUET TOSSAM";
		String prenom = "Joris";
		String email = "nsenguetjoris@gmail.com";
		String login = "deviok";
		String password = "toto";
		Date dateNaiss = Date.valueOf(LocalDate.of(2016, 3, 16));
		User user = new User(nom, prenom, email, login, password, dateNaiss);
		
		Model.addUser(user);
		
	}
	
	@Test
	public void isUserTest()
	{
		assertEquals(true, Model.isUser("deviok", "toto"));
	}
	
	@Test
	public void addUserTest()
	{
		String nom = "PEUCH";
		String prenom = "Guillaume";
		String email = "gp@gmail.com";
		String login = "Clement";
		String password = "benoit";
		Date dateNaiss = Date.valueOf(LocalDate.of(1995, 10, 18));
		User user = new User(nom, prenom, email, login, password, dateNaiss);
		
		assertEquals(true, Model.addUser(user));
		User userRecup = Model.getUser(login, password);
		assertNotNull(userRecup);
		assertEquals(nom, userRecup.getNom());
		assertEquals(prenom, userRecup.getPrenom());
		assertEquals(email, userRecup.getEmail());
		assertEquals(login, userRecup.getLogin());
		assertEquals(password, userRecup.getPassword());
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
		assertEquals("toto", user.getPassword());
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final LocalDate localDate = LocalDate.parse(user.getDateNaiss().toString(), dtf);
		assertEquals(LocalDate.of(2016, 3, 16), localDate);
	}
	
	@Test
	public void getUserTestByID()
	{
		
		User user = Model.getUser("deviok", "toto");
		
		assertNotNull(user);
		assertEquals("NSENGUET TOSSAM", user.getNom());
		assertEquals("Joris", user.getPrenom());
		assertEquals("nsenguetjoris@gmail.com", user.getEmail());
		assertEquals("deviok", user.getLogin());
		assertEquals("toto", user.getPassword());
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final LocalDate localDate = LocalDate.parse(user.getDateNaiss().toString(), dtf);
		assertEquals(LocalDate.of(2016, 3, 16), localDate);
	}
	
	@Test
	public void deleteUser()
	{
		String nom = "Fimes";
		String prenom = "Guillaume";
		String email = "fg@gmail.com";
		String login = "guillaume";
		String password = "guillaume";
		Date dateNaiss = Date.valueOf(LocalDate.of(1995, 10, 18));
		User user = new User( nom, prenom, email, login, password, dateNaiss);
		
		Model.addUser(user);
		
		Model.deleteUser(user);
		user = Model.getUser("guillaume", "guillaume");
		assertEquals(null, user);
		
		
	}

	@AfterClass
	public static void tear() {
		Model.deleteTable();
	}
}
