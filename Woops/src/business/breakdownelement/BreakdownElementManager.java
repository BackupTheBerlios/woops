package business.breakdownelement;

import java.util.Collection;

import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.PersistanceException;

public class BreakdownElementManager extends PersistentObjectManager {
	
	/** Instance permettant d'assurer la persistance d'une entit? */
	private BreakdownElementDAO breakdownElementDAO = new BreakdownElementDAO();
	
	/** Instance priv?e de la classe */
	private static BreakdownElementManager breakdownElementManager;

	

	/**
	 * Impl?mentation du pattern Singleton : constructeur priv?
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
	 * Fournit une entit? par rapport ? son identifiant
	 * @param bdeId identifiant de l'entit?
	 * @return Entit? correspondante
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la r?cup?ration des donn?es
	 */
	public BreakdownElement getBreakDownElementById(Integer bdeId) throws PersistanceException {
		return breakdownElementDAO.getBreakDownElementById(bdeId);
	}
	
	/**
	 * Fournit tous les types d'entit?
	 * @return : liste de types
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la r?cup?ration des donn?es
	 */
	public Collection getBreakDownElementKinds() throws PersistanceException {
		return breakdownElementDAO.getBreakDownElementKinds();
	}
}
