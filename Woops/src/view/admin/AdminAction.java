package view.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.admin.user.ListUsersModel;
import view.admin.user.UserItem;
import view.admin.breakdownelement.BreakDownElementItem;
import view.admin.breakdownelement.ListBreakDownElementsModel;
import view.common.WoopsCCAction;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;
import business.breakdownelement.BreakdownElementManager;
import business.breakdownelement.BreakdownElement;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.control.ControlActionContext;

public class AdminAction  extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(AdminAction.class);    
	
	public AdminAction (){
		super();
	}
	
	public void doExecute(ActionContext context) throws Exception {
		try 
		{
			this.loadListBreakDownElements(context);
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
	private void loadListBreakDownElements(ActionContext context) throws Exception 
	{
		logger.debug("AdminAction");
		Collection dbData = null;
		Collection listBreakDownElementsItems = null;
		BreakDownElementItem breakDownElementItem = null;

		// Initialisation du form si celui-ci est nul
		if (context.form()==null) 
		{
			context.session().setAttribute(context.mapping().getAttribute(), new AdminForm());
		}
		
		// Récupération du form bean nécessaire pour fournir les informations à la JSP
    	AdminForm adminForm = (AdminForm) context.form();
    	dbData = BreakdownElementManager.getInstance().getList(PresentationConstantes.TABLE_BREAKDOWN);
    	
    	// Constitue une liste d'UserItem à partir des données stockées en BD  
    	Iterator iter = dbData.iterator();
    	listBreakDownElementsItems = new ArrayList();
    	HashMap breakDownElementsMap = new HashMap();
    	
    	while (iter.hasNext()) {
    		BreakdownElement breakdownElement = (BreakdownElement) iter.next();
    		breakDownElementItem = new BreakDownElementItem();
			
    		breakDownElementItem.setId((Integer)breakdownElement.getId());
    		breakDownElementItem.setPrefix(breakdownElement.getPrefix());
    		breakDownElementItem.setStartDate(breakdownElement.getStartDate());
    		breakDownElementItem.setEndDate(breakdownElement.getEndDate());
    		breakDownElementItem.setKind(breakdownElement.getKind());
    		
    		listBreakDownElementsItems.add(breakDownElementItem);
			// Construction de la hash map stockant la liste des utilisateurs
    		breakDownElementsMap.put(breakdownElement.getId(),breakdownElement);
    	}

		// Conversion de la liste en tableau d'items
		DisplayObject[] result = new BreakDownElementItem[listBreakDownElementsItems.size()];
		listBreakDownElementsItems.toArray(result);
		
		// Création de la liste initialisée avec les valeurs à afficher
		ListBreakDownElementsModel model = new ListBreakDownElementsModel(result);
		adminForm.setDataModelListBreakDownElements(model);
	
		// Sauvegarde d'une HashMap stockant la liste des utilisateurs
		context.session().setAttribute(PresentationConstantes.KEY_USERS_MAP,breakDownElementsMap);
	}
	private void loadListUsers(ActionContext context) throws Exception {
		logger.debug("AdminAction");
		
		Collection dbData = null;
		Collection listUsersItems = null;
		UserItem userItem = null;
		
		// Initialisation du form si celui-ci est nul
		if (context.form()==null) {
			context.session().setAttribute(context.mapping().getAttribute(), new AdminForm());
		}
		
		// Récupération du form bean nécessaire pour fournir les informations à la JSP
    	AdminForm adminForm = (AdminForm) context.form();
    	
    	// Récupération de la liste des utilisateurs
    	dbData = UserManager.getInstance().getList(PresentationConstantes.TABLE_USER);

    	// Constitue une liste d'UserItem à partir des données stockées en BD  
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
			// Construction de la hash map stockant la liste des utilisateurs
			usersMap.put(user.getId(),user);
    	}

		// Conversion de la liste en tableau d'items
		DisplayObject[] result = new UserItem[listUsersItems.size()];
		listUsersItems.toArray(result);
		
		// Création de la liste initialisée avec les valeurs à afficher
		ListUsersModel model = new ListUsersModel(result);
		adminForm.setDataModelUser(model);
	
		// Sauvegarde d'une HashMap stockant la liste des utilisateurs
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
			this.loadListUsers(ctx);
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
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT_USER);
	}
	
	public void listUsers_onCreate(ControlActionContext context) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.INSERT_MODE);
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT);
	}
	
	public void listUsers_onDrilldown(ControlActionContext context, String userIdString) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_USER_ID,new Integer(userIdString));
		
		context.forwardByName(PresentationConstantes.FORWARD_DRILLDOWN_USER);
	}

}
