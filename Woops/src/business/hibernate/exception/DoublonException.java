package business.hibernate.exception;


/**
 * @author Nicolas Ricard
 * 
 */
public class DoublonException extends Exception {

	private String appMessage = null;
	static final long serialVersionUID = -3387516993124229948L;
	
	public DoublonException(String msg, Exception e){
		super(msg,e);
		appMessage=msg;
	}

	public DoublonException(String msg){
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
