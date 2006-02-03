/**
 * 
 */
package test.action;

import java.io.File;

import servletunit.struts.MockStrutsTestCase;
import view.PresentationConstantes;
import business.user.User;
import business.user.UserManager;

/**
 * @author Simon REGGIANI
 *
 */
public class TestActionWoops extends MockStrutsTestCase {

	/**
	 * Constructeur vide
	 */
	public TestActionWoops() {
		super();
	}

	/**
	 * Methode d'initialisation
	 */
	public void setUp() throws Exception {
		super.setUp();
		/* On precise le contexte /WebContent
		pour trouver le fichier /WEB-INF/web.xml */
		setContextDirectory(new File("WebContent"));
		// Récupération du User admin et on le place en session 
		User user = UserManager.getInstance().isLoginValid("woops", "woops");
		getSession().setAttribute(PresentationConstantes.KEY_USER, user);
	}
}
