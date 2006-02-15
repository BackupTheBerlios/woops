package business.breakdownelement;

import java.io.File;
import java.util.List;

import business.hibernate.HibernateSessionFactory;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.PersistanceException;

public class UserBDEManager extends PersistentObjectManager {
	
	/** Instance permettant d'assurer la persistance d'une entité */
	private UserBDEDAO userBDEDAO = new UserBDEDAO();
	
	/** Instance privée de la classe */
	private static UserBDEManager userBDEManager;

	

	/**
	 * Implémentation du pattern Singleton : constructeur privé
	 */
	private UserBDEManager() {
	}

	/**
	 * Fournit l'instance de la classe
	 * @return userBDEManager : instance de la classe
	 */
	public static UserBDEManager getInstance() {
		if (userBDEManager == null) {
			synchronized (UserBDEManager.class) {
				userBDEManager = new UserBDEManager();
			}
		}
		return userBDEManager;
	}
	
}
