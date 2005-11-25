package business.activity;
public class ActivitySequence {
	private Integer id;
	private Activity successor;
	private Activity predecessor;
	private ActivitySequenceType linkType;
	
	
	public Integer getId() {
	
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
