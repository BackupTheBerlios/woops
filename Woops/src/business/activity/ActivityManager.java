package business.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import net.sf.hibernate.Session;

import business.BusinessConstantes;
import business.activity.sequence.ActivitySequence;
import business.activity.sequence.ActivitySequenceManager;
import business.activity.sequencetype.ActivitySequenceType;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;



public class ActivityManager extends PersistentObjectManager {
	
	/** Instance permettant d'assurer la persistance d'une activite */
	private ActivityDAO activityDAO = new ActivityDAO();
	
	/** Instance privee de la classe */
	private static ActivityManager activityManager;

	

	/**
	 * Implementation du pattern Singleton : constructeur prive
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
	 * Recuperation d'une activite
	 * @param activityId : identififiant de l'activite 
	 * @return : activite correspondante
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donn?es
	 */
	public Activity getActivityById(Integer activityId)
			throws PersistanceException {
		return activityDAO.getActivityById(activityId);
	}
	
	/**
	 * Recuperation des activites d'une entite
	 * @param bdeId : identifiant de l'entite
	 * @return : Liste des activites correspondant au critere de recherche
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getAllActivitiesByBDE(Integer bdeId) throws PersistanceException {
		return activityDAO.getAllActivitiesByBDE(bdeId);
	}
	
	
	/**
	 * Recuperation des activites libres sur une entite
	 * @param bdeId : identifiant de l'entite
	 * @return : liste des activites 
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getFreeActivities(Integer bdeId) throws PersistanceException {
		return activityDAO.getFreeActivities(bdeId);
	}
	
	/**
	 * Recuperation des activites pour lesquelles le participant a la responsabilite
	 * @param userId : identifiant du participant
	 * @return : Liste des activites restantes du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donn?es
	 */
	public Collection getAllActivitiesByUser(Integer userId)
			throws PersistanceException {
		String[] states = new String[3];
		states[0] = BusinessConstantes.ACTIVITY_STATE_CREATED;
		states[1] = BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS;
		states[2] = BusinessConstantes.ACTIVITY_STATE_FINISHED;
		Collection list = activityDAO.getActivitiesByUserWithStates(userId, states);
		return list;
	}
	
