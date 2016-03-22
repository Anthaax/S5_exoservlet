package fr.intech.s5.appusers.models;

import java.util.Collection;

import javax.persistence.EntityManager;

import fr.intech.s5.appusers.beans.Telephone;
import fr.intech.s5.appusers.beans.User;

public class ModelImp implements IModel{
	private final EntityManager em;
	
	public ModelImp(EntityManager em) {
		this.em = em;
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
		
		
		return false;
	}

	@Override
	public boolean deleteTelephone(Telephone telephone) {
		
		Telephone tel =  selectTelephone(telephone.getUserID().getId());
		if(tel != null)
		{
			em.getTransaction().begin();
			em.remove(tel);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}
	/**
	 * Select all Users from database
	 * Autor : Guillaume
	 * @return List<User>
	 */
	@Override
	public Collection<User> selectAllUser() {
			

		javax.persistence.Query query = em.createQuery("SELECT u FROM User u");
		
		return (Collection<User>) query.getResultList();
	}
	/**
	 * Select User by id
	 * Autor : Guillaume
	 * @param id id of the user to select
	 * @return 
	 */
	@Override
	public User selectUserById(long id) {
		
		return em.find(User.class, id);
		
	}
	/**
	 * Se
	 * @param id
	 * @return
	 */
	@Override
	public Telephone selectTelephone(long id) {
		
		javax.persistence.Query query = em.createQuery("SELECT t FROM Telephone t WHERE t.UserID.UserId = ?1", Telephone.class);
		
		return (Telephone) query.setParameter(1, id).getSingleResult();
	}

	@Override
	public User selectUserByLoginAndPassword(String login, String password) {
		
		javax.persistence.Query query = em.createQuery("SELECT u FROM User u WHERE u.login = ?1 AND u.password = ?2", User.class);
		
		return (User) query.setParameter(1, login).setParameter(2, password).getSingleResult();
	}
	
}
