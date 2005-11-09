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
public class ObjetPersistantManager {

    private ObjetPersistantDAO dao = new ObjetPersistantDAO();  
    
    /*private static ObjetPersistantManager instance;
    
    /**
     * Singleton -> Constructeur privé
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
    
    public void ajouter(ObjetPersistant objet) throws PersistanceException,DoublonException {
        dao.insert(objet);
    }

    public void ajouter(ObjetPersistant objet, Session session) throws HibernateException  {
        dao.insert(objet,session);
    }    
  
    
    public void modifier(ObjetPersistant objet) throws PersistanceException {
        dao.update(objet);
    }

    public void modifier(ObjetPersistant objet, Session session) throws HibernateException  {
        dao.update(objet,session);
    }    
    
    public void supprimer(ObjetPersistant objet) throws PersistanceException, ForeignKeyException {
        dao.delete(objet);
    }
    
    public void supprimer(ObjetPersistant objet, Session session) throws HibernateException, ForeignKeyException {
        dao.delete(objet,session);
    }

}
