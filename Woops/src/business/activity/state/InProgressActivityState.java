package business.activity.state;

import java.util.Iterator;

import business.BusinessConstantes;
import business.activity.Activity;
import business.activity.ActivitySequence;

/**
 * Implements a behavior associated with a state of the Context.
 */

public class InProgressActivityState extends IActivityState {
	private Integer progress;
	
	public InProgressActivityState() {
		super();
	}

	public boolean process(Activity activity) {
		boolean result = false;
		if (checkBeforeChange(activity)) {
			activity.setState(new FinishedActivityState());
			result = true;
		}
		return result;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public boolean checkBeforeChange(Activity activity) {
		/* Si l'activité n'a aucun prédecesseur, elle peut commencer */
		boolean result = true;
		
		Iterator iter = activity.getListActivitiesSequences().iterator();

		ActivitySequence actSeq = null;
		while ((iter.hasNext())&&(result==true)) {
			actSeq = (ActivitySequence)iter.next();
			
			if (actSeq.getLinkType().equals(BusinessConstantes.LINK_TYPE_FINISH_TO_FINISH)) {
				if (!(actSeq.getPredecessor().getState() instanceof FinishedActivityState))
					result = false;
			}
			
			if (actSeq.getLinkType().equals(BusinessConstantes.LINK_TYPE_START_TO_FINISH)) {
				if (!(actSeq.getPredecessor().getState() instanceof InProgressActivityState))
					result = false;
			}
		}
		
		return result;
	}
}