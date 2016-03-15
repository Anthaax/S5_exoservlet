package fr.intech.project.application;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.models.Model;

public class ModelTest {

	private static JdbcTemplate jdbcTemplate;
	private static EmbeddedDatabase database;
	private Model modele;
	
	@BeforeClass
	public static void init() {
		database = (EmbeddedDatabase) (new EmbeddedDatabaseBuilder())
				.setSeparator(";;")
				.addScript("classpath:bdd.sql")
				.addScript("classpath:load.sql")
				.build();
	jdbcTemplate = new JdbcTemplate(database);
	}
	
	@Before
	public void setUp() {
		try {
			modele = new Model(jdbcTemplate.getDataSource().getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void isUserTest()
	{
		assertEquals(true, modele.isUser("deviok", "toto"));
	}
	
	@Test
	public void addUserTest()
	{
		int id = 0;
		String nom = "PEUCH";
		String prenom = "Guillaume";
		String email = "gp@gmail.com";
		String login = "Clement";
		String password = "benoit";
		LocalDate dateNaiss = LocalDate.of(1995, 10, 18);
		User user = new User(id, nom, prenom, email, login, password, dateNaiss);
		
		assertEquals(true, modele.addUser(user));
		User userRecup = modele.getUser(login, password);
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
		User user = modele.getUser("deviok", "toto");
		
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
	
	
	@AfterClass
	public static void tear() {
		database.shutdown();
	}
}
