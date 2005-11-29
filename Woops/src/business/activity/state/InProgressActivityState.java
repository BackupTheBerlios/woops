package business.activity.state;

import business.activity.Activity;

/**
 * Implements a behavior associated with a state of the Context.
 */

public class InProgressActivityState extends IActivityState {
	private Integer progress;
	
	public InProgressActivityState() {
		super();
	}

	public void process(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public void checkBeforeChange() {
		// TODO Auto-generated method stub
		
	}
}