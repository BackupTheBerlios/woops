package util;

import java.io.File;
import java.util.List;

import view.util.ProcessControler;

import junit.framework.TestCase;

public class ProcessControlerTest extends TestCase {
	private File dpe;
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * Test method for 'view.util.ProcessControler.load(File)'
	 */
	public void testLoad() {
		dpe = new File("test/util/processus.dpe");
		// On s'assure que le fichier existe 
		assertTrue(dpe.exists());
		assertTrue(dpe.canRead());
		
		try {
			List listActivities = ProcessControler.load(dpe);
			
			// On verifie que la recuperation s'est bien effectuee
			assertNotNull(listActivities);
			assertTrue(!listActivities.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

}
