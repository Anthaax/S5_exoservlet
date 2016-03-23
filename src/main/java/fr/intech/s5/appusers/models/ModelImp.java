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
	public boolean modifyUser(User newUser) {
		User user = em.find(User.class, 1);
		  em.getTransaction().begin();
		  user.setNom(newUser.getNom());
		  user.setPrenom(newUser.getPrenom());
		  user.setEmail(newUser.getEmail());
		  user.setLogin(newUser.getLogin());
		  user.setPassword(newUser.getPassword());
		  em.getTransaction().commit();
		  return true;
	}

	@Override
	public boolean modifyTelephone(Telephone newTelephone) {
		Telephone tel = em.find(Telephone.class, 1);
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
		
		return (Collection<User>) query.getResultList();
	}
	/**
	 * Select User by id
	 * Autor : Guillaume
	 * @param id id de l'user à selectioner
	 * @return 
	 */
	@Override
	public User selectUserById(long id) {
		
		return em.find(User.class, id);
		
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
		
		return (Telephone) query.setParameter(1, id).getSingleResult();
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
		
		return (User) query.setParameter(1, login).setParameter(2, password).getSingleResult();
	}
	
}
