package business.activity;

import business.hibernate.ObjetPersistantManager;
import business.hibernate.exception.PersistanceException;

public class ActivitySequenceTypeManager extends ObjetPersistantManager {
	
	/** Instance permettant d'assurer la persistance d'un type de suequence d'activit� */
	private ActivitySequenceTypeDAO activitySequenceTypeDAO = new ActivitySequenceTypeDAO();
	
	/** Instance priv�e de la la classe */
	private static ActivitySequenceTypeManager activitySequenceTypeManager;

	/**
	 * Impl�mentation du pattern Singleton : constructeur priv�
	 */
	private ActivitySequenceTypeManager() {
	}

	/**
	 * Fournit l'instance de la classe
	 * @return ActivitySequenceTypeManager : instance de la classe
	 */
	public static ActivitySequenceTypeManager getInstance() {
		if (activitySequenceTypeManager == null) {
			synchronized (ActivitySequenceTypeManager.class) {
				activitySequenceTypeManager = new ActivitySequenceTypeManager();
			}
		}
		return activitySequenceTypeManager;
	}
	
	
	public ActivitySequenceType getActivitySequenceTypeById(Integer activitySequenceTypeId)
	throws PersistanceException {
			return activitySequenceTypeDAO.getActivitySequenceTypeById(activitySequenceTypeId);
	}
}