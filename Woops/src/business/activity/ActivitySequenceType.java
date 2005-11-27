package business.activity;

import business.hibernate.ObjetHistorise;


public class ActivitySequenceType extends ObjetHistorise {
	private Integer id;
	private String name;
	
	/**
	 * Récupération de l'identifiant du type de sequence d'activité nécessaire pour la persistence
	 * @return identifiant du type de sequence d'activité
	 */
	public Object getId() {
		return id;
	}

	/**
	 * Modification de l'identifiant du type de sequence d'activité
	 * @param id identifiant du type de sequence d'activité à modifier
	 */
	public void setId(Object id) {
		this.id = (Integer) id;
	}
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
