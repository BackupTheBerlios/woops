package business.activity.sequence;

import java.util.Collection;
import java.util.List;

import net.sf.hibernate.Session;
import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;

public class ActivitySequenceDAO extends PersistentObjectDAO {
	
	/**
	 * Recuperation des sequences d'activites d'un projet
	 * @param bdeId : identifiant du projet
	 * @return : Liste des sequences d'activites du projet
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitySequencesByBDE(Integer bdeId) throws PersistanceException {
		//Constitution de la requete  
		StringBuffer query = new StringBuffer();
		query.append("FROM ActivitySequence as actSeq");
		query.append(" WHERE actSeq.predecessor.bdeId = "+bdeId);
		
		// Recuperation des donnees
		List listActivitySequences = executeQuery(query.toString());
		return listActivitySequences;
	}

	
	
	
	
	/*********************
	*  Session en cours  *
	**********************/
	
	
	/**
	 * Recuperation des sequences d'activites d'un projet
	 * @param bdeId : identifiant du projet
	 * @param session : session permettant d'executer la requete
	 * @return : Liste des sequences d'activites du projet
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitySequencesByBDE(Integer bdeId, Session session) throws PersistanceException {
		//Constitution de la requete  
		StringBuffer query = new StringBuffer();
		query.append("FROM ActivitySequence as actSeq");
		query.append(" WHERE actSeq.predecessor.bdeId = "+bdeId);
		
		// Recuperation des donnees
		List listActivitySequences = executeQuery(query.toString() , session);
		return listActivitySequences;
	}
}