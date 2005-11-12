package business.activity;

import business.user.User;

/**
 * Defines an interface of interest to clients.
 *  Maintains an instance of a ConcreteState subclass
 *  that defines the current state.
 */

public class Activity {

	private String name;

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
		state.process("aaa");
	}

}