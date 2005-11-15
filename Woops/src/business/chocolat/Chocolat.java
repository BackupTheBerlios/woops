package business.chocolat;

import business.hibernate.ObjetHistorise;


public class Chocolat extends ObjetHistorise {
	private Integer id;
	private String nom;
	private int calorie;
	private String fabricant;
	
	public int getCalorie() {
		return calorie;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	public String getFabricant() {
		return fabricant;
	}
	public void setFabricant(String fabricant) {
		this.fabricant = fabricant;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public String toString() {
		String ret = "Chocolat : [ "+ id + " | " + nom + " ]"; 
		return ret;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = (Integer) id;
	}
	
	
	
	
}
