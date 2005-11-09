package view.chocolat;

import view.WoopsFormItem;

public class ListerChocolatFormItem extends WoopsFormItem {

	private String id;
	
    private String nom;
    private String fabricant;
    private String calorie;
    
    
	public String getCalorie() {
		return calorie;
	}
	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}
	public String getFabricant() {
		return fabricant;
	}
	public void setFabricant(String fabricant) {
		this.fabricant = fabricant;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

}
