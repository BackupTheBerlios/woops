package business.activity;

import java.util.Collection;
import java.util.List;

import business.BusinessConstantes;
import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;

public class ActivityDAO extends PersistentObjectDAO {
	
	/**
	 * Recuperation d'une activite
	 * @param activityId : identififiant de l'activite 
	 * @return : activite correspondante
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donn?es
	 */
	public Activity getActivityById(Integer activityId) throws PersistanceException {
		List res = executeQuery("FROM Activity as act WHERE act.id = "+activityId);
		return (Activity)res.get(0);
	}
	
	/**
	 * Recuperation des activites pour lesquelles le participant a la responsabilite
	 * @param userId : identifiant du participant
	 * @param states : états des activités
	 * @return : Liste des activites du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitiesByUserWithStates(Integer userId, String[] states) throws PersistanceException {
		// Constitution de la requete en tenant compte du participant et de l'entite passes en parametre 
		StringBuffer query = new StringBuffer();
		query.append("FROM Activity as act WHERE act.userId = " + userId);  
		
		//On precise les etats a prendre en compte
		if (states.length != 0) {
			query.append(" AND ( ");
		
			// Selection des etats a prendre en compte
			for(int i = 0; i < states.length; i++) {
				query.append("act.state.name='"+ states[i] +"'");
				if (i != states.length - 1) query.append(" OR ");
			}
		
			query.append(" )");
		}
		
		// Recuperation des donnees
		return executeQuery(query.toString());
	}
	
	/**
	 * Recuperation des activites pour lesquelles le participant a la responsabilite
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @param states : états des activités
	 * @return : Liste des activites du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitiesByUserByBDEWithStates(Integer userId, Integer bdeId, String[] states) throws PersistanceException {
		// Constitution de la requete en tenant compte du participant et de l'entite passes en parametre 
		StringBuffer query = new StringBuffer();
		query.append("FROM Activity as act WHERE act.userId = " + userId + " AND act.bdeId = " + bdeId);  
		
		//On precise les etats a prendre en compte
		if (states.length != 0) {
			query.append(" AND ( ");
		
			// Selection des etats a prendre en compte
			for(int i = 0; i < states.length; i++) {
				query.append("act.state.name='"+ states[i] +"'");
				if (i != states.length - 1) query.append(" OR ");
			}
		
			query.append(" )");
		}
		
		// Recuperation des donnees
		return executeQuery(query.toString());
	}
	
	
	/**
	 * Recuperation des activites d'une entite
	 * @param bdeId : identifiant de l'entite
	 * @return : Liste des activites correspondant au critere de recherche
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitiesByBDE(Integer bdeId) throws PersistanceException {
		return executeQuery("FROM Activity WHERE bdeId = " + bdeId);
	}
	
	
	/**
	 * Recuperation des activites libres sur une entite
	 * @param bdeId : identifiant de l'entite
	 * @return : liste des activites libres
	 * @throws PersistanceException 
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getFreeActivities(Integer bdeId) throws PersistanceException {
		StringBuffer query = new StringBuffer();
		query.append("FROM Activity as act WHERE act.userId is null AND act.bdeId = " + bdeId);
		
		// Recuperation des donnees
		List listActivities = executeQuery(query.toString());
		return listActivities;
	}
	
	
	/**
	 * Recuperation des activites pouvant etre predecesseurs de l'activite passee en parametre
	 * @param activityId : l'activite dont on veut connaitre des dependances possibles
	 * @return : liste des activites dont peut dependre l'activite passee en parametre
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getPossiblePredecessors(Integer activityId) 
			throws PersistanceException {
		StringBuffer query = new StringBuffer();
		
		query.append("FROM Activity as act WHERE act.id <> " + activityId);
		query.append(" AND act.state.name <> '" + BusinessConstantes.ACTIVITY_STATE_FINISHED + "'");
		
		return executeQuery(query.toString());
	}
	
	
	/**
	 * Recuperation des dependances pour lesquelles l'activite passee en parametre st successeurs
	 * @param activityId : l'activite dont on veut connaitre ses dependances entrantes
	 * @return : liste des des dependances pour lesquelles l'activite passee en parametre est successeur
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitySequencesPredecessors(Integer activityId) 
		throws PersistanceException {
		StringBuffer query = new StringBuffer();
		
		query.append("FROM ActivitySequence as actSeq "); 
		query.append("WHERE actSeq.successor.id = " + activityId);
		
		return executeQuery(query.toString());
			
	}
	

	/**
	 * Recuperation des dependances pour lesquelles l'activite passee en parametre est predecesseur
	 * @param activityId : l'activite dont on veut connaitre ses dependances sortantes
	 * @return : liste des des dependances pour lesquelles l'activite passee en parametre est predecesseur
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitySequencesSuccessors(Integer activityId) 
		throws PersistanceException {
		StringBuffer query = new StringBuffer();
		
		query.append("FROM ActivitySequence as actSeq "); 
		query.append("WHERE actSeq.predecessor.id = " + activityId);
		
		return executeQuery(query.toString());
	}
}