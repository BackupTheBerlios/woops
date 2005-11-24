package business.user;

import java.io.File;

import business.hibernate.HibernateSessionFactory;
import business.hibernate.exception.PersistanceException;

public class UserManager  {
	
	private UserDAO dao = new UserDAO();	
	
	private static UserManager instance;
	
	/**
	 * Singleton -> Constructeur privé
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
	 * Cette methode controle la validité du couple login/mot de passe
	 * @param login
	 * @param password
	 * @return
	 * @throws PersistanceException
	 */
	public User isLoginValid(String login, String password) throws PersistanceException {
		User user = dao.getUser(login,password);
		
		return user;
	}	
	


	// TESTS //
	public static void main(String[] args) {
		
		File f = new File("C:/Abeilles/Travail/eclipse 3.1/workspace/Woops/src/hibernate.cfg.xml");
		HibernateSessionFactory.init(f);
		
		try {
			User user = UserManager.getInstance().isLoginValid("toto","tutu");
			if (user==null)
				System.out.println("User doesn't exist !");
			else
				System.out.println("It's ok man");
		}
		catch (PersistanceException pe) {
			pe.printStackTrace();
		}
	
	}

}