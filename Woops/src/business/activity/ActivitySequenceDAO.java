package business.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import business.hibernate.ObjetPersistantDAO;
import business.hibernate.exception.PersistanceException;

public class ActivitySequenceDAO extends ObjetPersistantDAO {
	/**
	 * Récupération des activités pour lesquelles le participant a la responsabilité
	 * @param userId : identifiant du participant
	 * @return : Liste des activités du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la récupération des données
	 */
	public Collection getActivitiesByUser(Integer userId) throws PersistanceException {
		List listActivities;
		List listResultQuery;
		Object[] activityItem;
		Activity activity;
		
		/* Récupération de l'idetifiant, le nom et le détail de chaque activité du participant */
		listResultQuery = executeQuery("SELECT act.id, act.name, act.details FROM Activity as act WHERE act.userId = 1");
		
		listActivities = new ArrayList();

		/* On parcourt de la liste */
		Iterator iter = listResultQuery.iterator();
		while (iter.hasNext()) {
			/* Reconstitution des activités à partir des valeurs récupérées */
			activityItem = (Object[]) iter.next();
			activity = new Activity();
			activity.setId((Integer)activityItem[0]);
			activity.setName((String) activityItem[1]);
			activity.setDetails((String) activityItem[2]);
			
			/* Ajout de chaque activité dans la liste */
			listActivities.add(activity);
		}
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