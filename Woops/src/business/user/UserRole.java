package business.user;

import business.security.Permission;
import business.security.RoleDescriptor;

public class UserRole implements RoleDescriptor, Comparable {
		
	/**
	 * Nom interne
	 */
	private String code;

	/**
	 * Nom du r�le
	 */	
	private String name;

	/**
	 * Permissions du r�le
	 */
	private Permission permission = new Permission();

	/**
	 * Construteur par d�faut
	 */
	public UserRole() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param		code code interne
	 * @param		name nom du r�le
	 */
	public UserRole(String code, String name) {
		super();
		this.code	= code;
		this.name	= name;
	}

	/**
	 * r�cup�ration du code.
	 * 
	 * @return		String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * R�cup�ration du nom
	 * 
	 * @return		String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modification du code
	 * 
	 * @param		code Le code � modifier
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Modification du nom
	 * 
	 * @param		name Le nom � modifier
	 */
	public void setName(String name) {
		this.name = name;
	}
		
	/**
	 * @see business.security.RoleDescriptor#hasRight(String)
	 */
	public boolean hasRight(String right) {
		return permission.isGranted(right);
	}

	/**
	 * @see java.lang.Comparable#compareTo(Object)
	 */
	public int compareTo(Object o) {
			
		if (o instanceof UserRole) {
			return name.compareTo(((UserRole) o).name);
		}
		return 0;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			return code.equals(obj);
		}
		return super.equals(obj);
	}
}
