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
import view.breakdownelement.BreakdownElementItem;
import view.breakdownelement.ListBreakDownElementsModel;
import view.common.WoopsCCAction;
import view.user.UserItem;
import business.breakdownelement.BreakdownElement;
import business.breakdownelement.BreakdownElementManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

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
		BreakdownElementItem breakDownElementItem = null;

		// Initialisation du form si celui-ci est nul
		if (context.form()==null) 
		{
			context.session().setAttribute(context.mapping().getAttribute(), new AdminForm());
		}
		
		// Récupération du form bean nécessaire pour fournir les informations à la JSP
    	AdminForm adminForm = (AdminForm) context.form();
    	dbData = BreakdownElementManager.getInstance().getList(PresentationConstantes.TABLE_BREAKDOWN);
    	
    	// Constitue une liste de BreakDownElementItem à partir des données stockées en BD  
    	Iterator iter = dbData.iterator();
    	listBreakDownElementsItems = new ArrayList();
    	HashMap breakDownElementsMap = new HashMap();
    	
    	while (iter.hasNext()) {
    		BreakdownElement breakdownElement = (BreakdownElement) iter.next();
    		breakDownElementItem = new BreakdownElementItem();
			
    		breakDownElementItem.setId(breakdownElement.getId().toString());
    		breakDownElementItem.setPrefix(breakdownElement.getPrefix());
    		breakDownElementItem.setName(breakdownElement.getName());
    		breakDownElementItem.setDetails(breakdownElement.getDetails());
    		breakDownElementItem.setStartDate(breakdownElement.getStartDate());
    		breakDownElementItem.setEndDate(breakdownElement.getEndDate());
    		breakDownElementItem.setKind(breakdownElement.getKind().getName());
    		
    		listBreakDownElementsItems.add(breakDownElementItem);
			// Construction de la hash map stockant la liste des BreakDownElements
    		breakDownElementsMap.put(breakdownElement.getId(),breakdownElement);
    	}

		// Conversion de la liste en tableau d'items
		DisplayObject[] result = new BreakdownElementItem[listBreakDownElementsItems.size()];
		listBreakDownElementsItems.toArray(result);
		
		// Création de la liste initialisée avec les valeurs à afficher
		ListBreakDownElementsModel model = new ListBreakDownElementsModel(result);
		adminForm.setDataModelListBreakDownElements(model);
	
		// Sauvegarde d'une HashMap stockant la liste des BreakDownElements
		context.session().setAttribute(PresentationConstantes.KEY_BDE_MAP,breakDownElementsMap);
	}
	
	
	/**
	 * 
	 * @param context
	 * @throws Exception
	 */
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
    		userItem.setRole(user.getRole().getCode());
    		
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
	public void listBreakDownElements_onRefresh(ControlActionContext ctx) throws Exception {
		try {
			this.loadListBreakDownElements(ctx);
		} catch (Throwable t) {
			logger.error(t);
			ctx.addGlobalError("errors.global");
		}
	}
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
	public void listBreakDownElements_onSort(ControlActionContext context, String column, SortOrder direction) throws Exception {
		// Récupération de la liste dans le contexte
		ListBreakDownElementsModel model = (ListBreakDownElementsModel) context.control().getDataModel();
		
		// Effectue le tri sur la colonne demandée et enregistre les modification au niveau du contexte
		model.sortByColumn(column, direction);		
		context.control().execute(context, column,  direction);
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
	
	public void listBreakDownElements_onEdit(ControlActionContext context, String id) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID,id);
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT_BREAKDOWN,PresentationConstantes.UPDATE_MODE);
	}
	
	
	/**
	 * Cette méthode est appelée si l'aministeur clique sur le lien "copier"
	 * @param context	contexte d'execution de la servlet
	 * @param column	id du projet à supprimer
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listBreakDownElements_onCopy(ControlActionContext context, String id) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID,id);
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT_BREAKDOWN,PresentationConstantes.COPY_MODE);
	}

	/**
	 * Cette méthode est appelée si l'aministeur clique sur l'icone de supprimer un projet
	 * @param context	contexte d'execution de la servlet
	 * @param column	id du projet à supprimer
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listBreakDownElements_onDelete(ControlActionContext context, String id) throws IOException, ServletException {
		HashMap bdesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_BDE_MAP);

		BreakdownElement bde = (BreakdownElement)bdesMap.get(new Integer(Integer.parseInt(id)));
		
		try {
			BreakdownElementManager.getInstance().delete(bde);
			context.forwardByName(PresentationConstantes.FORWARD_DELETE_BREAKDOWN);
		} catch (PersistanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ForeignKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void listUsers_onEdit(ControlActionContext context, String id) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_USER_ID,id);
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT_USER,PresentationConstantes.UPDATE_MODE);
	}
	
	/**
	 * Cette méthode est appelée si l'aministeur clique sur l'icone de supprimer un utilisateur
	 * @param context	contexte d'execution de la servlet
	 * @param column	id de l'utilisateur à supprimer
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listUsers_onDelete(ControlActionContext context, String id) throws IOException, ServletException {
		
		HashMap usersMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_USERS_MAP);

		User user = (User)usersMap.get(new Integer(Integer.parseInt(id)));
		
		User userCourant = null;
		
		userCourant = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
		
		//Un utilisateur ne peut pas supprimer son compte
		if ( ((Integer) userCourant.getId()).compareTo(new Integer(id)) == 0) {
			context.addGlobalError("errors.admin.deleteCurrentUser");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
			return;
		}

		try {
			UserManager.getInstance().delete(user);
			context.forwardByName(PresentationConstantes.FORWARD_DELETE_USER);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ForeignKeyException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listBreakDownElements_onCreate(ControlActionContext context) throws IOException, ServletException {
		context.forwardByName(PresentationConstantes.FORWARD_EDIT_BREAKDOWN,PresentationConstantes.INSERT_MODE);
	}
	public void listUsers_onCreate(ControlActionContext context) throws IOException, ServletException {
		context.forwardByName(PresentationConstantes.FORWARD_EDIT_USER,PresentationConstantes.INSERT_MODE);
	}
	
	public void listBreakDownElements_onDrilldown(ControlActionContext context, String userBdeString) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID,new Integer(userBdeString));
		
		context.forwardByName(PresentationConstantes.FORWARD_DRILLDOWN_BREAKDOWN);
	}

	public void listUsers_onDrilldown(ControlActionContext context, String userIdString) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_USER_ID,new Integer(userIdString));
		
		context.forwardByName(PresentationConstantes.FORWARD_DRILLDOWN_USER);
	}
}