	/**
	 * Recuperation des activites restant à realiser pour lesquelles le participant a la responsabilite sur une entité donnéé
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @return : Liste des activites du particpant sur l'entité
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getAllActivitiesByUserByBDE(Integer userId, Integer bdeId)
			throws PersistanceException {
		String[] states = new String[3];
		states[0] = BusinessConstantes.ACTIVITY_STATE_CREATED;
		states[1] = BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS;
		states[2] = BusinessConstantes.ACTIVITY_STATE_FINISHED;
		Collection list = activityDAO.getActivitiesByUserByBDEWithStates(userId, bdeId, states);
		return list;
	}
	
	/**
	 * Recuperation des activites restant à realiser pour lesquelles le participant a la responsabilite sur une entité donnéé
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @param session : session permettant d'executer la requete
	 * @return : Liste des activites du particpant sur l'entité
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getAllActivitiesByUserByBDE(Integer userId, Integer bdeId, Session session)
			throws PersistanceException {
		String[] states = new String[3];
		states[0] = BusinessConstantes.ACTIVITY_STATE_CREATED;
		states[1] = BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS;
		states[2] = BusinessConstantes.ACTIVITY_STATE_FINISHED;
		Collection list = activityDAO.getActivitiesByUserByBDEWithStates(userId, bdeId, states, session);
		return list;
	}
	
	/**
	 * Recuperation toutes les activites pour lesquelles le participant a la responsabilite
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @return : Liste des activites restantes du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getRemainingActivitiesByUserByBDE(Integer userId, Integer bdeId)
			throws PersistanceException {
		String[] states = new String[2];
		states[0] = BusinessConstantes.ACTIVITY_STATE_CREATED;
		states[1] = BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS;
		Collection list = activityDAO.getActivitiesByUserByBDEWithStates(userId, bdeId, states);
		return list;
	}
	
	
	/**
	 * Recuperation des activites que le participant a terminees
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @return : historique des activites du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitiesHistoryByUser(Integer userId, Integer bdeId)
			throws PersistanceException {
		String[] states = new String[1];
		states[0] = BusinessConstantes.ACTIVITY_STATE_FINISHED;
		Collection list = activityDAO.getActivitiesByUserByBDEWithStates(userId, bdeId, states);
		return list;
	}
	
	/**
	 * Recuperation des activites pouvant etre predecesseurs de l'activite passee en parametre
	 * @param activityId : l'activite dont on veut connaitre des dependances possibles
	 * @param bdeId : le projet de l'activité
	 * @return : liste des activites dont peut dependre l'activite passee en parametre
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getPossibleActivityPredecessors(Integer activityId,Integer bdeId)
			throws PersistanceException {
		Collection list = activityDAO.getPossiblePredecessors(activityId,bdeId);
		return list;
	}
	
	/**
	 * Recuperation des predecesseurs d'une activite
	 * @param activityId : l'activite dont on veut connaitre ses predecesseurs
	 * @return : liste des activite dont depend l'activite passee en parametre
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getPredecessors(Integer activityId) 
			throws PersistanceException {
		Collection listActivitySequences = activityDAO.getActivitySequencesPredecessors(activityId);
		
		Collection listPredecessors = new ArrayList();
		Iterator iter = listActivitySequences.iterator();
		
		while(iter.hasNext())
		{
			listPredecessors.add(((ActivitySequence)iter.next()).getPredecessor());
		}
		
		return listPredecessors;
	}
	
	/**
	 * Recuperation des successeurs d'une activite
	 * @param activityId : l'activite dont on veut connaitre ses successeurs
	 * @return : liste des activites dont depend l'activite passee en parametre
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getSuccessors(Integer activityId) 
			throws PersistanceException {
		Collection listActivitySequences = activityDAO.getActivitySequencesSuccessors(activityId);
		
		Collection listSuccessor = new ArrayList();
		Iterator iter = listActivitySequences.iterator();
		
		while(iter.hasNext())
		{
			listSuccessor.add(((ActivitySequence)iter.next()).getSuccessor());
		}
		
		return listSuccessor;
	}
	
	/**
	 * Recuperation des dependances pour lesquelles l'activite passee en parametre st successeurs
	 * @param activityId : l'activite dont on veut connaitre ses dependances entrantes
	 * @return : liste des des dependances pour lesquelles l'activite passee en parametre est successeur
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitySequencesPredecessors(Integer activityId) 
			throws PersistanceException {
		Collection list = activityDAO.getActivitySequencesPredecessors(activityId);
		return list;
	}
	
	/**
	 * Recuperation des dependances pour lesquelles l'activite passee en parametre est predecesseur
	 * @param activityId : l'activite dont on veut connaitre ses dependances sortantes
	 * @return : liste des des dependances pour lesquelles l'activite passee en parametre est predecesseur
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitySequencesSuccessors(Integer activityId) 
			throws PersistanceException {
		Collection list = activityDAO.getActivitySequencesSuccessors(activityId);
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
	public void saveActivityDependances(Integer activityId, Collection oldDependancesKeysList, Collection newDependancesKeysList)
	throws PersistanceException, DoublonException, ForeignKeyException {
		
		Collection dependancesToAddList = new ArrayList(newDependancesKeysList);
		dependancesToAddList.removeAll(oldDependancesKeysList);
		
		Collection dependancesToRemoveList = new ArrayList(oldDependancesKeysList);
		dependancesToRemoveList.removeAll(newDependancesKeysList);
		
		
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
			ActivitySequence newActivitySequence = new ActivitySequence();
			newActivitySequence.setPredecessor(predecessor);
			newActivitySequence.setSuccessor(successor);
			newActivitySequence.setLinkType(linkType);
			activitySequenceManager.insert(newActivitySequence);
		}
		
	}
	
	public Activity getActivityWithDependances(Integer activityId) throws PersistanceException {
		Activity activity = activityDAO.getActivityById(activityId);
		activity.setListPredecessors(ActivityManager.getInstance().getActivitySequencesPredecessors(activityId));
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
	 * @return retourne l'?tat dans lequel peut changer l'activit?, null si elle ne peut pas changer d'etat
	 * @throws PersistanceException
	 */
	public String verifChangeStateActivity(Activity activity) throws PersistanceException {
		boolean result = true;
		String state = null;
		Iterator iter;
		String activityState = activity.getState().getName();
		
		// R?cup?ration de la liste des ActivitySequence de l'activite
		Collection activitySeq = ActivityManager.getInstance().getActivitySequencesPredecessors((Integer) activity.getId());
		
		
		////////////////////////////////////////////////////////////////////////////////////
		
		
		// Si l'activit? est Created
		// Et qu'elle veut pouvoir commencer
		if (activityState.equals(BusinessConstantes.ACTIVITY_STATE_CREATED)) {
			iter = activitySeq.iterator();
			
			// Il faut v?rifier dans ses predecesseurs
			while ((iter.hasNext())&&(result==true))
				result = verifPredecessorState((ActivitySequence)iter.next(),BusinessConstantes.LINK_TYPE_FINISH_TO_START,BusinessConstantes.LINK_TYPE_START_TO_START);
		
			if (result==true) {
				state  = BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS;
			}
		}
		
		
		//////////////////////////////////////////////////////////////////////////////////
		
		
		// Si l'activit? est In Progress
		// Et qu'elle veut pouvoir finir
		if (activityState.equals(BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS)) {
			iter = activitySeq.iterator();
			
			// Il faut v?rifier dans ses predecesseurs
			while ((iter.hasNext())&&(result==true))
				result = verifPredecessorState((ActivitySequence)iter.next(),BusinessConstantes.LINK_TYPE_FINISH_TO_FINISH,BusinessConstantes.LINK_TYPE_START_TO_FINISH);
		
			if (result==true) {
				state  = BusinessConstantes.ACTIVITY_STATE_FINISHED;
			}
		}
		
		return state;
	}
	
