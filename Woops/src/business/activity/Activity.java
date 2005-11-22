package business.activity;

import java.util.Collection;

import business.user.User;


public class Activity {
	private	Integer			id; /** identifiant de l'activit� */
	private String 			name; /** nom de l'activit� */
	private	String			details; /** description de l'activit� */
	private User 			user; /** Participant responsable de la r�alisation de l'activit� */
	private IActivityState	state; /** Etat actuel de l'activit�
	
	/**
	 * Liste des activit�s dont d�pend l'activit�
	 * @associates business.activity.ActivitySequence
	 * @clientCardinality 1
	 * @clientRole linkToPredecessor
	 * @directed directed
	 * @supplierCardinality 0..*
	 * @supplierRole predecessor
	 */
	private Collection 		linkToPredecessor;
	
	/**
	 * Liste des activit�s qui d�pendent de l'activit�
	 * @associates business.activity.ActivitySequence
	 * @clientCardinality 1
	 * @clientRole linkToSuccessor
	 * @directed directed
	 * @link association
	 * @supplierCardinality 0..*
	 * @supplierRole successor
	 */
	private Collection 		linkToSuccessor;
	
	/**
	 * Constructeur par d�faut
	 */
	public Activity() {
		this.name = null;
		this.details = null;
		this.linkToPredecessor = null;
		this.linkToSuccessor = null;
		this.state = null;
	}
	
	/**
	 * Construteur permettant d'initialiser chaque caract�ristique d'une activit�
	 * @param name nom de l'activit�
	 * @param details description de l'activit�
	 * @param linkToPredecessor liste des activit�s dont d�pend l'activit�
	 * @param linkToSuccessor liste des activit�s qui d�pendent de l'activit�
	 * @param user participant responsable de la r�alisation de l'activit�
	 */
	public Activity(String name, String details, Collection linkToPredecessor,
			Collection linkToSuccessor, User user) {
		this.name = name;
		this.details = details;
		this.linkToPredecessor = linkToPredecessor;
		this.linkToSuccessor = linkToSuccessor;
		this.user = user;
		this.state = new CreatedActivity();
	}
	
	/**
	 * R�cup�ration de l'identifiant de l'activit� n�cessaire pour la persistence
	 * @return identifiant de l'activit�
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Modification de l'identifiant de l'activit�
	 * @param id identifiant de l'activit� � modifier
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * R�cup�ration du nom de l'activit�
	 * @return nom de l'activit�
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modification du nom de l'activit� 
	 * @param name nom de l'activit�
	 */ 
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * R�cup�ration du participant responsable de la r�alisation de l'activit�
	 * @return participant
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Modification du participant responsable de la r�alisation de l'activit�
	 * @param  participant
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * R�cup�ration du descriptif de l'activit�
	 * @return description de l'activit�
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Modification du descriptif de l'activit�
	 * @param details description de l'activit�
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * Modification de l'�tat de l'activit�
	 * @shapeType PatternLink
	 * @pattern gof.State
	 * @supplierRole State Abstraction
	 * @param newState nouvel �tat
	 */
	public void setState(IActivityState newState) {
		this.state = newState;
	}

	/**
	 * Op�ration qui d�clenche la modification de l'�tat de l'activit�
	 */
	public void someOperation() {
		state.process();
	}








}