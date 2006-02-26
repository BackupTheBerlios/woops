package business.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import business.breakdownelement.BreakdownElement;
import business.hibernate.HibernateSessionFactory;
import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;

public class UserDAO extends PersistentObjectDAO {

	public User get(String login) throws PersistanceException {
		StringBuffer req = new StringBuffer("select u.login from User as u ") ;
        req.append("where ");
        req.append("u.login='" + login +"'");

	    User user = (User) executeQuery(req.toString());
	    
	    return user;
	}

	/**
	 * @param login
	 * @param password
	 * @return
	 * @throws PersistanceException
	 */
	public User getUser(String login, String password) throws PersistanceException {
		User user = null;
		
		StringBuffer req = new StringBuffer("from User as u ") ;
        req.append("where ");
        req.append("u.login='" + login +"' ");
        req.append("and ");
        req.append("u.password='" + password +"'");

	    List list = (ArrayList) executeQuery(req.toString());
	    
	
	    if (list.size()!=0)
	        user = (User) list.get(0);
	    
	    return user;
	}	
	/**
	 * permet d'obtenir un particpant a partir de son id
	 * @param identifiant
	 * @return User
	 * @throws PersistanceException
	 */
	public User getUser(Integer id) throws PersistanceException {
		User user = null;
		
		StringBuffer req = new StringBuffer("from User as u ") ;
        req.append("where ");
        req.append("u.id='" + id +"' ");

	    List list = (ArrayList) executeQuery(req.toString());
	    
	    if (list.size()!=0)
	        user = (User) list.get(0);
	    
	    return user;
	}
	
	/**
	 * Fournit tous les participants de l'entité
	 * @param bdeId : identifiant de l'entité
	 * @return : liste des participants
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getUsersByBDE(Integer bdeId) throws PersistanceException {
		Session session = null ;
		Transaction transaction = null;
		BreakdownElement bde = null;
		
		try {
			session = HibernateSessionFactory.currentSession();
		
			transaction = session.beginTransaction();
			
			// Récupération du projet avec ses participants 
			Query query = session.createQuery("SELECT bde FROM BreakdownElement bde left join fetch bde.users WHERE bde.id = :bdeId");
	        query.setParameter("bdeId", bdeId);
	        bde = (BreakdownElement) query.uniqueResult();
	        
	    	transaction.commit();

		} catch (HibernateException he) {
			throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}

    	return bde.getUsers();
	}
}
