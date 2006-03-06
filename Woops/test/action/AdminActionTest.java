package action;

import business.BusinessConstantes;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.UserRole;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.adapter.struts.ActionContext;

import junit.framework.TestCase;
import view.PresentationConstantes;
import view.activity.ActivityItem;
import view.activity.performing.ListActivitiesForm;
import view.admin.AdminForm;
import view.common.WoopsListDataModel;

public class AdminActionTest extends WoopsActionTest {
	
	private AdminForm adminForm;
	
	public AdminActionTest() {
		super();
	}
	/**
	 * Methode d'initialisation
	 */
	public void setUp() throws Exception {
		super.setUp();
		// Connexion d'un administrateur
		super.connect("woops", "woops");
	}
	
	public void testSuccessfullDisplay() {
		// On s'assure qu'on est connecte
		User user = (User) getSession().getAttribute(PresentationConstantes.KEY_USER);
		assertNotNull(user);
		
		setRequestPathInfo("/admin");
		
		//On execute l'action
		actionPerform();
		
		// On vérifie les informations dans le form
		adminForm = (AdminForm) getActionForm();
		assertNotNull(adminForm);
				
		//On s'assure que toutes les BreakDownElements sont terminees
		SimpleListControl listBreakDownElements = (SimpleListControl) adminForm.getListBreakDownElements();
		assertNotNull(listBreakDownElements);
		
		//On s'assure que toutes les users sont terminees
		SimpleListControl listUsers = (SimpleListControl) adminForm.getListUsers();
		assertNotNull(listUsers);
		
		
		verifyInputForward();
        verifyNoActionErrors();
	}
	
	public void testSuccessfulDeleteUser() {
		
		//On s'assure qu'on est connecte
		User currentUser = (User) getSession().getAttribute(PresentationConstantes.KEY_USER);
		assertNotNull(currentUser);
		
		// on ajoute d'abord un utilisateur "testSuccessfulDeleteUser"
		User user = new User();
		UserRole userRole = new UserRole();
		
		user.setFirstName("testSuccessfulDeleteUser");
		user.setLastName("testSuccessfulDeleteUser");
		user.setLogin("testSuccessfulDeleteUser");
		user.setPassword("testSuccessfulDeleteUser");
		
		userRole.setId(new Integer(1));
		user.setRole(userRole);
		
		try {
			user = (User) UserManager.getInstance().insert(user);
		} catch (PersistanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DoublonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// on exécute la methode doExecute() pour mettre à jour context
//		setRequestPathInfo("/admin");
//		actionPerform();
//		
		// on récupère nouvel adminForm
		adminForm = (AdminForm) getActionForm();
		
		assertNotNull(adminForm);
		
		String userId = user.getId().toString();
		
		setRequestPathInfo("/admin");
		
		setActionForm(adminForm);
		
		addRequestParameter("ctrl", "listUsers");
		addRequestParameter("action", "Delete");
		addRequestParameter("param", userId);
		System.out.println(request);
		
		//On execute l'action pour supprimer cet utilisateur "testSuccessfulDeleteUser"
		actionPerform();
		
		// on vérifier que l'utilisateur "testSuccessfulDeleteUser" n'existe plus
				
		verifyForward(PresentationConstantes.FORWARD_DELETE_USER);
//		verifyInputForward();
		verifyNoActionErrors();
	}
}
