package business.activity;

import java.util.Collection;


public class Activity {
	private	Integer			id; /** identifiant de l'activité */
	private String 			name; /** nom de l'activité */
	private	String			details; /** description de l'activité */
	private Integer 		userId; /** Id du participant responsable de la réalisation de l'activité */
	private IActivityState	state; /** Etat actuel de l'activité
	
	/**
	 * Liste des activités dont dépend l'activité
	 * @associates business.activity.ActivitySequence
	 * @clientCardinality 1
	 * @clientRole linkToPredecessor
	 * @directed directed
	 * @supplierCardinality 0..*
	 * @supplierRole predecessor
	 */
	private Collection 		linkToPredecessor;
	
	/**
	 * Constructeur par défaut
	 */
	public Activity() {
		this.name = null;
		this.details = null;
		this.linkToPredecessor = null;
		this.state = null;
	}
	
	/**
	 * Construteur permettant d'initialiser chaque caractéristique d'une activité
	 * @param name nom de l'activité
	 * @param details description de l'activité
	 * @param linkToPredecessor liste des activités dont dépend l'activité
	 * @param linkToSuccessor liste des activités qui dépendent de l'activité
	 * @param userId participant responsable de la réalisation de l'activité
	 */
	public Activity(String name, String details, Collection linkToPredecessor,
			 Integer userId) {
		this.name = name;
		this.details = details;
		this.linkToPredecessor = linkToPredecessor;
		this.userId = userId;
		this.state = new CreatedActivity();
	}
	
	/**
	 * Récupération de l'identifiant de l'activité nécessaire pour la persistence
	 * @return identifiant de l'activité
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Modification de l'identifiant de l'activité
	 * @param id identifiant de l'activité à modifier
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Récupération du nom de l'activité
	 * @return nom de l'activité
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modification du nom de l'activité 
	 * @param name nom de l'activité
	 */ 
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Récupération du participant responsable de la réalisation de l'activité
	 * @return : l'id du participant
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Modification du participant responsable de la réalisation de l'activité
	 * @param  participant
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * Récupération du descriptif de l'activité
	 * @return description de l'activité
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Modification du descriptif de l'activité
	 * @param details description de l'activité
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * Modification de l'état de l'activité
	 * @shapeType PatternLink
	 * @pattern gof.State
	 * @supplierRole State Abstraction
	 * @param newState nouvel état
	 */
	public void setState(IActivityState newState) {
		this.state = newState;
	}

	/**
	 * Opération qui déclenche la modification de l'état de l'activité
	 */
	public void someOperation() {
		state.process();
	}








}