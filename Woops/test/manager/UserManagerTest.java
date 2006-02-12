package manager;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;
import view.PresentationConstantes;
import business.user.User;
import business.user.UserManager;
import business.user.UserRole;

public class UserManagerTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * Test method for 'business.user.UserManager.getUser(String)'
	 */
	public void testGetUser() {
		String login = new String("woops");
		User user = new User();
		
		try {
			
			user = UserManager.getInstance().getUser(login);
			
			// On v�rifie qu'on a r�cup�r� le bon user
			assertEquals(login, user.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.user.UserManager.isLoginValid(String, String)'
	 */
	public void testIsLoginValid() {
		User user = new User();
		String login = new String("woops");
		String pass = new String("woops");
		try {
			
			user = UserManager.getInstance().isLoginValid(login,pass);
			
			// On v�rifie qu'on a r�cup�r� le bon user
			assertEquals(login, user.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.insert(PersistentObject)'
	 */
	public void testInsertPersistentObject() {
		User user = new User();
		UserRole ur = new UserRole();
		ur.setCode("dev");
		
		user.setLogin("nico");
		user.setPassword("nico");
		user.setFirstName("nico");
		user.setLastName("nico");
		user.setRole(ur);
		
		try {
			
			UserManager.getInstance().insert(user);
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.update(PersistentObject)'
	 */
	public void testUpdatePersistentObject() {
		User user = new User();
		
		try {
			UserManager.getInstance().getUser("nico");
			user.setPassword("nico7");
			UserManager.getInstance().update(user);
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.getList(String)'
	 */
	public void testGetList() {
		try {
			Collection coll = new ArrayList();
			
			coll = UserManager.getInstance().getList(PresentationConstantes.TABLE_USER);
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
