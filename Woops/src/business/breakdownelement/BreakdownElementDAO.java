package business.breakdownelement;

import java.util.Collection;
import java.util.List;

import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;

public class BreakdownElementDAO extends PersistentObjectDAO{
	
	/**
	 * Fournit une entité par rapport à son identifiant
	 * @param bdeId identifiant de l'entité
	 * @return Entité correspondante
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public BreakdownElement getBreakDownElementById(Integer bdeId) throws PersistanceException {
		List res = executeQuery("FROM BreakdownElement as bde WHERE bde.id = "+bdeId);
		return (BreakdownElement)res.get(0);
	}
	
	/**
	 * Fournit tous les types d'entité
	 * @return : liste de types
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getBreakDownElementKinds() throws PersistanceException {
		return executeQuery("FROM BreakdownElementKind as bdek");
	}
}
