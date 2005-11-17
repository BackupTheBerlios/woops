package business.activity;

import java.util.Collection;

import business.hibernate.ObjetPersistantDAO;
import business.hibernate.exception.PersistanceException;

public class ActivityDAO extends ObjetPersistantDAO {

	public Collection getListActivitiesByUser(Integer idUser) throws PersistanceException {
		
		Object[] params = new Integer[1];
		params[0] = idUser;

		return executeQuery("SELECT name,details FROM Activity WHERE user=?",params);
	}
	
}