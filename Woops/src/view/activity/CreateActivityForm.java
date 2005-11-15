package view.activity;

import java.util.Collection;

import view.WoopsFormBean;

public class CreateActivityForm extends WoopsFormBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private Collection activitiesNamesList;

	
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {

		return description;
	}
	
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {

		this.description = description;
	}
	
	

	public Collection getActivitiesNamesList() {
		return activitiesNamesList;
	}

	public void setActivitiesNamesList(Collection activitiesNamesList) {
		this.activitiesNamesList = activitiesNamesList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
