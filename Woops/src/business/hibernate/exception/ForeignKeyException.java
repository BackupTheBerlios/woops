package business.hibernate.exception;

/**
 * @author Nicolas RICARD
 * 
 */
public class ForeignKeyException extends Exception {

	private String appMessage = null;
	
	public ForeignKeyException(String msg, Exception e){
		super(msg,e);
		appMessage=msg;
	}

	public ForeignKeyException(String msg){
		super(msg);
		appMessage=msg;
	}
	
	public String getAppMessage() {
		return appMessage;
	}
	public void setAppMessage(String appMessage) {
		this.appMessage = appMessage;
	}
}
