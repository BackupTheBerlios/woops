package business.user;

import java.io.File;
import java.util.Collection;
import java.util.List;

import business.hibernate.HibernateSessionFactory;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.PersistanceException;

public class UserManager extends PersistentObjectManager {
	
	private UserDAO dao = new UserDAO();	
	
	private static UserManager instance;
	
	/**
	 * Singleton -> Constructeur priv?
	 */
	private UserManager() {}

	public static UserManager getInstance() {
		if (instance == null) {
			synchronized (UserManager.class) {
				instance = new UserManager();
			}
		}
		return instance;
	}
	
	public User getUser(String login) throws PersistanceException {
		User user = dao.get(login);		
		return user;
	}
	

	/**
	 * Cette methode controle la validit? du couple login/mot de passe
	 * @param login
	 * @param password
	 * @return
	 * @throws PersistanceException
	 */
	public User isLoginValid(String login, String password) throws PersistanceException {
		User user = dao.getUser(login,password);
		
		return user;
	}	
	
	/**
	 * Retourne la liste des utilisateur d'un projet
	 * @param projectId l'id du projet
	 * @return la liste des utilisateur d'un projet
	 * @throws PersistanceException 
	 */
	public Collection getUsersByProject(Integer projectId) throws PersistanceException {
		return dao.getUsersByProject(projectId);
	}


	// TESTS //
	public static void main(String[] args) {
		
		File f = new File("/users/iupisi/m1isi23/eclipse/workspace/Woops/src/hibernate.cfg.xml");
		HibernateSessionFactory.init(f);
		
		try {
			List boubou = UserManager.getInstance().getList("User");
			if (boubou==null)
				System.out.println("User doesn't exist !");
			else
				System.out.println("It's ok man");
		}
		catch (PersistanceException pe) {
			pe.printStackTrace();
		}
	
	}

}