package fr.intech.s5.appusers.beans;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long UserId;
	private String nom;
	private String prenom;
	private String email;
	private String login;
	private String password;
	
	/**
	 * Constructeur par defaut
	 */
	public User(){
		this.UserId = 0;
		this.nom = "";
		this.prenom = "";
		this.email = "";
		this.login = "";
	}
	/**
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param login
	 * @param password
	 * @param dateNaiss
	 */
	public User(String nom, String prenom, String email, String login, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.login = login;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return UserId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.UserId = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the telephone
	 */
	
	
}
