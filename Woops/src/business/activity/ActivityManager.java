package business.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import business.BusinessConstantes;
import business.activity.sequence.ActivitySequence;
import business.activity.sequence.ActivitySequenceManager;
import business.activity.sequencetype.ActivitySequenceType;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;



public class ActivityManager extends PersistentObjectManager {
	
	/** Instance permettant d'assurer la persistance d'une activit? */
	private ActivityDAO activityDAO = new ActivityDAO();
	
	/** Instance priv?e de la classe */
	private static ActivityManager activityManager;

	

	/**
	 * Impl?mentation du pattern Singleton : constructeur priv?
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
	 * R?cup?ration des activit?s pour lesquelles le participant a la responsabilit?
	 * @param userId : identifiant du participant
	 * @return : Liste des activit?s du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitiesByUser(Integer userId)
			throws PersistanceException {
		String[] states = new String[2];
		states[0] = BusinessConstantes.ACTIVITY_STATE_CREATED;
		states[1] = BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS;
		Collection list = activityDAO.getActivitiesByUserWithStates(userId, states);
		return list;
	}
	
	/**
	 * R?cup?ration des activit?s que le participant a termin?es
	 * @param userId : identifiant du participant
	 * @return : historique des activit?s du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitiesHistoryByUser(Integer userId)
			throws PersistanceException {
		String[] states = new String[1];
		states[0] = BusinessConstantes.ACTIVITY_STATE_FINISHED;
		Collection list = activityDAO.getActivitiesByUserWithStates(userId, states);
		return list;
	}
	
	
	/**
	 * 
	 * @param activityId : l'activit? dont on veut connaitre des d?pendances possibles
	 * @return la liste des activit? dont peut d?pendre l'activit? pass?e en parametre
	 * @throws PersistanceException
	 */
	public Collection getPossibleActivityPredecessors(Integer activityId)
			throws PersistanceException {
		Collection list = activityDAO.getPossiblePredecessors(activityId);
		return list;
	}
	
	/**
	 * 
	 * @param activityId : l'activit? dont on veut connaitre ses pr?d?cesseurs
	 * @return la liste des activit? dont depend l'activit? pass?e en parametre
	 * @throws PersistanceException
	 */
	public Collection getPredecessors(Integer activityId) 
			throws PersistanceException {
		Collection listActivitySequences = activityDAO.getActivitySequences(activityId);
		
		Collection listPredecessors = new ArrayList();
		Iterator iter = listActivitySequences.iterator();
		
		while(iter.hasNext())
		{
			listPredecessors.add(((ActivitySequence)iter.next()).getPredecessor());
		}
		
		return listPredecessors;
	}
	
	/**
	 * 
	 * @param activityId : l'activit? dont on veut connaitre ses d?pendances
	 * @return la liste des s?quence d'activit? dont l'activit? pass?e en parametre et le successeur
	 * @throws PersistanceException
	 */
	public Collection getActivitySequences(Integer activityId) 
			throws PersistanceException {
		Collection list = activityDAO.getActivitySequences(activityId);
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
	
		Activity successor= new Activity();
		successor.setId(activityId);
		Activity predecessor = new Activity();
		
		Iterator dependancesToRemoveIter = dependancesToRemoveList.iterator();
		while(dependancesToRemoveIter.hasNext())
		{
			predecessor.setId(dependancesToRemoveIter.next());
			activitySequenceManager.removeActivitySequence(predecessor,successor);
		}
		
		/* Par defaut, le type des d?pendances sont finsihToStart */
		ActivitySequenceType linkType = new ActivitySequenceType();
		linkType.setId(new Integer(1));
		
		Iterator dependancesToAddIter = dependancesToAddList.iterator();
		while(dependancesToAddIter.hasNext())
		{
			predecessor.setId(dependancesToAddIter.next());
			activitySequenceManager.addActivitySequence(predecessor,successor,linkType);
		}
		
	}
	
