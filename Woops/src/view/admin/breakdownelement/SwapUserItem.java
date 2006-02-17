package view.admin.breakdownelement;



import com.cc.framework.common.DisplayObject;


public class SwapUserItem implements DisplayObject{
	private	String id;
	private String firstandLastName;
	private String login;
	private String role;

	public SwapUserItem() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String getFirstandLastName() {
		return firstandLastName;
	}

	public void setFirstandLastName(String firstandLastName) {
		this.firstandLastName = firstandLastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
