package fr.intech.s5.appusers.models;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import fr.intech.s5.appusers.beans.Telephone;
import fr.intech.s5.appusers.beans.User;
import fr.intech.s5.appusers.services.MD5;

public class ModelImp implements IModel{
	private final EntityManager em;
	
	public ModelImp(EntityManager em) {
		this.em = em;
	}

	/**
	 * Add a user and the associate telephone
	 * Autor : Clement
	 * @return boolean
	 */
	@Override
	public boolean addUserAndTelephone(User user,Telephone telephone) {
		EntityTransaction et = em.getTransaction();	
		et.begin();
				
		User userC = new User(user.getNom(), user.getPrenom(), user.getEmail(), user.getLogin(), MD5.crypt(user.getPassword()));
		Telephone tel = new Telephone(telephone.getTelFix(),telephone.getTelPortable(), userC);
		
		em.persist(userC);
		em.persist(tel);
				
		et.commit();
		
		return true;
	}



	@Override
	public boolean modifyUser(User newUser) {
		User user = em.find(User.class, newUser.getId());
		  em.getTransaction().begin();
		  user.setNom(newUser.getNom());
		  user.setPrenom(newUser.getPrenom());
		  user.setEmail(newUser.getEmail());
		  user.setLogin(newUser.getLogin());
		  em.getTransaction().commit();
		  return true;
	}

	@Override
	public boolean modifyTelephone(Telephone newTelephone) {
		Telephone tel = em.find(Telephone.class, newTelephone.getId());
		  em.getTransaction().begin();
		  tel.setTelFix(newTelephone.getTelFix());
		  tel.setTelPortable(newTelephone.getTelPortable());
		  em.getTransaction().commit();
		  return true;
	}
	/**
	 * Supprime un utilisateur et son telephone de la base de donnée
	 * Autor : Guillaume
	 * @param user user to delete
	 * @return true si le user a été supprimé
	 */
	@Override
	public boolean deleteUser(User user) {
		
		Telephone tel =  selectTelephone(user.getId());
		deleteTelephone(tel);
		
		User u = selectUserById(user.getId());
		if(u != null)
		{
			em.getTransaction().begin();
			em.remove(u);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}
	/**
	 * Supprime un telephone de la base de donnée 
	 * Autor : Guillaume
	 * @param telephone	Telephone à supprimer
	 * @return true si telephone a été supprimé
	 */
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
	 * Selectionne tous les users
	 * Autor : Guillaume
	 * @return List<User> une list contenant tous les users
	 */
	@Override
	public Collection<User> selectAllUser() {
			

		javax.persistence.Query query = em.createQuery("SELECT u FROM User u");
		try {
			return (Collection<User>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	/**
	 * Select User by id
	 * Autor : Guillaume
	 * @param id id de l'user à selectioner
	 * @return 
	 */
	@Override
	public User selectUserById(long id) {
		
		try {
			return em.find(User.class, id);
		} catch (NoResultException e) {
			return null;
		}
		
	}
	/**
	 * Select Telephone with UserID
	 * Auteur : Guillaume
	 * @param id UserID
	 * @return Le telephone de l'user
	 */
	@Override
	public Telephone selectTelephone(long id) {
		
		javax.persistence.Query query = em.createQuery("SELECT t FROM Telephone t WHERE t.UserID.UserId = ?1", Telephone.class);
		try {
			return (Telephone) query.setParameter(1, id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	/**
	 * Select user by login and password
	 * Auteur : Guillaume
	 * @param login Login of the user
	 * @param password password of the user
	 * @return Return the user
	 */
	@Override
	public User selectUserByLoginAndPassword(String login, String password) {
		
		javax.persistence.Query query = em.createQuery("SELECT u FROM User u WHERE u.login = ?1 AND u.password = ?2", User.class);
		try {
			return (User) query.setParameter(1, login).setParameter(2, MD5.crypt(password)).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
}