	public Activity getActivityWithDependances(Integer activityId) throws PersistanceException {
		Activity activity = activityDAO.getActivityById(activityId);
		activity.setListActivitiesSequences(ActivityManager.getInstance().getActivitySequences(activityId));
		return activity;
	}
	
	/**
	 * 
	 * @param actSeq
	 * @param linkType1
	 * @param linkType2
	 * @return retourne Vrai si l'?tat du predecessor est le bon, faux sinon
	 */
	private boolean verifPredecessorState(ActivitySequence actSeq, String linkType1, String linkType2) {
		boolean result = true;
		
		// Si il n'y a pas de lien Finish To ...
		if (actSeq.getLinkType().getName().equals(linkType1)) {
			// Et v?rifier si l'activit? predecessor est au bon ?tat
			if (!actSeq.getPredecessor().getState().equals(BusinessConstantes.ACTIVITY_STATE_FINISHED))
				result = false;
		}
				
		// Ou de lien Start To ...
	    if (actSeq.getLinkType().getName().equals(linkType2)) {
	    	// Et v?rifier si l'activit? predecessor est au bon ?tat
	    	if ((!actSeq.getPredecessor().getState().equals(BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS))&&
	    	     (!actSeq.getPredecessor().getState().equals(BusinessConstantes.ACTIVITY_STATE_FINISHED)))
				result = false;
	    }
	    
	    return result;
	}
	
	/**
	 * 
	 * @param activity
	 * @return retourne vrai si l'activite peut ?voluer d'?tat, faux sinon
	 * @throws PersistanceException
	 */
	private boolean verifChangeStateActivity(Activity activity) throws PersistanceException {
		boolean result = true;
		Iterator iter;
		String activityState = activity.getState().getName();
		
		// R?cup?ration de la liste des ActivitySequence de l'activite
		Collection activitySeq = ActivityManager.getInstance().getActivitySequences((Integer) activity.getId());
		
		
		////////////////////////////////////////////////////////////////////////////////////
		
		
		// Si l'activit? est Created
		// Et qu'elle veut pouvoir commencer
		if (activityState.equals(BusinessConstantes.ACTIVITY_STATE_CREATED)) {
			iter = activitySeq.iterator();
			
			// Il faut v?rifier dans ses predecesseurs
			while ((iter.hasNext())&&(result==true))
				result = verifPredecessorState((ActivitySequence)iter.next(),BusinessConstantes.LINK_TYPE_FINISH_TO_START,BusinessConstantes.LINK_TYPE_START_TO_START);
		}
		
		
		//////////////////////////////////////////////////////////////////////////////////
		
		
		// Si l'activit? est In Progress
		// Et qu'elle veut pouvoir finir
		if (activityState.equals(BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS)) {
			iter = activitySeq.iterator();
			
			// Il faut v?rifier dans ses predecesseurs
			while ((iter.hasNext())&&(result==true))
				result = verifPredecessorState((ActivitySequence)iter.next(),BusinessConstantes.LINK_TYPE_FINISH_TO_FINISH,BusinessConstantes.LINK_TYPE_START_TO_FINISH);
		}
		
		
		return result;
	}
	
	/**
	 * 
	 * @param userId
	 * @return retourne ? l'utilisateur, l'ensemble de ses activit?s dont il peut changer l'?tat
	 * @throws PersistanceException 
	 */
	public Collection activitiesChangeState(Integer userId) throws PersistanceException {
		Activity act;
		
		// Liste des activites pouvant changer d'?tat
		Collection listActivitiesChangeState = new ArrayList();
		
		// Recuperation des activites de l'utilisateur
		Collection listActivities = getActivitiesByUser(userId);
		
		// Pour chacune d'entre elles, on v?rifie si elle peut changer d'etat
		Iterator iter = listActivities.iterator();
		while (iter.hasNext()) {
			act = (Activity)iter.next();
			if (verifChangeStateActivity(act))
				listActivitiesChangeState.add(act);
		}
		
		return listActivitiesChangeState;
	}
	

}