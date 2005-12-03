package business.activity;

import java.util.Collection;
import java.util.List;

import business.BusinessConstantes;
import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;

public class ActivityDAO extends PersistentObjectDAO {
	/**
	 * 
	 * @param activityId : l'id de l'activit� que l'on veut r�cup�rer
	 * @return l'activit�
	 * @throws PersistanceException
	 */
	public Activity getActivityById(Integer activityId) throws PersistanceException {
		List res = executeQuery("FROM Activity as act WHERE act.id = "+activityId);
		return (Activity)res.get(0);
		//return (Activity)get(Activity.class,activityId);
	}
	
	
	/**
	 * R�cup�ration des activit�s pour lesquelles le participant a la responsabilit�
	 * @param userId : identifiant du participant
	 * @return : Liste des activit�s du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r�cup�ration des donn�es
	 */
	public Collection getActivitiesByUser(Integer userId) throws PersistanceException {
		List listActivities = executeQuery("FROM Activity as act WHERE act.userId = "+ userId);
		return listActivities;
	}
	
	/**
	 * @param activityId : l'activit� dont on veut connaitre des pr�d�cesseurs possibles
	 * @return la liste des activit�s dont peut d�pendre l'activit� pass�e en parametre
	 * @throws PersistanceException
	 */
	public Collection getPossiblePredecessors(Integer activityId) 
			throws PersistanceException {
		StringBuffer query = new StringBuffer();
		query.append("FROM Activity as act WHERE act.id <> "+activityId);

		return executeQuery(query.toString());
	}
	
	
	/**
	 * @param activityId : l'activit� dont on veut connaitre ses d�pendances
	 * @return la liste des s�quence d'activit� dont l'activit� pass�e en parametre et le successeur
	 * @throws PersistanceException
	 */
	public Collection getActivitySequences(Integer successorId) 
		throws PersistanceException {
		
		StringBuffer query = new StringBuffer();
		query.append("FROM ActivitySequence as actSeq "); 
		query.append("WHERE actSeq.successor.id = "+successorId);
		
		return executeQuery(query.toString());
			
	}
}