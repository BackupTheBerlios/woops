package business.breakdownelement;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.sequence.ActivitySequence;
import business.activity.sequence.ActivitySequenceManager;
import business.activity.state.CreatedActivityState;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.UserManager;

public class BreakdownElementManager extends PersistentObjectManager {
	
	/** Instance permettant d'assurer la persistance d'une entité */
	private BreakdownElementDAO breakdownElementDAO = new BreakdownElementDAO();
	
	/** Instance privée de la classe */
	private static BreakdownElementManager breakdownElementManager;

	

	/**
	 * Implémentation du pattern Singleton : constructeur privé
	 */
	private BreakdownElementManager() {
	}

	/**
	 * Fournit l'instance de la classe
	 * @return BreakdownElementManager : instance de la classe
	 */
	public static BreakdownElementManager getInstance() {
		if (breakdownElementManager == null) {
			synchronized (BreakdownElementManager.class) {
				breakdownElementManager = new BreakdownElementManager();
			}
		}
		return breakdownElementManager;
	}
	/**
	 * Fournit une entité par rapport à son identifiant
	 * @param bdeId identifiant de l'entité
	 * @return Entité correspondante
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public BreakdownElement getBreakDownElementById(Integer bdeId) throws PersistanceException {
		return breakdownElementDAO.getBreakDownElementById(bdeId);
	}
	
	/**
	 * Fournit une entité par rapport à son identifiant avec les users associés
	 * @param bdeId identifiant de l'entité
	 * @return Entité correspondante
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public BreakdownElement getBreakDownElementByIdWithUsers(Integer bdeId) throws PersistanceException {
		BreakdownElement bde = breakdownElementDAO.getBreakDownElementById(bdeId);
		UserManager userMngr = UserManager.getInstance();
		bde.setUsers((Set)userMngr.getUsersByBDE(bdeId));
		return bde;
	}
	
	/**
	 * Fournit tous les types d'entité
	 * @return : liste de types
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getBreakDownElementKinds() throws PersistanceException {
		return breakdownElementDAO.getBreakDownElementKinds();
	}
	
	/**
	 * Fournit toutes les entités sur lesquelles le participant est affecté
	 * @param userId : identifiant de l'utilisateur
	 * @return : liste des entités
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getBreakDownElementsByUser(Integer userId) throws PersistanceException {
		return breakdownElementDAO.getBreakDownElementsByUser(userId);
	}
	
	/**
	 * Affecte des participants à une entite
	 * @param bde : entite
	 * @return : identifiant de l'entite
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de l'affectation
	 */
	public Serializable affectUsersToBDE(BreakdownElement bde) throws PersistanceException {
		return breakdownElementDAO.affectUsersToBDE(bde);
	}
	
	
	/**
	 * Copie un projet (particpants et activités) dans un autre.
	 * @param srcBde : le projet source
	 * @param destBde : le projet destination (seuls les infos primaires doivent être renseignées) 
	 * @return : identifiant du projet copié
	 * @throws PersistanceException
	 * @throws DoublonException 
	 */
	public Serializable copyBreakdownElement(BreakdownElement srcBde, BreakdownElement destBde) throws PersistanceException, DoublonException {
		// affectation des utilisateurs au nouveau projet
		destBde.setUsers(srcBde.getUsers());
		
		// insertion en bd
		destBde.setId((Integer)breakdownElementDAO.insert(destBde));
		
		// Récupération de toutes les activités du projet source
		ActivityManager actMngr = ActivityManager.getInstance();
		Collection listActivities = actMngr.getAllActivitiesByBDE((Integer)srcBde.getId());
		
		// On va utiliser une map pour associer les activités sources et destination
		// pour pouvoir ensuite créer les activity sequences correspondantes
		HashMap mapActSrcDest = new HashMap();
		
		// Copie de ces activités en les mettant libres et dans l'état créée
		Iterator iterAct = listActivities.iterator();
		Activity actSrc;
		Activity actDest;
		while(iterAct.hasNext()) {
			// copie
			actSrc = (Activity)iterAct.next();
			actDest = (Activity)actSrc.clone();
			actDest.setUserId(null);
			actDest.setState(new CreatedActivityState());
			actDest.setBdeId((Integer)destBde.getId());
			
			// insertion en bd
			actDest.setId(actMngr.insert(actDest));
			
			// correspondance dans la map
			mapActSrcDest.put(actSrc.getId(),actDest.getId());
		}
		
		
		// Récupération de toutes les dépendances du projet source
		ActivitySequenceManager actSeqMngr = ActivitySequenceManager.getInstance();
		Collection listActSeq = actSeqMngr.getActivitySequencesByBDE((Integer)srcBde.getId());
		
		// Copie des dépendances
		Iterator iterActSeq = listActSeq.iterator();
		ActivitySequence actSeqSrc;
		ActivitySequence actSeqDest;
		Activity predecessor = new Activity();
		Activity successor = new Activity();
		while(iterActSeq.hasNext()) {
			// copie
			actSeqSrc = (ActivitySequence)iterActSeq.next();
			actSeqDest = (ActivitySequence)actSeqSrc.clone();
			
			// correspondances des activités source et destination
			predecessor.setId((Integer)mapActSrcDest.get(actSeqSrc.getPredecessor().getId()));
			successor.setId((Integer)mapActSrcDest.get(actSeqSrc.getSuccessor().getId()));
			
			actSeqDest.setPredecessor(predecessor);
			actSeqDest.setSuccessor(successor);
			
			// insertion en bd
			actSeqDest.setId(actSeqMngr.insert(actSeqDest));
		}
		
		return (Serializable)destBde.getId();
	}
}
