package business.activity;

import java.util.Collection;


/**
 * Defines an interface of interest to clients.
 *  Maintains an instance of a ConcreteState subclass
 *  that defines the current state.
 */

public class Activity {

	private Integer id;
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
	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	private String name;
	private String details;

	/**
	 * @associates business.activity.ActivitySequence
	 * @clientCardinality 1
	 * @clientRole linkToPredecessor
	 * @directed directed
	 * @supplierCardinality 0..*
	 * @supplierRole predecessor
	 */
	public java.util.Collection linkToPredecessor = null;

	/**
	 * @associates business.activity.ActivitySequence
	 * @clientCardinality 1
	 * @clientRole linkToSuccessor
	 * @directed directed
	 * @link association
	 * @supplierCardinality 0..*
	 * @supplierRole successor
	 */
	public java.util.Collection linkToSuccessor = null;

	private IActivityState state;

	/**
	 * @link
	 * @shapeType PatternLink
	 * @pattern gof.State
	 * @supplierRole State Abstraction
	 */
	/*# private IActivityState lnkIActivityState;*/
	public void setState(IActivityState newState) {
		this.state = newState;
	}

	public void someOperation() {
		state.process();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Activity() {
		this.name = null;
		this.details = null;
		this.linkToPredecessor = null;
		this.linkToSuccessor = null;
		this.state = null;
	}
	
	public Activity(String name, String details, Collection linkToPredecessor, Collection linkToSuccessor) {
		this.name = name;
		this.details = details;
		this.linkToPredecessor = linkToPredecessor;
		this.linkToSuccessor = linkToSuccessor;
		this.state = new CreatedActivity();
	}
}