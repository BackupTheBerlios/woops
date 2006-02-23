package business.activity.sequence;

import java.util.Collection;
import java.util.List;

import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;

public class ActivitySequenceDAO extends PersistentObjectDAO {
	
	/**
	 * R?cup?ration des sequences d'activit?s d'un projet
	 * @param bdeId : identifiant du projet
	 * @return : Liste des sequences d'activit?s du projet
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitySequencesByBDE(Integer bdeId) throws PersistanceException {
		//Constitution de la requ?te  
		StringBuffer query = new StringBuffer();
		query.append("FROM ActivitySequence as actSeq");
		query.append(" WHERE actSeq.predecessor.bdeId = "+bdeId);
		
		// R?cup?ration des donn?es
		List listActivitySequences = executeQuery(query.toString());
		return listActivitySequences;
	}
	
	
	
}