package fr.intech.s5.appusers.models;


import java.util.Calendar;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.services.Connexion;

public class Model {
	
	private static Logger logger = Logger.getLogger( Model.class.getName());
	
	/**
	 * Constructeur inutile!
	 */
	private Model()
	{
		//Rien a y mettre!
	}
	
	/**
	 * Check if pseudo and mdp was assigned to the same user
	 * @param pseudo
	 * @param mdp
	 * @return true if is an user
	 */
	public static boolean isUser(String pseudo, String mdp)
	{
		Connexion connexion = new Connexion();
		try {
			Statement st = connexion.getConnexion().createStatement();
			ResultSet resultat = st.executeQuery("SELECT * FROM users");
			while(resultat.next())
			{
				if(pseudo.equals(resultat.getString(4)) && mdp.equals(resultat.getString(5)))
				{
					return true;
				}
			}
			
			
		} catch (Exception e) {
			printErr(e);
		}
		finally {
			closeConnection(connexion);
		}
		
		return false;
		
		
	}
	/**
	 * Add an user into database
	 * @param user
	 * @return return true if user as been created 
	 */
	public static boolean addUser(User user)
	{
		Connexion connexion = new Connexion();
		String sql = "INSERT INTO users(nom, prenom, email, login, password, datenaissance, id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = connexion.getConnexion().prepareStatement(sql);
			st.setString(1, user.getNom());
			st.setString(2, user.getPrenom());
			st.setString(3, user.getEmail());
			st.setString(4, user.getLogin());
			st.setString(5, user.getPassword());
			st.setDate(6, (Date) (user.getDateNaiss()));
			st.setLong(7, user.getId());
			
			st.executeUpdate();
		}catch (Exception e) {
			printErr(e);
			return false;
		}
		finally {
			closeConnection(connexion);
		}
		return true;
		
	}
	/**
	 * Get an user with is pseuod and mdp
	 * @param pseudo
	 * @param mdp
	 * @return An User or null
	 */
	public static User getUser(String pseudo, String mdp)
	{
		Connexion connexion = new Connexion();
		User user = null;
		try {
			PreparedStatement pt = connexion.getConnexion().prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
			pt.setString(1, pseudo);
			pt.setString(2, mdp);
			ResultSet rs= pt.executeQuery();
			
			while(rs.next())
			{
				user = new User();
				user.setId(rs.getInt(7));
				user.setNom(rs.getString(1));
				user.setPrenom(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setLogin(rs.getString(4));
				user.setPassword(rs.getString(5));
				final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				final LocalDate localDate = LocalDate.parse(rs.getDate(6).toString(), dtf);
				user.setDateNaiss(Date.parse(rs.getDate(6).toString()));
				
			}
			
		} catch (Exception e) {
			printErr(e);
		}
		finally {
			closeConnection(connexion);
		}
		return user;
	}
	/**
	 *  Get an user with is id
	 * @param id
	 * @return An User or null
	 */
	public static User getUserWithID(int id)
	{
		Connexion connexion = new Connexion();
		User user = null;
		try {
			PreparedStatement pt = connexion.getConnexion().prepareStatement("select * from users where id = ?");
			pt.setInt(1, id);
			ResultSet rs= pt.executeQuery();

			while(rs.next())
			{
				user = new User();
				user.setId(rs.getInt(7));
				user.setNom(rs.getString(1));
				user.setPrenom(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setLogin(rs.getString(4));
				user.setPassword(rs.getString(5));
				final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				final LocalDate localDate = LocalDate.parse(rs.getDate(6).toString(), dtf);
				user.setDateNaiss(Date.valueOf(localDate));
				
			}
			
		} catch (Exception e) {
			printErr(e);
		}
		finally {
			closeConnection(connexion);
		}
		return user;
	}
	/**
	 * Delete an user from database 
	 * @param user
	 * @return true if it's ok
	 */
	public static boolean deleteUser(User user)
	{
		Connexion connexion = new Connexion();
		try {
			PreparedStatement pt = connexion.getConnexion().prepareStatement("DELETE from users WHERE id = ?");
			pt.setLong(1, user.getId());
			pt.executeUpdate();
		} catch (Exception e) {
			printErr(e);
			return false;
		}
		finally {
			closeConnection(connexion);
		}
		return true;
	}
	
	/**
	 * Delete the table
	 * @return true if the table as been delete
	 */
	public static boolean deleteTable()
	{
		Connexion connexion = new Connexion();
		try {
			Statement st = connexion.getConnexion().createStatement();
			st.executeUpdate("TRUNCATE TABLE users");
		}catch (Exception e) {
			printErr(e);
			return false;
		}
		finally {
			closeConnection(connexion);
		}	
		return true;
	}
	
	private static void closeConnection(Connexion con)
	{
		try {
			con.getConnexion().close();
		} catch (SQLException e) {
			printErr(e);
		}
	}
	public static void printErr(Exception e)
	{
		logger.severe(e.getMessage());
	}
}
