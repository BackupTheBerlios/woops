package business.activity;

import business.hibernate.ObjetHistorise;


public class ActivitySequenceType extends ObjetHistorise {
	private Integer id;
	private String name;
	
	/**
	 * R�cup�ration de l'identifiant du type de sequence d'activit� n�cessaire pour la persistence
	 * @return identifiant du type de sequence d'activit�
	 */
	public Object getId() {
		return id;
	}

	/**
	 * Modification de l'identifiant du type de sequence d'activit�
	 * @param id identifiant du type de sequence d'activit� � modifier
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
