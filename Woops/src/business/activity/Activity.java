package business.activity;

import java.util.Collection;


/**
 * Defines an interface of interest to clients.
 *  Maintains an instance of a ConcreteState subclass
 *  that defines the current state.
 */

public class Activity {

	private String name;
	private String description;

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

	public Activity(String name, String description, Collection linkToPredecessor, Collection linkToSuccessor) {
		this.name = name;
		this.description = description;
		this.linkToPredecessor = linkToPredecessor;
		this.linkToSuccessor = linkToSuccessor;
		this.state = new CreatedActivity();
	}

	
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
}