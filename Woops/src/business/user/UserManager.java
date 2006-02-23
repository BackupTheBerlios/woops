package business.user;

import java.util.Collection;
import java.util.Iterator;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.exception.GenericJDBCException;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;

public class UserManager extends PersistentObjectManager {
	
	private UserDAO dao = new UserDAO();	
	
	private static UserManager instance;
	
	/**
	 * Singleton -> Constructeur priv?
	 */
	private UserManager() {}

	public static UserManager getInstance() {
		if (instance == null) {
			synchronized (UserManager.class) {
				instance = new UserManager();
			}
		}
		return instance;
	}
	
	public User getUser(String login) throws PersistanceException {
		User user = dao.get(login);		
		return user;
	}
	

	/**
	 * Cette methode controle la validite du couple login/mot de passe
	 * @param login
	 * @param password
	 * @return
	 * @throws PersistanceException
	 */
	public User isLoginValid(String login, String password) throws PersistanceException {
		User user = dao.getUser(login,password);
		
		return user;
	}	
	
	/**
	 * Fournit tous les participants de l'entit�
	 * @param bdeId : identifiant de l'entit�
	 * @return : liste des participants
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la r�cup�ration des donn�es
	 */
	public Collection getUsersByBDE(Integer bdeId) throws PersistanceException {
		return dao.getUsersByBDE(bdeId);
	}
	
	/**
	 * Cette m�thode permet d'obtenir un participant a partir de son identification
	 * @param bdeId : identifiant 
	 * @return : participant
	 * @throws PersistanceException Indique qu'une erreur s'est d�roul�e au moment de la r�cup�ration des donn�es
	 */
	public User getUserById(Integer id){
		
		User user = new User();
		
		try {
			user = dao.getUser(id);
		} catch (PersistanceException e) {
			user = null;
		}
		return user;
	}
	
	

	public void delete(User user) throws PersistanceException, ForeignKeyException {	
		Session session = null ;
		Transaction transaction = null;
		Collection dbData = null;
		try {
			// Chercher les activites concernant de l'utilisateur a supprimer
			// Mise a jour le champs de "UserId" a null de ces activites
			dbData = ActivityManager.getInstance().getAllActivitiesByUser((Integer)user.getId());
			
			session = this.getSession();
			// Cr�ation d'une transaction pour pouvoir annuler l'ensemble des modifications en cas d'erreur
			transaction = session.beginTransaction();
			
			Iterator iter = dbData.iterator();
	    	while (iter.hasNext()) {
	    		Activity activity = (Activity) iter.next();
	    		activity.setUserId(null);
	    		ActivityManager.getInstance().update(activity, session);
	    	}
	    	// Recuperation de l'utilisateur avec la liste des projets
	    	user = (User) session.load(User.class, (Integer) user.getId());

	    	// Suppression des affectations aux entit�s, la suppression se repercute sur la BD automatiquement grace ala session en cours
	    	user.getBdes().clear();
	    	
	    	// Suppression de l'utilisateur
	    	this.delete(user, session);
			
	    	// Tout s'est bien pass�, on valide la transaction
			transaction.commit();
			
	    } catch (GenericJDBCException se) {
			this.rollback(transaction);
	        if (se.getErrorCode()==2292)
	        	throw new ForeignKeyException(se.getMessage());
			throw new PersistanceException(se.getMessage(),se);
	    } catch (HibernateException he) {
	        this.rollback(transaction);
	        throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) 
					this.closeSession(session);			
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}
	}	
}