package business.activity;

import java.util.Collection;

import business.hibernate.ObjetPersistantManager;
import business.hibernate.exception.PersistanceException;

public class ActivityManager extends ObjetPersistantManager {
	
	private ActivityDAO dao = new ActivityDAO();	
	
	private static ActivityManager instance;
	
	/**
	 * Singleton -> Constructeur privé
	 */
	private ActivityManager() {}

	public static ActivityManager getInstance() {
		if (instance == null) {
			synchronized (ActivityManager.class) {
				instance = new ActivityManager();
			}
		}
		return instance;
	}
		
	
	public Collection listActivities(Integer userId) throws PersistanceException {
		Collection list = dao.getListActivitiesByUser(userId);
		return list;
	}	
}