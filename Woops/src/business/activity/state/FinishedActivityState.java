package business.activity.state;

import business.activity.Activity;

/**
 * Implements a behavior associated with a state of the Context.
 */

public class FinishedActivityState extends IActivityState {

	private static final long serialVersionUID = 1L;

	public FinishedActivityState() {
		super();
	}

	public FinishedActivityState(Integer id) {
		super(id);
	}

	public boolean process(Activity activity) {
		return false;
	}

	public boolean checkBeforeChange(Activity activity) {
		return false;
		// TODO Auto-generated method stub
	}
}