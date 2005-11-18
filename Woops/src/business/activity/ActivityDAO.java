package business.activity;

import java.util.Collection;

import business.hibernate.ObjetPersistantDAO;
import business.hibernate.exception.PersistanceException;

public class ActivityDAO extends ObjetPersistantDAO {

	public Collection getListActivitiesByUser(Integer idUser) throws PersistanceException {

		return executeQuery("FROM Activity as act WHERE act.user=" + idUser.toString());
	}
	
	
}