package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import view.util.ProcessControler;

import junit.framework.TestCase;

public class ProcessControlerTest extends TestCase {
	private FileInputStream dpe;
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * Test method for 'view.util.ProcessControler.load(File)'
	 */
	public void testLoad() {
		try {
			dpe = new FileInputStream("test/util/processus.dpe");
			
			// On s'assure que le fichier existe 
			//assertTrue(dpe.exists());
			//assertTrue(dpe.canRead());
			
			List listActivities = ProcessControler.load(new BufferedInputStream(dpe));
			
			// On verifie que la recuperation s'est bien effectuee
			assertNotNull(listActivities);
			assertTrue(!listActivities.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

}
