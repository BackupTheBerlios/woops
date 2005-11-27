package business.activity;

import java.util.List;

import business.hibernate.ObjetPersistantDAO;
import business.hibernate.exception.PersistanceException;

public class ActivitySequenceTypeDAO extends ObjetPersistantDAO {
	
	public ActivitySequenceType getActivitySequenceTypeById(Integer activitySequenceTypeId)
	throws PersistanceException {
		List res = executeQuery("FROM ActivitySequenceType as actSeqTyp WHERE actSeqTyp.id = "+activitySequenceTypeId);
		return (ActivitySequenceType)res.get(0);
		//return (ActivitySequenceType)get(ActivitySequenceType.class,activitySequenceTypeId);
	}
}