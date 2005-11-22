package business.activity;

import java.util.Collection;
import business.hibernate.ObjetPersistantManager;
import business.hibernate.exception.PersistanceException;

public class ActivityManager extends ObjetPersistantManager {
	
	/** Instance permettant d'assurer la persistance d'une activité */
	private ActivityDAO activityDAO = new ActivityDAO();
	
	/** Instance privée de la la classe */
	private static ActivityManager activityManager;

	/**
	 * Implémentation du pattern Singleton : constructeur privé
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
	 * Récupération des activités pour lesquelles le participant a la responsabilité
	 * @param userId : identifiant du participant
	 * @return : Liste des activités du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la récupération des données
	 */
	public Collection getActivitiesByUser(Integer userId)
			throws PersistanceException {
		Collection list = activityDAO.getActivitiesByUser(userId);
		return list;
	}
}