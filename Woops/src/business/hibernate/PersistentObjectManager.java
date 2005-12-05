package business.hibernate;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;

/**
 * @author Nicolas Ricard
 * 
 */
public class PersistentObjectManager {

    private PersistentObjectDAO dao = new PersistentObjectDAO();  
    
    /*private static ObjetPersistantManager instance;
    
    /**
     * Singleton -> Constructeur priv�
     */
    /*private ObjetPersistantManager() {}

    public static ObjetPersistantManager getInstance() {
        if (instance == null) {
            synchronized (ObjetPersistantManager.class) {
                instance = new ObjetPersistantManager();
            }
        }
        return instance;
    }*/

    public Session getSession() throws HibernateException  {
        return HibernateSessionFactory.currentSession();
    }

    public void closeSession(Session session) throws HibernateException  {
        if (session!=null && session.isOpen()) 
            HibernateSessionFactory.closeSession();
    }    
    
    public void insert(PersistentObject objet) throws PersistanceException,DoublonException {
        dao.insert(objet);
    }

    public void insert(PersistentObject objet, Session session) throws HibernateException  {
        dao.insert(objet,session);
    }    
  
    
    public void update(PersistentObject objet) throws PersistanceException {
        dao.update(objet);
    }

    public void update(PersistentObject objet, Session session) throws HibernateException  {
        dao.update(objet,session);
    }    
    
    public void delete(PersistentObject objet) throws PersistanceException, ForeignKeyException {
        dao.delete(objet);
    }
    
    public void delete(PersistentObject objet, Session session) throws HibernateException, ForeignKeyException {
        dao.delete(objet,session);
    }

}