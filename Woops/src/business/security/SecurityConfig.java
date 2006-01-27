package business.security;

import java.util.Hashtable;

import business.user.User;
import business.user.UserRole;

public final class SecurityConfig {
	// Liste des rôle reconnus par le système
	private Hashtable roles = new Hashtable();
	
	/**
	 * Constructeur
	 */
	public SecurityConfig() {
		super();
		roles.put(Roles.GUEST_ROLE, new UserRole(Roles.GUEST_ROLE, "guest"));
		roles.put(Roles.ADMINISTRATOR_ROLE, new UserRole(Roles.ADMINISTRATOR_ROLE, "administrator"));
		roles.put(Roles.DEVELOPER_ROLE, new UserRole(Roles.DEVELOPER_ROLE, "developer"));
	}
	
	/**
	 * Récupération d'un UserRole à partir du code
	 * @param 	code	code interne du rôle
	 * @return	Le rôle correspondant au code
	 * @throws IllegalArgumentException	Le code n'est pas reconnu par l'application
	 */
	public UserRole parseRole(String code) throws IllegalArgumentException {
		if (code == null) {
			// Création d'un rôle "Invité"
			return new UserRole(Roles.GUEST_ROLE, "Guest");
		}

		String c = code.trim();
		if ("".equals(c)) {
			// Création d'un rôle "Invité"
			return new UserRole(Roles.GUEST_ROLE, "Guest");
		} else {
			UserRole role = (UserRole) roles.get(c);

			if (role == null) {
				throw new IllegalArgumentException("userrole.invalid.user");
			}
		
			return role;
		}
	}
	
	/**
	 * Vérifie si l'utilisateur est administrateur
	 * @param 	user 	utilisateur
	 * @return <code>true</code> Si l'utilisateur est administrateur
	 */
	public static boolean isAdmin(User user) {
		RoleDescriptor userRole = user.getRole();
		return userRole.getCode().equals(Roles.ADMINISTRATOR_ROLE);
	}
}
