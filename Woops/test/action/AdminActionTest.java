package action;

import business.BusinessConstantes;
import business.user.User;

import com.cc.framework.ui.control.SimpleListControl;

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
		
//		// On s'assure que toutes les activites sont terminees
//		SimpleListControl listActivities = (SimpleListControl) adminForm.getListActivities();
//		assertNotNull(listActivities);
//		// On recupere le model de la liste
//		WoopsListDataModel model = (WoopsListDataModel) listActivities.getDataModel();
//		for(int i = 0; i < model.size(); i++) {
//			ActivityItem act = (ActivityItem) model.getElementAt(i);
//			assertEquals(act.getState(), BusinessConstantes.ACTIVITY_STATE_FINISHED);
//		}
		
		//On s'assure que toutes les BreakDownElements sont terminees
		SimpleListControl listBreakDownElements = (SimpleListControl) adminForm.getListBreakDownElements();
		assertNotNull(listBreakDownElements);
		
//		On s'assure que toutes les users sont terminees
		SimpleListControl listUsers = (SimpleListControl) adminForm.getListUsers();
		assertNotNull(listUsers);
		
		verifyInputForward();
        verifyNoActionErrors();
	}
}
