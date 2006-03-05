/**
 * 
 */
package business.event;

import java.util.Collection;

import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;

/**
 * @author Simon REGGIANI
 * Cette classe permet de g?rer les acces ? la base de donn?e
 */
public class EventDAO extends PersistentObjectDAO {
	
	/**
	 * Permet de r�cup�rer la liste des evenements pour un projet (pass�s ou non)
	 * @param bdeId : l'id du projet
	 * @param occured : boolean indiquant si on veut r�cup�rer les evenements pass�s ou non
	 * @return la liste
	 * @throws PersistanceException 
	 */
	public Collection getEventsByBde(Integer bdeId, boolean occured) throws PersistanceException {
		String query = 	"FROM Event evt" +
						" WHERE evt.bde = " + bdeId +
						" AND evt.occured = '" + (occured?"oui":"non") + "'";
		return executeQuery(query);
	}
}
