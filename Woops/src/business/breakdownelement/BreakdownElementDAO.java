package business.breakdownelement;

import java.util.Collection;
import java.util.List;

import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;

public class BreakdownElementDAO extends PersistentObjectDAO{
	
	/**
	 * Fournit une entit� par rapport � son identifiant
	 * @param bdeId identifiant de l'entit�
	 * @return Entit� correspondante
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la r�cup�ration des donn�es
	 */
	public BreakdownElement getBreakDownElementById(Integer bdeId) throws PersistanceException {
		List res = executeQuery("FROM BreakdownElement as bde WHERE bde.id = "+bdeId);
		return (BreakdownElement)res.get(0);
	}
	
	/**
	 * Fournit tous les types d'entit�
	 * @return : liste de types
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la r�cup�ration des donn�es
	 */
	public Collection getBreakDownElementKinds() throws PersistanceException {
		return executeQuery("FROM BreakdownElementKind as bdek");
	}
}
