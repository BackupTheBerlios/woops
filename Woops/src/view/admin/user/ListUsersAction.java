package view.admin.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.activity.ActivityItem;
import view.activity.performing.ListActivitiesAction;
import view.activity.performing.ListActivitiesModel;
import view.common.WoopsCCAction;
import business.BusinessConstantes;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.state.CreatedActivityState;
import business.activity.state.FinishedActivityState;
import business.activity.state.InProgressActivityState;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.control.ControlActionContext;

public class ListUsersAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ListActivitiesAction.class);    
	
	/**
	 * Constructeur par d�faut
	 */
	public ListUsersAction() {
		super();
	}
	
	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#doExecute(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void doExecute(ActionContext context) throws Exception {
		try {
			this.loadList(context);
			context.forwardToInput();
	    } catch (PersistanceException pe) {
	    	logger.error(pe);
			context.addGlobalError("errors.persistance.select");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);  
		} catch (Throwable t) {
			logger.error(t);
			context.addGlobalError("errors.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);  
		}		
	}
	
	/**
	 * Cette m�thode constitue la liste � partir de la BD
	 * @param contexte	contexte d'execution de la servlet
	 * @throws Exception	indique qu'une erreur s'est produite pendant le traitement
	 */
	private void loadList(ActionContext context) throws Exception {
		logger.debug("ListActivitiesAction");
		
		Collection dbData = null;
		Collection listUsersItems = null;
		UserItem userItem = null;
		
		// Initialisation du form si celui-ci est nul
		if (context.form()==null) {
			context.session().setAttribute(context.mapping().getAttribute(), new ListUsersForm());
		}
		
		// R�cup�ration du form bean n�cessaire pour fournir les informations � la JSP
    	ListUsersForm listUsersForm = (ListUsersForm) context.form();
    	
    	// R�cup�ration de la liste des activit�s
    	dbData = UserManager.getInstance().getList(PresentationConstantes.TABLE_USER);

    	// Constitue une liste d'ActivityItems � partir des donn�es stock�es en BD  
    	Iterator iter = dbData.iterator();
    	listUsersItems = new ArrayList();
    	HashMap usersMap = new HashMap();
    	while (iter.hasNext()) {
    		User user = (User) iter.next();
    		userItem = new UserItem();
			
    		userItem.setId(user.getId().toString());
    		userItem.setFirstName(user.getFirstName());
    		userItem.setLastName(user.getLastName());
    		userItem.setLogin(user.getLogin());
    		userItem.setRole(user.getRole().getName());
    		
    		listUsersItems.add(userItem);
			// Construction de la hash map stockant la liste des activit?s
			usersMap.put(user.getId(),user);
    	}

		// Conversion de la liste en tableau d'items
		DisplayObject[] result = new UserItem[listUsersItems.size()];
		listUsersItems.toArray(result);
		
		// Cr�ation de la liste initialis�e avec les valeurs � afficher
		ListUsersModel model = new ListUsersModel(result);
		listUsersForm.setDataModel(model);
	
		// Sauvegarde d'une HashMap stockant la liste des activit�s du participant
		// context.session().setAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP,usersMap);
	}
	
	// ------------------------------------------------
	//          List-Control  Event Handler
    // ------------------------------------------------

	/**
	 * Cette m�thode est appel�e lorsque l'utilisateur demande un rafra�chissement de la liste 
	 * @param	context		contexte d'execution de la servlet
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listUsers_onRefresh(ControlActionContext ctx) throws Exception {
		try {
			this.loadList(ctx);
		} catch (Throwable t) {
			logger.error(t);
			ctx.addGlobalError("errors.global");
		}
	}

	
	/**
	 * Cette m�thode est appel�e si le participant clique sur l'icone de tri d'une colonne
	 * @param context	contexte d'execution de la servlet
	 * @param column	colonne � trier
	 * @param direction	direction (ASC, DESC)
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listUsers_onSort(ControlActionContext context, String column, SortOrder direction) throws Exception {
		// R�cup�ration de la liste dans le contexte
		ListUsersModel model = (ListUsersModel) context.control().getDataModel();
		
		// Effectue le tri sur la colonne demand�e et enregistre les modification au niveau du contexte
		model.sortByColumn(column, direction);		
		context.control().execute(context, column,  direction);
	}
	
	public void listUsers_onEdit(ControlActionContext context, String login) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.UPDATE_MODE);
		context.request().setAttribute(PresentationConstantes.PARAM_LOGIN,login);
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT);
	}
	
	public void listUsers_onCreate(ControlActionContext context) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.INSERT_MODE);
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT);
	}
	
	
	
	
	
	
	public void listUsers_onDelete(ControlActionContext context, String activityIdString) throws IOException, ServletException, PersistanceException, ForeignKeyException {
	
//		try{
//			
//			Integer activityId = new Integer(activityIdString);
//			ActivityManager.getInstance().deleteLinksFromActivity(activityId);
//
//		}
//		catch (ForeignKeyException fke) {
//			logger.error(fke);
//			context.addGlobalError("errors.persistance.activity.foreignKey");
//		}
//		catch (PersistanceException pe) {
//			logger.error(pe);
//			context.addGlobalError("errors.persistance.select");
//		} 
 		
		context.forwardByName(PresentationConstantes.FORWARD_ACTION);
		
	}
}