	/**
	 * 
	 * @param userId
	 * @return retourne ? l'utilisateur, l'ensemble de ses activit?s dont il peut changer l'?tat
	 * @throws PersistanceException 
	 */
	public Collection activitiesChangeState(Integer userId, Integer bdeId) throws PersistanceException {
		Activity act;
		
		// Liste des activites pouvant changer d'?tat
		Collection listActivitiesChangeState = new ArrayList();
		
		// Recuperation des activites de l'utilisateur
		Collection listActivities = getRemainingActivitiesByUserByBDE(userId, bdeId);
		
		// Pour chacune d'entre elles, on v?rifie si elle peut changer d'etat
		Iterator iter = listActivities.iterator();
		while (iter.hasNext()) {
			act = (Activity)iter.next();
			if (verifChangeStateActivity(act)!=null)
				listActivitiesChangeState.add(act);
		}
		
		return listActivitiesChangeState;
	}
	
	/**
	 * 
	 * Cette m?thode permet  de supprimer une activit?.
	 * Pour cela il faut?galement supprimer toutes les relations d'une activit? avec les autres.
	 * 
	 * On supprime TOUS les liens relatifs ? l'activit? (mode barbare)
	 * 
	 * @param activityId
	 * @return 
	 * @throws PersistanceException, ForeignKeyException
	 */
	
	public void deleteLinksFromActivity(Integer activityId) throws PersistanceException, ForeignKeyException{
		
		// listes des predecesseurs et des successeurs
		Collection PredecessorsList = null ;
		Collection SuccessorsList	= null ;

		// chargement de l'activit?
		Activity activity = ActivityManager.getInstance().getActivityById(activityId);
			
		// on recupere toutes les activit?s pr?cedentes
	PredecessorsList =ActivityManager.getInstance().getPredecessors(activityId);
	
		// puis on supprime les liens avec predecesseurs via un iterateur
	Iterator iter = PredecessorsList.iterator();
	while (iter.hasNext()) {
		Activity pred = (Activity) iter.next();
		
		// appel a une methode du manager de activitySequence
		ActivitySequenceManager.getInstance().removeActivitySequence(pred,activity);
	}
	
	// on recupere les sequences des dependances suivantes
	SuccessorsList =ActivityManager.getInstance().getSuccessors(activityId);
	
	//  puis on supprime les liens avec les successeurs		
	Iterator iter2 = SuccessorsList.iterator();
	while (iter2.hasNext()) {
		Activity succ = (Activity) iter2.next();
		ActivitySequenceManager.getInstance().removeActivitySequence(activity,succ);
	}


	// ... et on finit par supprimes l'activit?
	ActivityManager.getInstance().delete(activity);
	
	}
}