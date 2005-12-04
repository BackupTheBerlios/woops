package business.activity;

import java.util.Collection;

import business.activity.state.IActivityState;
import business.hibernate.HistorizedObject;


public class Activity extends HistorizedObject{	
	private	Integer			id; /** identifiant de l'activit� */
	private String 			name; /** nom de l'activit� */
	private	String			details; /** description de l'activit� */
	private Integer 		userId; /** Id du participant responsable de la r�alisation de l'activit� */
	private IActivityState	state; /** Etat actuel de l'activit�
	
	/**
	 * Liste des activit�s dont d�pend l'activit�
	 * @associates business.activity.ActivitySequence
	 * @clientCardinality 1
	 * @clientRole listActivitiesSequences
	 * @directed directed
	 * @supplierCardinality 0..*
	 * @supplierRole predecessor
	 */
	private Collection 		listActivitiesSequences;
	
	/**
	 * Constructeur par d�faut
	 */
	public Activity() {
		this.id = null;
		this.name = null;
		this.details = null;
		//this.listActivitiesSequences = null;
		this.userId = null;
		this.state = null;
	}
	
	/**
	 * R�cup�ration de l'identifiant de l'activit� n�cessaire pour la persistence
	 * @return identifiant de l'activit�
	 */
	public Object getId() {
		return id;
	}

	/**
	 * Modification de l'identifiant de l'activit�
	 * @param id identifiant de l'activit� � modifier
	 */
	public void setId(Object id) {
		this.id = (Integer) id;
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
	 * @return : l'id du participant
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Modification du participant responsable de la r�alisation de l'activit�
	 * @param  participant
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	
	
	public Collection getListActivitiesSequences() {
		return listActivitiesSequences;
	}

	public void setListActivitiesSequences(Collection listActivitiesSequences) {
		this.listActivitiesSequences = listActivitiesSequences;
	}

	/**
	 * R�cup�ration de l'�tat de l'activit�
	 * @return �tat de l'activit�
	 */
	public IActivityState getState() {
		return state;
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
	public boolean process() {
		return state.process(this);
	}

}