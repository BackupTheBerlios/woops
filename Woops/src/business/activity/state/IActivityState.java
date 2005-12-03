package business.activity.state;

import business.activity.Activity;


/**
 * @role __State
 */


public abstract class IActivityState {
	protected	Integer	id;
	protected	String name;
	
	/**
	 * Constructeur par défaut appelé par Hibernate
	 */
	public IActivityState() {}
	
	/**
	 * Récupération de l'identifiant de l'état
	 * @return identifiant de l'état
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Modification de l'identifiant de l'état
	 * @param id : identifiant de l'état
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public abstract boolean process(Activity activity);
	public abstract boolean checkBeforeChange(Activity activity);
	
	public boolean equals(String state) {
		return this.name.equals(state);
	}
}