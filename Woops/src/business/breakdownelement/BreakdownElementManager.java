package business.breakdownelement;

import java.io.Serializable;
import java.util.Collection;

import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.PersistanceException;

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
}
