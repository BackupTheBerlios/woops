package business.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import business.chocolat.Chocolat;
import business.chocolat.ChocolatManager;
import business.hibernate.HibernateSessionFactory;
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
	
	
	
//	 TESTS //
	public static void main(String[] args) {
		
		File f = new File("C:/Abeilles/Travail/eclipse 3.1/workspace/Woops/src/hibernate.cfg.xml");
		HibernateSessionFactory.init(f);
		
		try {
			ArrayList tab = (ArrayList) ActivityManager.getInstance().listActivities(1);
		}
		catch (PersistanceException pe) {
			String boubou = "toto";
		}
	
	}

	
	
}