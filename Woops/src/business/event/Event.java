/**
 * 
 */
package business.event;

import business.hibernate.HistorizedObject;

/**
 * @author Simon REGGIANI
 * Cette classe permet de g?rer les evenements
 */
public class Event extends HistorizedObject {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String details;
	private String occured;	/* "oui" ou "non" */
	private Integer	bdeId;
	private Integer activityId;
	
	
	public Object getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getOccured() {
		return occured;
	}
	public void setOccured(String occured) {
		this.occured = occured;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getBdeId() {
		return bdeId;
	}
	public void setBdeId(Integer bdeId) {
		this.bdeId = bdeId;
	}
	
}
