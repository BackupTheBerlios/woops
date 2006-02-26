package business.activity.sequence;

import java.util.Collection;

import net.sf.hibernate.Session;
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
	
	/**
	 * Recuperation des sequences d'activites d'un projet
	 * @param bdeId : identifiant du projet
	 * @return : Liste des sequences d'activites du projet
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitySequencesByBDE(Integer bdeId) throws PersistanceException {
		Collection list = activitySequenceDAO.getActivitySequencesByBDE(bdeId);
		return list;
	}
	
	/**
	 * Recuperation des sequences d'activites d'un projet
	 * @param bdeId : identifiant du projet
	 * @param session : session permettant d'executer la requete
	 * @return : Liste des sequences d'activites du projet
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitySequencesByBDE(Integer bdeId, Session session) throws PersistanceException {
		Collection list = activitySequenceDAO.getActivitySequencesByBDE(bdeId, session);
		return list;
	}
}