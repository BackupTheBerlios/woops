package business.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;



public class ActivityManager extends PersistentObjectManager {
	
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

	
	public Activity getActivityById(Integer activityId)
			throws PersistanceException {
		return activityDAO.getActivityById(activityId);
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
	
	/**
	 * 
	 * @param activityId : l'activit� dont on veut connaitre des d�pendances possibles
	 * @return la liste des activit� dont peut d�pendre l'activit� pass�e en parametre
	 * @throws PersistanceException
	 */
	public Collection getPossibleActivityDependances(Integer activityId)
			throws PersistanceException {
		Collection list = activityDAO.getPossibleActivityDependances(activityId);
		return list;
	}
	
	/**
	 * 
	 * @param activityId : l'activit� dont on veut connaitre ses d�pendances
	 * @return la liste des activit� dont depend l'activit� pass�e en parametre
	 * @throws PersistanceException
	 */
	public Collection getActivityDependances(Integer activityId) 
			throws PersistanceException {
		Collection list = activityDAO.getActivityDependances(activityId);
		return list;
	}
	
	/**
	 * 
	 * @param activityId
	 * @param oldDependancesKeys
	 * @param newDependancesKeys
	 * @throws PersistanceException
	 * @throws DoublonException 
	 * @throws ForeignKeyException 
	 */
	public void saveActivityDependances(Integer activityId, String[] oldDependancesKeys, String[] newDependancesKeys)
	throws PersistanceException, DoublonException, ForeignKeyException {
		Collection oldDendancesKeysList = new ArrayList();
		for(int i=0; i < oldDependancesKeys.length; i++)
			if(!oldDependancesKeys[i].equals(""))
				oldDendancesKeysList.add(new Integer(oldDependancesKeys[i]));
		
		Collection newDendancesKeysList = new ArrayList();
		for(int i=0; i < newDependancesKeys.length; i++)
			if(!newDependancesKeys[i].equals(""))
				newDendancesKeysList.add(new Integer(newDependancesKeys[i]));
		
		Collection dependancesToAddList = new ArrayList(newDendancesKeysList);
		dependancesToAddList.removeAll(oldDendancesKeysList);
		
		Collection dependancesToRemoveList = new ArrayList(oldDendancesKeysList);
		dependancesToRemoveList.removeAll(newDendancesKeysList);
		
		
		ActivitySequenceManager activitySequenceManager = ActivitySequenceManager.getInstance();
		ActivitySequenceTypeManager activitySequenceTypeManager = ActivitySequenceTypeManager.getInstance();
		
		
		
		Activity successor= new Activity();
		successor.setId(activityId);
		Activity predecessor = new Activity();
		ActivitySequenceType linkType = new ActivitySequenceType();
		linkType.setId(new Integer(1));
		/**
		 * Attention : id du activitySequenceType en DUR 
		 */
		
		Iterator dependancesToAddIter = dependancesToAddList.iterator();
		while(dependancesToAddIter.hasNext())
		{
			predecessor.setId(dependancesToAddIter.next());
			activitySequenceManager.addActivitySequence(predecessor,successor,linkType);
		}
		
		Iterator dependancesToRemoveIter = dependancesToRemoveList.iterator();
		while(dependancesToRemoveIter.hasNext())
		{
			predecessor.setId(dependancesToRemoveIter.next());
			activitySequenceManager.removeActivitySequence(predecessor,successor,linkType);
		}
	}
	
}