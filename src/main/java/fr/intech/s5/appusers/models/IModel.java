package fr.intech.s5.appusers.models;

import java.util.Collection;

import fr.intech.s5.appusers.beans.Telephone;
import fr.intech.s5.appusers.beans.User;

public interface IModel {
	
	public boolean addUser(User user);
	
	public boolean addTelephone(Telephone telephone);
	
	public boolean modifyUser(User user);
	
	public boolean modifyTelephone(Telephone telephone);
	
	public boolean deleteUser(User user);
	
	public boolean deleteTelephone(Telephone telephone);
	
	public Collection<User> selectAllUser();
	
	public User selectUserById(long id);
	
	public User selectUserByLoginAndPassword(String login, String password);
	
	public Telephone selectTelephone(long id);
		
}
