package business.user;

import java.util.Collection;

public class User {

	private Integer id;

	private String login;

	private String password;

	private String firstName;

	private String lastName;

	/**
	 * @link aggregation
	 * @associates business.activity.Activity
	 * @directed directed
	 * @supplierCardinality 0..*
	 */
	private Collection linkToActivity = null;

	public Collection getLinkToActivity() {
		return linkToActivity;
	}

	public void setLinkToActivity(Collection linkToActivity) {
		this.linkToActivity = linkToActivity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
