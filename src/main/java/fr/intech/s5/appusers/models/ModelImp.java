package fr.intech.s5.appusers.models;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.sql.ordering.antlr.Factory;

import fr.intech.s5.appusers.beans.Telephone;
import fr.intech.s5.appusers.beans.User;

public class ModelImp implements IModel{

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean addTelephone(Telephone telephone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyTelephone(Telephone telephone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTelephone(Telephone telephone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean selectAllUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean selectUserById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean selectTelephone(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
