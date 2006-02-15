package business.breakdownelement;

import business.hibernate.HistorizedObject;
import business.user.User;


/**
 * UserBDE : classe permettant de relier un user à un breakdown element
 * @author simon REGGIANI
 *
 */
public class UserBDE extends HistorizedObject{	
	private static final long serialVersionUID = 2763309763407190586L; /** Generated Serial ID */
	private	Integer				id;
	private User				user;
	private BreakdownElement	bde;
	
	public UserBDE() {
		super();
		this.id = null;
		this.user = null;
	}

	public BreakdownElement getBde() {
		return bde;
	}

	public void setBde(BreakdownElement bde) {
		this.bde = bde;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = (Integer)id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
