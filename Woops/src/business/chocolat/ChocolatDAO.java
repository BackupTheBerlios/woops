package business.chocolat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;

public class ChocolatDAO extends PersistentObjectDAO {

	public Chocolat get(Integer ID) throws PersistanceException {
		Chocolat chocolat = (Chocolat) get(Chocolat.class,ID);
		return chocolat;
	}	

	public Collection getListeChocolats() throws PersistanceException {
   
		List liste = getList("Chocolat");
		return liste;
	}

	public Chocolat getjeVeuxQueLeNomEtLesCaloriesDuChocolatPourFairePlaisirAJulien(Integer ID) throws PersistanceException {
		Object toto[] = null; 
		
		StringBuffer req = new StringBuffer("select choc.nom, choc.calorie from Chocolat as choc ") ;
        req.append("where ");
        req.append("choc.id=" + ID);
		
	    ArrayList list = (ArrayList) executeQuery(req.toString());
	    
	    if (list.size()!=0) {
	    	toto = (Object[]) list.get(0);
	    }
		
	    Chocolat chocolat = new Chocolat();
	    chocolat.setNom((String) toto[0]);
	    chocolat.setCalorie(((Integer) toto[1]).intValue());
	    
		return chocolat;
	}	
	
}


