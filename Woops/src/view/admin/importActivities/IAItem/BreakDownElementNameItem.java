package view.admin.importActivities.IAItem;

import com.cc.framework.common.DisplayObject;

public class BreakDownElementNameItem implements DisplayObject {
	String id ;
	String currentName ;

	/**
	 * @return Returns the name.
	 */
	public String getCurrentName() {
		return currentName;
	}

	/**
	 * @param name The name to set.
	 */
	public void setCurrentName(String name) {
		this.currentName = name;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
