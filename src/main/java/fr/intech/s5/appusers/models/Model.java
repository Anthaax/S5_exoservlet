package fr.intech.s5.appusers.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.services.Connexion;

public class Model {
	
	//Logger pour afficher les informations.
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
			PreparedStatement pt = connexion.getConnexion().prepareStatement(sql);
			setParamsUser(pt, user);
			
			pt.executeUpdate();
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
	 * Modifier un utilisateur.
	 * @param user
	 */
	public static void modifyUser(User user)
	{
		Connexion connexion = new Connexion();
		try {
			PreparedStatement pt = connexion.getConnexion().prepareStatement("UPDATE users SET nom = ?, prenom = ?, email = ?, login = ?, password = ?, datenaissance = ? WHERE id = ?");
			setParamsUser(pt, user);
			
			pt.executeUpdate();
		} catch (Exception e) {
			printErr(e);
		}
	}
	
	/**
	 * Get an user with is pseudo and mdp
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
				user = hydrateUser(rs);
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
				user = hydrateUser(rs);
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
	 * Recuperer tous les users
	 */
	public static List<User> getAllUsers()
	{
		List<User> users = new ArrayList<>();
		Connexion connexion = new Connexion();
		
		try {
			PreparedStatement pt = connexion.getConnexion().prepareStatement("SELECT * FROM users");
			ResultSet rs= pt.executeQuery();
			while(rs.next())
			{
				User user = hydrateUser(rs);
				users.add(user);
			}
			
		} catch (Exception e) {
			printErr(e);
		}
		finally {
			closeConnection(connexion);
		}
		return users;
	}
	
	/**
	 * Delete an user from database 
	 * @param user
	 * @return true if it's ok
	 */
	public static boolean deleteUser(int idUser)
	{
		Connexion connexion = new Connexion();
		try {
			PreparedStatement pt = connexion.getConnexion().prepareStatement("DELETE from users WHERE id = ?");
			pt.setInt(1, idUser);
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
	
	private static User hydrateUser(ResultSet rs)
	{
		User user = new User();
		try {
			user.setId(rs.getInt(7));
			user.setNom(rs.getString(1));
			user.setPrenom(rs.getString(2));
			user.setEmail(rs.getString(3));
			user.setLogin(rs.getString(4));
			user.setPassword(rs.getString(5));
			final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			final LocalDate localDate = LocalDate.parse(rs.getDate(6).toString(), dtf);
			user.setDateNaiss(localDate);
		} catch (Exception e) {
			printErr(e);
		}
		
		return user;
	}
	
	/**
	 * Introduction des paramatres dans la requete preparée pt avec un user.
	 * @param pt
	 * @param user
	 */
	private static void setParamsUser(PreparedStatement pt, User user)
	{
		try {
			pt.setString(1, user.getNom());
			pt.setString(2, user.getPrenom());
			pt.setString(3, user.getEmail());
			pt.setString(4, user.getLogin());
			pt.setString(5, user.getPassword());
			pt.setDate(6, Date.valueOf(user.getDateNaiss()));
			pt.setInt(7, user.getId());
		} catch (Exception e) {
			printErr(e);
		}
		
	}
	
	/**
	 * Fermeture d'une connexion...
	 * @param con
	 */
	private static void closeConnection(Connexion con)
	{
		try {
			con.getConnexion().close();
		} catch (SQLException e) {
			printErr(e);
		}
	}
	
	/**
	 * Affichage d'eventuelles erreurs
	 * @param e
	 */
	public static void printErr(Exception e)
	{
		logger.severe(e.getMessage());
	}
}
