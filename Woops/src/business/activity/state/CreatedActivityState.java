package business.activity.state;

import java.util.Iterator;

import business.BusinessConstantes;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.ActivitySequence;
import business.hibernate.exception.PersistanceException;

/**
 * Implements a behavior associated with a state of the Context.
 */

public class CreatedActivityState extends IActivityState {

	public CreatedActivityState() {
		super();
	}
	
	public CreatedActivityState(Integer id) {
		super(id);
	}

	public boolean process(Activity activity) {
		boolean result = false;
		if (checkBeforeChange(activity)) {
			/** TODO integer parametre à remplacer */
			InProgressActivityState inProg = new InProgressActivityState(2);

			activity.setState(inProg);
			result = true;
		}
		return result;
	}

	public boolean checkBeforeChange(Activity activity) {
		boolean result = true;
		
		Iterator iter = activity.getListActivitiesSequences().iterator();
		
		ActivitySequence actSeq = null;
		while ((iter.hasNext())&&(result==true)) {
			actSeq = (ActivitySequence)iter.next();
			
			if (actSeq.getLinkType().equals(BusinessConstantes.LINK_TYPE_FINISH_TO_START)) {
				if (!(actSeq.getPredecessor().getState() instanceof FinishedActivityState))
					result = false;
			}
			
			if (actSeq.getLinkType().equals(BusinessConstantes.LINK_TYPE_START_TO_START)) {
				if (!(actSeq.getPredecessor().getState() instanceof InProgressActivityState))
					result = false;
			}
		}
		
		return result;
	}
	

}