package view.activity;



import com.cc.framework.common.DisplayObject;


public class ActivitySequenceItem implements DisplayObject{
	private String id;
	private String predecessor;
	private String successor;
	private String linkType;
	private String predecessorState;
	private String successorState;
	
	private String StartToFinishEditable = "true";
	private Boolean StartToStartEditable = new Boolean(true);
	private String FinishToStartEditable = "true";
	private Boolean FinishToFinishEditable = new Boolean(true);
	
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the linkType.
	 */
	public String getLinkType() {
		return linkType;
	}
	/**
	 * @param linkType The linkType to set.
	 */
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	/**
	 * @return Returns the predecessor.
	 */
	public String getPredecessor() {
		return predecessor;
	}
	/**
	 * @param predecessor The predecessor to set.
	 */
	public void setPredecessor(String predecessor) {
		this.predecessor = predecessor;
	}
	/**
	 * @return Returns the successor.
	 */
	public String getSuccessor() {
		return successor;
	}
	/**
	 * @param successor The successor to set.
	 */
	public void setSuccessor(String successor) {
		this.successor = successor;
	}
	/**
	 * @return Returns the predecessorState.
	 */
	public String getPredecessorState() {
		return predecessorState;
	}
	/**
	 * @param predecessorState The predecessorState to set.
	 */
	public void setPredecessorState(String predecessorState) {
		this.predecessorState = predecessorState;
	}
	/**
	 * @return Returns the successorState.
	 */
	public String getSuccessorState() {
		return successorState;
	}
	/**
	 * @param successorState The successorState to set.
	 */
	public void setSuccessorState(String successorState) {
		this.successorState = successorState;
	}
	
	public Boolean getFinishToFinishEditable() {
		return FinishToFinishEditable;
	}
	public void setFinishToFinishEditable(Boolean finishToFinishEditable) {
		FinishToFinishEditable = finishToFinishEditable;
	}
	public String getFinishToStartEditable() {
		return FinishToStartEditable;
	}
	public void setFinishToStartEditable(String finishToStartEditable) {
		FinishToStartEditable = finishToStartEditable;
	}
	public String getStartToFinishEditable() {
		return StartToFinishEditable;
	}
	public void setStartToFinishEditable(String startToFinishEditable) {
		StartToFinishEditable = startToFinishEditable;
	}
	public Boolean getStartToStartEditable() {
		return StartToStartEditable;
	}
	public void setStartToStartEditable(Boolean startToStartEditable) {
		StartToStartEditable = startToStartEditable;
	}
	


	
	
	
}
