package business.activity;

import java.util.Collection;
import java.util.List;

import business.BusinessConstantes;
import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;

public class ActivityDAO extends PersistentObjectDAO {
	/**
	 * 
	 * @param activityId : l'id de l'activit? que l'on veut r?cup?rer
	 * @return l'activit?
	 * @throws PersistanceException
	 */
	public Activity getActivityById(Integer activityId) throws PersistanceException {
		List res = executeQuery("FROM Activity as act WHERE act.id = "+activityId);
		return (Activity)res.get(0);
	}
	
	
	/**
	 * R?cup?ration des activit?s pour lesquelles le participant a la responsabilit?
	 * @param userId : identifiant du participant
	 * @return : Liste des activit?s du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitiesByUserWithStates(Integer userId, String[] states) throws PersistanceException {
		// Constitution de la requ?te en tenant compte du participant pass? en param?tre 
		StringBuffer query = new StringBuffer();
		query.append("FROM Activity as act WHERE act.userId = " + userId + "  AND ( ");
		
		// S?lection des ?tats ? prendre en compte
		for(int i = 0; i < states.length; i++) {
			query.append("act.state.name='"+ states[i] +"'");
			if (i != states.length-1) query.append(" OR ");
		}
		
		query.append(" )");
		
		// R?cup?ration des donn?es
		List listActivities = executeQuery(query.toString());
		return listActivities;
	}
	
	
	/**
	 * R?cup?ration des activit?s d'un projet
	 * @param userId : identifiant du projet
	 * @return : Liste des activit?s du projet
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitiesByProject(Integer projectId) throws PersistanceException {
		// Constitution de la requ?te  
		StringBuffer query = new StringBuffer();
		query.append("FROM Activity");
		
		// R?cup?ration des donn?es
		List listActivities = executeQuery(query.toString());
		return listActivities;
	}
	
	/**
	 * retourne les activities sans user
	 * @return
	 * @throws PersistanceException
	 */
	public Collection getFreeActivities() throws PersistanceException {
	
		StringBuffer query = new StringBuffer();
		query.append("FROM Activity as act WHERE act.userId is null");
		
		// R?cup?ration des donn?es
		List listActivities = executeQuery(query.toString());
		return listActivities;
	}
	
	/**
	 * @param activityId : l'activit? dont on veut connaitre des pr?d?cesseurs possibles
	 * @return la liste des activit?s dont peut d?pendre l'activit? pass?e en parametre
	 * @throws PersistanceException
	 */
	public Collection getPossiblePredecessors(Integer activityId) 
			throws PersistanceException {
		StringBuffer query = new StringBuffer();
		query.append("FROM Activity as act WHERE act.id <> "+activityId);
		query.append(" AND act.state.name <> '"+BusinessConstantes.ACTIVITY_STATE_FINISHED+"'");
		return executeQuery(query.toString());
	}
	
	
	/**
	 * @param activityId : l'activit? dont on veut connaitre ses d?pendances entrantes
	 * @return la liste des s?quence d'activit? dont l'activit? pass?e en parametre et le successeur
	 * @throws PersistanceException
	 */
	public Collection getActivitySequencesPredecessors(Integer activityId) 
		throws PersistanceException {
		
		StringBuffer query = new StringBuffer();
		query.append("FROM ActivitySequence as actSeq "); 
		query.append("WHERE actSeq.successor.id = "+activityId);
		
		return executeQuery(query.toString());
			
	}
	
	/**
	 * @param activityId : l'activit? dont on veut connaitre ses d?pendances sortantes
	 * @return la liste des s?quence d'activit? dont l'activit? pass?e en parametre et le predecesseur
	 * @throws PersistanceException
	 */
	public Collection getActivitySequencesSuccessors(Integer activityId) 
		throws PersistanceException {
		
		StringBuffer query = new StringBuffer();
		query.append("FROM ActivitySequence as actSeq "); 
		query.append("WHERE actSeq.predecessor.id = "+activityId);
		
		return executeQuery(query.toString());
			
	}
	

}