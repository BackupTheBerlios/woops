package business.user;

import java.util.Collection;

import business.hibernate.PersistentObjectManager;
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
	 * Fournit tous les participants de l'entité
	 * @param bdeId : identifiant de l'entité
	 * @return : liste des participants
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getUsersByBDE(Integer bdeId) throws PersistanceException {
		return dao.getUsersByBDE(bdeId);
	}
}