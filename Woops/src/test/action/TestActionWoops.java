/**
 * 
 */
package test.action;

import java.io.File;

import servletunit.struts.MockStrutsTestCase;

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
		/* On pr?cise le contexte /WebContent
		pour trouver le fichier /WEB-INF/web.xml */
		setContextDirectory(new File("WebContent"));
		
		
	}
}
