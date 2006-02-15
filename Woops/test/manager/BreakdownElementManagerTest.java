package manager;

import java.util.ArrayList;

import view.PresentationConstantes;

import business.breakdownelement.BreakdownElement;
import business.breakdownelement.BreakdownElementKind;
import business.breakdownelement.BreakdownElementManager;

public class BreakdownElementManagerTest extends WoopsManagerTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.insert(PersistentObject)'
	 */
	public void testInsertPersistentObject() {
		BreakdownElement bde = new BreakdownElement();
		bde.setName("projet");
		bde.setDetails("super projet");
		BreakdownElementKind bdek = new BreakdownElementKind();
		bdek.setId(new Integer(1));
		bde.setKind(bdek);
		
		try {
			
			Integer bdeId = (Integer)BreakdownElementManager.getInstance().insert(bde);
			assertNotNull(bdeId);
			
			bde.setId(bdeId);
			BreakdownElementManager.getInstance().delete(bde);

			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetList() {
		BreakdownElement bde = new BreakdownElement();
		bde.setName("projet");
		bde.setDetails("super projet");
		BreakdownElementKind bdek = new BreakdownElementKind();
		bdek.setId(new Integer(1));
		bde.setKind(bdek);
		
		try {
			
			Integer bdeId = (Integer)BreakdownElementManager.getInstance().insert(bde);
			assertNotNull(bdeId);
			
			ArrayList list = (ArrayList)BreakdownElementManager.getInstance().getList(PresentationConstantes.TABLE_BREAKDOWN);
			assertFalse(list.isEmpty());
			
			bde.setId(bdeId);
			BreakdownElementManager.getInstance().delete(bde);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
