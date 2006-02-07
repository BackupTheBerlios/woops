/**
 * 
 */
package action;

import java.io.File;

import servletunit.struts.MockStrutsTestCase;
import view.logon.LoginForm;

public class WoopsActionTest extends MockStrutsTestCase {
	protected LoginForm loginForm;
	
	/**
	 * Constructeur vide
	 */
	public WoopsActionTest() {
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
		
		// On se connecte � l'application
		setRequestPathInfo("/loginUser");
		// On constitue le form et on initialise les param�tres
		loginForm = new LoginForm();
		loginForm.setLogin("bernard");
		loginForm.setPassword("bernard");
		// On fournit l'actionForm � l'action
		setActionForm(loginForm);
		
		// On execute l'action
		actionPerform();
	}
}
