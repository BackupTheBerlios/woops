package business.chocolat;


public class Chocolat {
	private int id;
	private String nom;
	private int calorie;
	private String fabricant;
	
	public Chocolat(int i, String n, int c, String f) {
		this.id = i;
		this.nom = n;
		this.calorie = c;
		this.fabricant = f;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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

	
}
