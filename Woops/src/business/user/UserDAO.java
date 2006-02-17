package business.user;

import java.util.ArrayList;
import java.util.Collection;

import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;
import business.security.Roles;

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
	 * Retourne la liste des utilisateur d'un projet
	 * @param projectId L'id du projet
	 * @return la liste des utilisateur d'un projet
	 * @throws PersistanceException 
	 */
	public Collection getUsersByProject(Integer projectId) throws PersistanceException {
		StringBuffer req = new StringBuffer("from User as u, UserBDE as ubde ");
		req.append("where ubde.project.id = "+projectId);
		req.append("where u.role.name <> '"+Roles.ADMINISTRATOR_ROLE+"'");
		return executeQuery(req.toString());
	}
	
}
