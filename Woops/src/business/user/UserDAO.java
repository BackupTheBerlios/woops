package business.user;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.hibernate.HibernateException;
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

	    ArrayList list = (ArrayList) executeQuery(req.toString());
	    
	
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
		//return executeQuery("FROM User as u , UserBDE ubde WHERE u.id = ubde.user AND ubde.bde = " + bdeId);
		Session session;
		try {
			session = HibernateSessionFactory.currentSession();
		
	    Transaction transaction = session.beginTransaction();

	    BreakdownElement bde = (BreakdownElement) session
	            .createQuery("SELECT bde FROM BreakdownElement bde left join fetch bde.users WHERE bde.id = :bdeid")
	            .setParameter("bdeId", bdeId)
	            .uniqueResult(); // Eager fetch the collection so we can use it detached

	    	transaction.commit();
	    	return bde.getUsers();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			throw new PersistanceException(e.toString());
		}
		
	}
}
