package fr.intech.s5.appusers.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.services.Connexion;

public class Model {

	private Connection con;
	
	public Model() {

	}
	
	public Model(Connection pCon) {
		con = pCon;
	}
	
	/**
	 * @param pCon
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Model(String pForname, String pDriverName, String pUserDataBase, String pPasswordDataBase) throws ClassNotFoundException, SQLException {
		Class.forName(pForname);
	    con = (Connection) DriverManager.getConnection(pDriverName,pUserDataBase, pPasswordDataBase);
		//Class.forName("org.h2.Driver");
	    //con = DriverManager.getConnection("jdbc:h2:mem:mytestdb","sa", "");
	}
	
	public boolean isUser(String pseudo, String mdp)
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
	
	public boolean addUser(User user)
	{
		String sql = "INSERT INTO user(id,nom, prenom, email, login, password, datenaissance) VALUES (?,?, ?, ?, ?, ?, ?)";
		Connexion connexion = new Connexion();
		try {
			PreparedStatement st = connexion.getConnexion().prepareStatement(sql);
			st.setInt(1, user.getId());
			st.setString(2, user.getNom());
			st.setString(3, user.getPrenom());
			st.setString(4, user.getEmail());
			st.setString(5, user.getLogin());
			st.setString(6, user.getPassword());
			st.setDate(7, Date.valueOf(user.getDateNaiss()));
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	public User getUser(String pseudo, String mdp)
	{
		User user = new User();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE login = ? AND password = ?");
			ps.setString(1, pseudo);
			ps.setString(2, mdp);
			ResultSet rs = ps.executeQuery();
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
	
	public boolean deleteTable()
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
