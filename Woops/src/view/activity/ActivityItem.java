package view.activity;



import com.cc.framework.common.DisplayObject;


public class ActivityItem implements DisplayObject{
	private	String id;
	private String name;
	private String details;
	private String state;
	private String action;
	private boolean actionEnabled;
	private boolean deleteEnabled;
	
	public ActivityItem() {
		super();
	}
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the actionEnabled.
	 */
	public boolean getActionEnabled() {
		return actionEnabled;
	}

	/**
	 * @param actionEnabled The actionEnabled to set.
	 */
	public void setActionEnabled(boolean actionEnabled) {
		this.actionEnabled = actionEnabled;
	}

	public boolean getDeleteEnabled() {
		return deleteEnabled;
	}

	public void setDeleteEnabled(boolean deleteEnabled) {
		this.deleteEnabled = deleteEnabled;
	}
	
}
