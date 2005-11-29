package business.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import business.hibernate.ObjetPersistantDAO;
import business.hibernate.exception.PersistanceException;

public class ActivityDAO extends ObjetPersistantDAO {
	
	/**
	 * 
	 * @param activityId : l'id de l'activité que l'on veut récupérer
	 * @return l'activité
	 * @throws PersistanceException
	 */
	public Activity getActivityById(Integer activityId) throws PersistanceException {
		List res = executeQuery("FROM Activity as act WHERE act.id = "+activityId);
		return (Activity)res.get(0);
		//return (Activity)get(Activity.class,activityId);
	}
	
	
	/**
	 * Récupération des activités pour lesquelles le participant a la responsabilité
	 * @param userId : identifiant du participant
	 * @return : Liste des activités du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la récupération des données
	 */
	public Collection getActivitiesByUser(Integer userId) throws PersistanceException {
		List listActivities = executeQuery("FROM Activity as act WHERE act.userId = "+ userId);
		return listActivities;
	}
	
	/**
	 * @param activityId : l'activité dont on veut connaitre des dépendances possibles
	 * @return la liste des activité dont peut dépendre l'activité passée en parametre
	 * @throws PersistanceException
	 */
	public Collection getPossibleActivityDependances(Integer activityId) 
			throws PersistanceException {
		return executeQuery("FROM Activity as act WHERE act.id <> "+activityId);
	}
	
	
	/**
	 * @param activityId : l'activité dont on veut connaitre ses dépendances
	 * @return la liste des activité dont depend l'activité passée en parametre
	 * @throws PersistanceException
	 */
	public Collection getActivityDependances(Integer activityId) 
		throws PersistanceException {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT actSeq.predecessor ");
		query.append("FROM ActivitySequence as actSeq "); 
		query.append("WHERE actSeq.successor.id = "+activityId);
		
		return executeQuery(query.toString());
			
	}
}