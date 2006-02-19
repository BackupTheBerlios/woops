package business.breakdownelement;

import java.io.Serializable;
import java.util.Collection;

import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.PersistanceException;

public class BreakdownElementManager extends PersistentObjectManager {
	
	/** Instance permettant d'assurer la persistance d'une entit� */
	private BreakdownElementDAO breakdownElementDAO = new BreakdownElementDAO();
	
	/** Instance priv�e de la classe */
	private static BreakdownElementManager breakdownElementManager;

	

	/**
	 * Impl�mentation du pattern Singleton : constructeur priv�
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
	 * Fournit une entit� par rapport � son identifiant
	 * @param bdeId identifiant de l'entit�
	 * @return Entit� correspondante
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la r�cup�ration des donn�es
	 */
	public BreakdownElement getBreakDownElementById(Integer bdeId) throws PersistanceException {
		return breakdownElementDAO.getBreakDownElementById(bdeId);
	}
	
	/**
	 * Fournit tous les types d'entit�
	 * @return : liste de types
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la r�cup�ration des donn�es
	 */
	public Collection getBreakDownElementKinds() throws PersistanceException {
		return breakdownElementDAO.getBreakDownElementKinds();
	}
	
	/**
	 * Fournit toutes les entit�s sur lesquelles le participant est affect�
	 * @param userId : identifiant de l'utilisateur
	 * @return : liste des entit�s
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la r�cup�ration des donn�es
	 */
	public Collection getBreakDownElementsByUser(Integer userId) throws PersistanceException {
		return breakdownElementDAO.getBreakDownElementsByUser(userId);
	}
	
	/**
	 * Affecte des participants � une entite
	 * @param bde : entite
	 * @return : identifiant de l'entite
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de l'affectation
	 */
	public Serializable affectUsersToBDE(BreakdownElement bde) throws PersistanceException {
		return breakdownElementDAO.affectUsersToBDE(bde);
	}
}
