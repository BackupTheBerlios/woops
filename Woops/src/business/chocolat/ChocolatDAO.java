package business.chocolat;

import java.util.Collection;
import java.util.List;

import business.hibernate.ObjetPersistantDAO;
import business.hibernate.exception.PersistanceException;

public class ChocolatDAO extends ObjetPersistantDAO {

	public Chocolat get(Integer ID) throws PersistanceException {
		Chocolat chocolat = (Chocolat) get(Chocolat.class,ID);
		return chocolat;
	}	

	public Collection getListeChocolats() throws PersistanceException {
   
		List liste = getListe("Chocolat");
		return liste;
	}

}


