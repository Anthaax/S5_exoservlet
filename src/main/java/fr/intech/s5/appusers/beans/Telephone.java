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
	
	
	private String TelFix;
	
	private String TelPortable;
	
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
		TelFix = telFix;
		TelPortable = telPortable;
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
		return TelFix;
	}

	/**
	 * @param telFix the telFix to set
	 */
	public void setTelFix(String telFix) {
		TelFix = telFix;
	}

	/**
	 * @return the telPortable
	 */
	public String getTelPortable() {
		return TelPortable;
	}

	/**
	 * @param telPortable the telPortable to set
	 */
	public void setTelPortable(String telPortable) {
		TelPortable = telPortable;
	}
	
	
}
