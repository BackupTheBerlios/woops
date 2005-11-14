package business.activity;
public class ActivitySequence {
	private ActivitySequenceType linkType;
	public Activity successor;
	public Activity predecessor;
	public ActivitySequenceType getLinkType() {
		return linkType;
	}
	public void setLinkType(ActivitySequenceType linkType) {
		this.linkType = linkType;
	}
}
