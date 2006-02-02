package business.activity.sequence;

import junit.framework.TestCase;
import business.BusinessConstantes;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.sequencetype.ActivitySequenceType;
import business.activity.sequencetype.ActivitySequenceTypeManager;
import business.activity.state.CreatedActivityState;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;

public class ActivitySequenceManagerTest extends TestCase {


	/*
	 * Test method for 'business.activity.sequence.ActivitySequenceManager.removeActivitySequence(Activity, Activity)'
	 */
	public void testRemoveActivitySequence() {

	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.insert(PersistentObject)'
	 */
	public void testInsertPersistentObject() {

	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.insertWithGetId(PersistentObject)'
	 */
	public void testInsertWithGetId() {
		try {
			// cr?ation du pr?d?cesseur
			Activity predecessor = new Activity();
			predecessor.setName("pred");
			predecessor.setUserId(new Integer(1));
			predecessor.setState(new CreatedActivityState());
			Integer predecessorId = (Integer)ActivityManager.getInstance().insertWithGetId(predecessor);
			predecessor.setId(predecessorId);
		
			// cr?ation du successeur
			Activity successor = new Activity();
			successor.setName("succ");
			successor.setUserId(new Integer(1));
			successor.setState(new CreatedActivityState());
			Integer successorId = (Integer)ActivityManager.getInstance().insertWithGetId(successor);
			successor.setId(successorId);
		
			// cr?ation du linkType
			ActivitySequenceType linkType = new ActivitySequenceType();
			linkType = ActivitySequenceTypeManager.getInstance().getActivitySequenceTypeByName(BusinessConstantes.LINK_TYPE_FINISH_TO_START);
		
			
			// Ajout de l'activity sequence
			ActivitySequence newActivitySequence = new ActivitySequence();
			newActivitySequence.setPredecessor(predecessor);
			newActivitySequence.setSuccessor(successor);
			newActivitySequence.setLinkType(linkType);
			Integer newActivitySequenceId = (Integer)ActivitySequenceManager.getInstance().insertWithGetId(newActivitySequence);
		
			
			// V?rification que l'activity sequence a bien ?t? ajout?e
			ActivitySequence theSameActivitySequence = (ActivitySequence)ActivitySequenceManager.getInstance().get(ActivitySequence.class,newActivitySequenceId);
			
			assertTrue(newActivitySequence.equals(theSameActivitySequence));
			
			
			// Suppression de tout ce merdier
			ActivitySequenceManager.getInstance().delete(newActivitySequence);
			ActivityManager.getInstance().delete(predecessor);
			ActivityManager.getInstance().delete(successor);
			
		} catch (PersistanceException e) {
			assertTrue(false);
			e.printStackTrace();
		} catch (DoublonException e) {
			assertTrue(false);
			e.printStackTrace();
		} catch (ForeignKeyException e) {
			assertTrue(false);
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

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.getList(String)'
	 */
	public void testGetList() {

	}

}
