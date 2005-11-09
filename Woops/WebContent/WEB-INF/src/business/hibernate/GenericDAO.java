package business.hibernate;

import java.io.Serializable;

import business.hibernate.exception.PersistanceException;

/**
 * @author Nicolas Ricard
 * 
 */
public interface GenericDAO {
	
	public void save(ObjetPersistant objet) throws PersistanceException ;
	public void update(ObjetPersistant objet) throws PersistanceException;	
	public ObjetPersistant getForUpdate(Class classe, Serializable id) throws PersistanceException;		
	public void delete(ObjetPersistant objet) throws PersistanceException ;
	
}