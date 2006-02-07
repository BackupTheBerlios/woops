package manager;

import business.activity.Activity;
import business.activity.ActivityManager;


public class ActivityManagerTest extends WoopsManagerTest {

	protected void setUp() throws Exception {
		super.setUp();
		mgr = ActivityManager.getInstance();
	}

	/*
	 * Test method for 'business.activity.ActivityManager.getActivityById(Integer)'
	 */
	public void testGetActivityById() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.getActivitiesByUser(Integer)'
	 */
	public void testGetActivitiesByUser() {

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

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.insert(PersistentObject)'
	 */
	public void testInsertPersistentObject() {
		final Integer activityId = new Integer(1);
		
		try {
			// On s'assure que l'activité n'existe pas
			assertNull(((ActivityManager)mgr).getActivityById(activityId));
			
			// Création de l'activité et insertion en BD
			Activity act = new Activity();
			act.setId(activityId);
			mgr.insert(act);
			
			// On s'assure que l'activité a été insérée en BD
			assertEquals(activityId, ((ActivityManager)mgr).getActivityById(activityId).getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.update(PersistentObject)'
	 */
	public void testUpdatePersistentObject() {

	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.delete(PersistentObject)'
	 */
	public void testDeletePersistentObject() {

	}

}
