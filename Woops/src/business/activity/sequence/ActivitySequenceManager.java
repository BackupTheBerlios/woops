package business.activity.sequence;

import business.activity.Activity;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;

public class ActivitySequenceManager extends PersistentObjectManager {
	
	/** Instance permettant d'assurer la persistance d'une activit? */
	private ActivitySequenceDAO activitySequenceDAO = new ActivitySequenceDAO();
	
	/** Instance priv?e de la la classe */
	private static ActivitySequenceManager activitySequenceManager;

	/**
	 * Impl?mentation du pattern Singleton : constructeur priv?
	 */
	private ActivitySequenceManager() {
	}

	/**
	 * Fournit l'instance de la classe
	 * @return ActivityManager : instance de la classe
	 */
	public static ActivitySequenceManager getInstance() {
		if (activitySequenceManager == null) {
			synchronized (ActivitySequenceManager.class) {
				activitySequenceManager = new ActivitySequenceManager();
			}
		}
		return activitySequenceManager;
	}

	
	public void removeActivitySequence(Activity predecessor, Activity successor) 
	throws PersistanceException, ForeignKeyException {
		StringBuffer query = new StringBuffer();
		query.append("FROM ActivitySequence actSeq");
		query.append(" WHERE actSeq.successor.id = "+successor.getId().toString());
		query.append(" AND actSeq.predecessor.id = "+predecessor.getId().toString());
		activitySequenceDAO.delete(query.toString());
	}
	
}