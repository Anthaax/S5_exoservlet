package fr.intech.s5.appusers.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.intech.s5.appusers.beans.Telephone;
import fr.intech.s5.appusers.beans.User;

public class ModelImp implements IModel{
	
	private EntityManager entityManager ()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("appuser");
		EntityManager em = emf.createEntityManager();
		
		return em;
	}
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
	/**
	 * Select all Users from database
	 * Autor : Guillaume
	 * @return List<User>
	 */
	@Override
	public Collection<User> selectAllUser() {
			
		EntityManager em = entityManager();
		em.getTransaction().begin();

		javax.persistence.Query query = em.createQuery("SELECT * FROM User");
		
		return query.getResultList();
	}
	/**
	 * Select User by id
	 * Autor : Guillaume
	 * @param id id of the user to select
	 * @return 
	 */
	@Override
	public User selectUserById(int id) {
		
		return null;
		
	}
	/**
	 * Se
	 * @param id
	 * @return
	 */
	@Override
	public Telephone selectTelephone(int id) {
		
		return null;
	}

	@Override
	public User selectUserByLoginAndPassword(String login, String password) {
		
		return null;
	}
	
}
