package fr.intech.s5.appusers.models;

import fr.intech.s5.appusers.beans.Telephone;
import fr.intech.s5.appusers.beans.User;

public interface IModel {
	
	public boolean addUser(User user);
	
	public boolean addTelephone(Telephone telephone);
	
	public boolean modifyUser(User user);
	
	public boolean modifyTelephone(Telephone telephone);
	
	public boolean deleteUser(User user);
	
	public boolean deleteTelephone(Telephone telephone);
	
	public boolean selectAllUser();
	
	public boolean selectUserById(int id);
	
	public boolean selectTelephone(int id);
		
}
