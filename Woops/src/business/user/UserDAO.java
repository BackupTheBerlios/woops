package business.user;

import java.util.ArrayList;

import business.hibernate.ObjetPersistantDAO;
import business.hibernate.exception.PersistanceException;

public class UserDAO extends ObjetPersistantDAO {

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
	
}
