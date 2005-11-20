package view.activity.listActivities;

import com.cc.framework.common.DisplayObject;


public class ActivityItem implements DisplayObject{
	private Integer id;
	private String name;
	private String details;
	
	/**
	 * @return Returns the details.
	 */
	public String getDetails() {
		return details;
	}
	
	/**
	 * @param details The details to set.
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
