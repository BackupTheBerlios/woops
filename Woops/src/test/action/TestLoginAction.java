package test.action;

import java.io.File;

import business.user.User;

import servletunit.struts.MockStrutsTestCase;
import view.PresentationConstantes;
import view.logon.LoginForm;

public class TestLoginAction extends MockStrutsTestCase {
	private LoginForm loginForm;
	
	public TestLoginAction(String testName) {
		super(testName);
	}
	
	public void setUp() throws Exception {
		super.setUp();
		/* On pr�cise le contexte /WebContent
		pour trouver le fichier /WEB-INF/web.xml */
		setContextDirectory(new File("WebContent"));

	}
	
	public void testSuccessfullLogin() {
		setRequestPathInfo("/loginUser");
		
		//On constitue le form et on initialise les param�tres
		loginForm = new LoginForm();
		loginForm.setLogin("bernard");
		loginForm.setPassword("bernard");
		//On fournit l'actionForm � l'action
		setActionForm(loginForm);
		
		//On execute l'action
		actionPerform();

		// On v�rifie le User stock� en session
		User user = (User) getSession().getAttribute(PresentationConstantes.KEY_USER);
		assertNotNull(user);
        assertEquals("bernard", user.getLogin());
        assertEquals("bernard", user.getPassword());
        //On v�rifie qu'il n'y a pas d'erreurs et on s'assure que le forward correspond � ce qui est attendu
		verifyForward(user.getRole().getCode());
        verifyNoActionErrors();

	}
}