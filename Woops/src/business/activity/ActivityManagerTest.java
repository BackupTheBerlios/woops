package business.activity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


import business.hibernate.exception.PersistanceException;
import junit.framework.TestCase;

public class ActivityManagerTest extends TestCase {

	/*
	 * Test method for 'business.activity.ActivityManager.getActivityById(Integer)'
	 */
	public void testGetActivityById() {
				
		// on cr?e les ids
		Integer id1 = new Integer(1);
		Integer id2 = new Integer(2);
		Integer id999 = new Integer(999);
		
		// on initialise les activit?s
		Activity act1 = new Activity();
		Activity act2 = new Activity();
		Activity act999 = new Activity();
		
		// on charge les activit?s
		
		try {
			act1 	= ActivityManager.getInstance().getActivityById(id1);
		}		
		catch (PersistanceException e1) {
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		assertTrue(act1.getId().equals(id1));
		
		
		try {
			act2 	= ActivityManager.getInstance().getActivityById(id2);
		} 
		catch (PersistanceException e1) {
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		assertFalse(act2.getId().equals(id999));
		

		try {
			act999 	= ActivityManager.getInstance().getActivityById(id999);
			
		} catch (PersistanceException e) {
			assertTrue(act999.getId()==null);
		}
		catch (IndexOutOfBoundsException e) {
			assertTrue(act999.getId()==null);
		}
		

		}
	

	
	

	/*
	 * Test method for 'business.activity.ActivityManager.getActivitiesByUser(Integer)'
	 */
	public void testGetActivitiesByUser() {

		// on cr?e l'id
		Integer id_user = new Integer(1);
		Collection ActivitiesList = new ArrayList();
		Activity act = new Activity();
		
		try {
			ActivitiesList = ActivityManager.getInstance().getActivitiesByUser(id_user);
		}		
		catch (PersistanceException e1) {
		}

		// tabmleau d'activit? pas vide
		assertFalse(ActivitiesList.isEmpty());
		
		Iterator iter = ActivitiesList.iterator();
		

		while (iter.hasNext()) {	
			
			act = (Activity)iter.next();
			assertTrue(act.getUserId().equals(id_user));
		}
	
	}

	
	
	/*
	 * Test method for 'business.activity.ActivityManager.getActivitiesHistoryByUser(Integer)'
	 */
	public void testGetActivitiesHistoryByUser() {
		

	}

	/*
	 * Test method for 'business.activity.ActivityManager.getPossibleActivityPredecessors(Integer)'
	 */
	public void testGetPossibleActivityPredecessors() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.getPredecessors(Integer)'
	 */
	public void testGetPredecessors() {

		Integer id = new Integer(2);
		Collection ActivitiesList = new ArrayList();
		Collection ActivitiesListSucc = new ArrayList();
		Activity act = new Activity();
		Activity acti = new Activity();
		boolean res = false ;
		
		try {
			act = ActivityManager.getInstance().getActivityById(id);
		}
		catch (PersistanceException e1) {
			
			assertTrue(false);
		}
		
		
		try {
			ActivitiesList = ActivityManager.getInstance().getPredecessors((Integer)act.getId());
		}		
		catch (PersistanceException e1) {
			
			assertTrue(false);
		}
		
		
		// tabmleau d'activit?s pas vide
		assertFalse(ActivitiesList.isEmpty());
		
		Iterator iter = ActivitiesList.iterator();
		

		while (iter.hasNext()) {	
			
			res = false;
			
			acti = (Activity)iter.next();
			
			try{
				ActivitiesListSucc = ActivityManager.getInstance().getSuccessors((Integer)acti.getId());
			}
			catch (PersistanceException e1) {
				assertTrue(false);
			}
			System.out.print(res+"\n\n");
			System.out.print(act.getId()+"\n\n");
			System.out.print(acti.getId()+"\n\n");
			
			res &= ActivitiesListSucc.contains(act);
	 		
		}
		assertTrue(res);
	 	
	 	
	 	
	}

	/*
	 * Test method for 'business.activity.ActivityManager.getSuccessors(Integer)'
	 */
	public void testGetSuccessors() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.getActivitySequencesPredecessors(Integer)'
	 */
	public void testGetActivitySequencesPredecessors() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.getActivitySequencesSuccessors(Integer)'
	 */
	public void testGetActivitySequencesSuccessors() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.saveActivityDependances(Integer, Collection, Collection)'
	 */
	public void testSaveActivityDependances() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.getActivityWithDependances(Integer)'
	 */
	public void testGetActivityWithDependances() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.verifChangeStateActivity(Activity)'
	 */
	public void testVerifChangeStateActivity() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.activitiesChangeState(Integer)'
	 */
	public void testActivitiesChangeState() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.deleteLinksFromActivity(Integer)'
	 */
	public void testDeleteLinksFromActivity() {

	}

}
