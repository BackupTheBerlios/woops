package business.chocolat;

import java.io.File;
import java.util.Collection;

import business.hibernate.HibernateSessionFactory;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.PersistanceException;

public class ChocolatManager extends PersistentObjectManager {
	
	private ChocolatDAO dao = new ChocolatDAO();	
	
	private static ChocolatManager instance;
	
	/**
	 * Singleton -> Constructeur privé
	 */
	private ChocolatManager() {}

	public static ChocolatManager getInstance() {
		if (instance == null) {
			synchronized (ChocolatManager.class) {
				instance = new ChocolatManager();
			}
		}
		return instance;
	}
		
	public Chocolat getChocolat(Integer ID) throws PersistanceException {
		Chocolat chocolat = dao.get(ID);		
		return chocolat;
	}	

	
	public Collection listeChocolats() throws PersistanceException {
		Collection liste = dao.getListeChocolats();
		return liste;
	}	
	
	public Chocolat jeVeuxQueLeNomEtLesCaloriesDuChocolatPourFairePlaisirAJulien(Integer ID) throws PersistanceException {
		Chocolat chocolat = dao.getjeVeuxQueLeNomEtLesCaloriesDuChocolatPourFairePlaisirAJulien(ID);		
		return chocolat;
	}	

	

	// TESTS //
	public static void main(String[] args) {
		
		File f = new File("C:/Abeilles/Travail/eclipse 3.1/workspace/Woops/src/hibernate.cfg.xml");
		HibernateSessionFactory.init(f);
		
		try {
			Chocolat chocolat = ChocolatManager.getInstance().jeVeuxQueLeNomEtLesCaloriesDuChocolatPourFairePlaisirAJulien(new Integer(2));
			chocolat.toString();
		}
		catch (PersistanceException pe) {
			
		}
	
	}


}
