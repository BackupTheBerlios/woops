package business.user;

import business.security.Permission;
import business.security.RoleDescriptor;

public class UserRole implements RoleDescriptor, Comparable {
		
	/**
	 * Nom interne
	 */
	private String code;

	/**
	 * Nom du rôle
	 */	
	private String name;

	/**
	 * Permissions du rôle
	 */
	private Permission permission = new Permission();

	/**
	 * Construteur par défaut
	 */
	public UserRole() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param		code code interne
	 * @param		name nom du rôle
	 */
	public UserRole(String code, String name) {
		super();
		this.code	= code;
		this.name	= name;
	}

	/**
	 * récupération du code.
	 * 
	 * @return		String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Récupération du nom
	 * 
	 * @return		String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modification du code
	 * 
	 * @param		code Le code à modifier
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Modification du nom
	 * 
	 * @param		name Le nom à modifier
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
