package view.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.control.ControlActionContext;

import view.PresentationConstantes;
import view.activity.performing.ListActivitiesAction;
import view.admin.user.ListUsersForm;
import view.admin.user.ListUsersModel;
import view.admin.user.UserItem;
import view.common.WoopsCCAction;

public class AdminAction  extends WoopsCCAction {

	
	private static Logger logger = Logger.getLogger(ListActivitiesAction.class);    
	
	public AdminAction (){
		super();
	}
	
	public void doExecute(ActionContext context) throws Exception {
		try {
			this.loadListUsers(context);
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
	
	private void loadListUsers(ActionContext context) throws Exception {
		logger.debug("ListActivitiesAction");
		
		Collection dbData = null;
		Collection listUsersItems = null;
		UserItem userItem = null;
		
		// Initialisation du form si celui-ci est nul
		if (context.form()==null) {
			context.session().setAttribute(context.mapping().getAttribute(), new ListUsersForm());
		}
		
		// Récupération du form bean nécessaire pour fournir les informations à la JSP
    	ListUsersForm listUsersForm = (ListUsersForm) context.form();
    	
    	// Récupération de la liste des activités
    	dbData = UserManager.getInstance().getList(PresentationConstantes.TABLE_USER);

    	// Constitue une liste d'ActivityItems à partir des données stockées en BD  
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
		
		// Création de la liste initialisée avec les valeurs à afficher
		ListUsersModel model = new ListUsersModel(result);
		listUsersForm.setDataModel(model);
	
		// Sauvegarde d'une HashMap stockant la liste des activités du participant
		context.session().setAttribute(PresentationConstantes.KEY_USERS_MAP,usersMap);
	}
	
	// ------------------------------------------------
	//          List-Control  Event Handler
    // ------------------------------------------------

	/**
	 * Cette méthode est appelée lorsque l'utilisateur demande un rafraîchissement de la liste 
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
	 * Cette méthode est appelée si le participant clique sur l'icone de tri d'une colonne
	 * @param context	contexte d'execution de la servlet
	 * @param column	colonne à trier
	 * @param direction	direction (ASC, DESC)
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listUsers_onSort(ControlActionContext context, String column, SortOrder direction) throws Exception {
		// Récupération de la liste dans le contexte
		ListUsersModel model = (ListUsersModel) context.control().getDataModel();
		
		// Effectue le tri sur la colonne demandée et enregistre les modification au niveau du contexte
		model.sortByColumn(column, direction);		
		context.control().execute(context, column,  direction);
	}
	
	public void listUsers_onEdit(ControlActionContext context, String id) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.UPDATE_MODE);
		context.request().setAttribute(PresentationConstantes.PARAM_USER_ID,id);
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT);
	}
	
	public void listUsers_onCreate(ControlActionContext context) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.INSERT_MODE);
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT);
	}

}
