package fr.intech.s5.appusers.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.services.Connexion;

public class Model {

	
	public Model()
	{
		
	}
	
	public static boolean isUser(String pseudo, String mdp)
	{
		Connexion connexion = new Connexion();
		try {
			Statement st = connexion.getConnexion().createStatement();
			ResultSet resultat = st.executeQuery("SELECT * FROM user");
			while(resultat.next())
			{
				if(pseudo.equals(resultat.getString(5)) && mdp.equals(resultat.getString(6)))
				{
					return true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean addUser(User user)
	{
		String sql = "INSERT INTO user(nom, prenom, email, login, password, datenaissance) VALUES (?, ?, ?, ?, ?, ?)";
		Connexion connexion = new Connexion();
		try {
			PreparedStatement st = connexion.getConnexion().prepareStatement(sql);
			st.setString(1, user.getNom());
			st.setString(2, user.getPrenom());
			st.setString(3, user.getEmail());
			st.setString(4, user.getLogin());
			st.setString(5, user.getPassword());
			st.setDate(6, Date.valueOf(user.getDateNaiss()));
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	public static User getUser(String pseudo, String mdp)
	{
		Connexion connexion = new Connexion();
		User user = new User();
		try {
			Statement st = connexion.getConnexion().createStatement();
			ResultSet rs= st.executeQuery("SELECT * FROM user WHERE login = '"+pseudo+"' AND password = '"+mdp+"'");

			while(rs.next())
			{
				user.setId(rs.getInt(1));
				user.setNom(rs.getString(2));
				user.setPrenom(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setLogin(rs.getString(5));
				user.setPassword(rs.getString(6));
				final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				final LocalDate localDate = LocalDate.parse(rs.getDate(7).toString(), dtf);
				user.setDateNaiss(localDate);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean deleteTable()
	{
		
		Connexion connexion = new Connexion();
		try {
			Statement st = connexion.getConnexion().createStatement();
			st.executeUpdate("TRUNCATE TABLE user");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
