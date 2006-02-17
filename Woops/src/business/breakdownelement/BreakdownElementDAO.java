package business.breakdownelement;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import business.hibernate.HibernateSessionFactory;
import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;
import business.user.User;

public class BreakdownElementDAO extends PersistentObjectDAO{
	
	/**
	 * Fournit une entité par rapport à son identifiant
	 * @param bdeId identifiant de l'entité
	 * @return Entité correspondante
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public BreakdownElement getBreakDownElementById(Integer bdeId) throws PersistanceException {
		List res = executeQuery("FROM BreakdownElement as bde WHERE bde.id = "+bdeId);
		return (BreakdownElement)res.get(0);
	}
	
	/**
	 * Fournit tous les types d'entité
	 * @return : liste de types
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getBreakDownElementKinds() throws PersistanceException {
		return executeQuery("FROM BreakdownElementKind as bdek");
	}
	
	/**
	 * Fournit toutes les entités sur lesquelles le participant est affecté
	 * @param userId : identifiant de l'utilisateur
	 * @return : liste des entités
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getBreakDownElementsByUser(Integer userId) throws PersistanceException {
		Session session;
		try {
			session = HibernateSessionFactory.currentSession();
		
	    Transaction transaction = session.beginTransaction();

	    User user = (User) session
	            .createQuery("SELECT u FROM User u left join fetch u.bdes WHERE u.id = :uid")
	            .setParameter("uid", userId)
	            .uniqueResult(); // Eager fetch the collection so we can use it detached

	    	transaction.commit();
	    	return user.getBdes();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			throw new PersistanceException(e.toString());
		}
		
	}
	
	public Serializable affectUsersToBDE(BreakdownElement bde) throws PersistanceException {
		Session session;
		try {
			session = HibernateSessionFactory.currentSession();
		
			Transaction transaction = session.beginTransaction();

			session.update(bde); // Reattachment of aPerson

			transaction.commit();
			return (Integer) bde.getId();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			throw new PersistanceException(e.toString());
		}
	}
}
