package business.activity;

import business.hibernate.HistorizedObject;

public class ActivitySequence extends HistorizedObject {
	private Integer id;
	private Activity successor;
	private Activity predecessor;
	private ActivitySequenceType linkType;
	
	
	/**
	 * Récupération de l'identifiant de la séquence d'activité nécessaire pour la persistence
	 * @return identifiant de la séquence d'activité
	 */
	public Object getId() {
		return id;
	}

	/**
	 * Modification de l'identifiant de la séquence d'activité
	 * @param id identifiant de la séquence d'activité à modifier
	 */
	public void setId(Object id) {
		this.id = (Integer) id;
	}
	
	public Activity getPredecessor() {
		return predecessor;
	}
	
	public void setPredecessor(Activity predecessor) {
		this.predecessor = predecessor;
	}
	
	public Activity getSuccessor() {
		return successor;
	}
	
	public void setSuccessor(Activity successor) {
		this.successor = successor;
	}
	
	public ActivitySequenceType getLinkType() {
		return linkType;
	}
	
	public void setLinkType(ActivitySequenceType linkType) {
		this.linkType = linkType;
	}
}
