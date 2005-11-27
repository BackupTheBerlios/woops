package business.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import business.hibernate.ObjetPersistantDAO;
import business.hibernate.exception.PersistanceException;

public class ActivitySequenceDAO extends ObjetPersistantDAO {
	/**
	 * R�cup�ration des activit�s pour lesquelles le participant a la responsabilit�
	 * @param userId : identifiant du participant
	 * @return : Liste des activit�s du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r�cup�ration des donn�es
	 */
	public Collection getActivitiesByUser(Integer userId) throws PersistanceException {
		List listActivities;
		List listResultQuery;
		Object[] activityItem;
		Activity activity;
		
		/* R�cup�ration de l'idetifiant, le nom et le d�tail de chaque activit� du participant */
		listResultQuery = executeQuery("SELECT act.id, act.name, act.details FROM Activity as act WHERE act.userId = 1");
		
		listActivities = new ArrayList();

		/* On parcourt de la liste */
		Iterator iter = listResultQuery.iterator();
		while (iter.hasNext()) {
			/* Reconstitution des activit�s � partir des valeurs r�cup�r�es */
			activityItem = (Object[]) iter.next();
			activity = new Activity();
			activity.setId((Integer)activityItem[0]);
			activity.setName((String) activityItem[1]);
			activity.setDetails((String) activityItem[2]);
			
			/* Ajout de chaque activit� dans la liste */
			listActivities.add(activity);
		}
		return listActivities;
	}
	
	/**
	 * @param activityId : l'activit� dont on veut connaitre des d�pendances possibles
	 * @return la liste des activit� dont peut d�pendre l'activit� pass�e en parametre
	 * @throws PersistanceException
	 */
	public Collection getPossibleActivityDependances(Integer activityId) 
			throws PersistanceException {
		return executeQuery("FROM Activity as act WHERE act.id <> "+activityId);
	}
	
	
	/**
	 * @param activityId : l'activit� dont on veut connaitre ses d�pendances
	 * @return la liste des activit� dont depend l'activit� pass�e en parametre
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