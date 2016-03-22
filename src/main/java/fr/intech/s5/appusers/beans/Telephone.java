package fr.intech.s5.appusers.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Telephone {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	
	private String telFix;
	
	private String telPortable;
	
	@OneToOne
	@JoinColumn(name = "UserId")
	private User UserID;
	
	public Telephone() 
	{
		
	}
	
	/**
	 * 
	 * @param telFix
	 * @param telPortable
	 */
	public Telephone(String telFix, String telPortable, User user) {
		this.telFix = telFix;
		this.telPortable = telPortable;
		UserID = user;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the telFix
	 */
	public String getTelFix() {
		return telFix;
	}

	/**
	 * @param telFix the telFix to set
	 */
	public void setTelFix(String telFix) {
		this.telFix = telFix;
	}

	/**
	 * @return the telPortable
	 */
	public String getTelPortable() {
		return telPortable;
	}

	/**
	 * @param telPortable the telPortable to set
	 */
	public void setTelPortable(String telPortable) {
		this.telPortable = telPortable;
	}
	/**
	 * @return the userID
	 */
	public User getUserID() {
		return UserID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(User userID) {
		UserID = userID;
	}
	
}
