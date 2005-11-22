package business.activity;

import java.util.Collection;
import business.hibernate.ObjetPersistantManager;
import business.hibernate.exception.PersistanceException;

public class ActivityManager extends ObjetPersistantManager {
	
	/** Instance permettant d'assurer la persistance d'une activit� */
	private ActivityDAO activityDAO = new ActivityDAO();
	
	/** Instance priv�e de la la classe */
	private static ActivityManager activityManager;

	/**
	 * Impl�mentation du pattern Singleton : constructeur priv�
	 */
	private ActivityManager() {
	}

	/**
	 * Fournit l'instance de la classe
	 * @return ActivityManager : instance de la classe
	 */
	public static ActivityManager getInstance() {
		if (activityManager == null) {
			synchronized (ActivityManager.class) {
				activityManager = new ActivityManager();
			}
		}
		return activityManager;
	}

	/**
	 * R�cup�ration des activit�s pour lesquelles le participant a la responsabilit�
	 * @param userId : identifiant du participant
	 * @return : Liste des activit�s du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r�cup�ration des donn�es
	 */
	public Collection getActivitiesByUser(Integer userId)
			throws PersistanceException {
		Collection list = activityDAO.getActivitiesByUser(userId);
		return list;
	}
}