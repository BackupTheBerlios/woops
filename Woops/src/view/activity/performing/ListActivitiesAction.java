package view.activity.performing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashMap;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.activity.ActivityItem;
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

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.control.ControlActionContext;


public class ListActivitiesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ListActivitiesAction.class);    

	/**
	 * Constructeur par défaut
	 */
	public ListActivitiesAction() {
		super();
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#doExecute(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void doExecute(ActionContext context) throws IOException, ServletException {
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
	 * Cette méthode constitue la liste à partir de la BD
	 * @param contexte	contexte d'execution de la servlet
	 * @throws Exception	indique qu'une erreur s'est produite pendant le traitement
	 */
	private void loadList(ActionContext context) throws Exception {
		logger.debug("ListActivitiesAction");
		
		Collection dbData = null;
		Collection listActivitiesItems = null;
		ActivityItem activityItem = null;
		User sessionUser = null;
		String state = null;
		
		// Initialisation du form si celui-ci est nul
		if (context.form()==null) {
			context.session().setAttribute(context.mapping().getAttribute(), new ListActivitiesForm());
		}
		
		// Récupération du form bean nécessaire pour fournir les informations à la JSP
    	ListActivitiesForm listActivitiesForm = (ListActivitiesForm) context.form();

    	// Récupération de l'identifiant du participant connecté
    	sessionUser = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
    	
    	// Récupération de la liste des activités
    	dbData = ActivityManager.getInstance().getActivitiesByUser((Integer) sessionUser.getId());  	

    	// Constitue une liste d'ActivityItems à partir des données stockées en BD  
    	Iterator iter = dbData.iterator();
    	listActivitiesItems = new ArrayList();
    	HashMap activitiesMap = new HashMap();
    	while (iter.hasNext()) {
    		Activity activity = (Activity) iter.next();
    		activityItem = new ActivityItem();
			
    		activityItem.setId(activity.getId().toString());
    		activityItem.setName(activity.getName());
    		activityItem.setDetails(activity.getDetails());
    		activityItem.setState(activity.getState().toString());
    		
    		// Verifions si les dependances de l'activite lui permettent de changer d'etat
    		state = ActivityManager.getInstance().verifChangeStateActivity(activity);
    		if (state!=null) {
    			if (state.equals(BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS)) 
    				activityItem.setAction(PresentationConstantes.ACTIVITY_START);
    			else 
    				activityItem.setAction(PresentationConstantes.ACTIVITY_FINISH);
    		}
    		// si state est null, l'activite ne peut pas changer d'etat
    		else
    			activityItem.setAction("");
			
			listActivitiesItems.add(activityItem);
    	
			// Construction de la hash map stockant la liste des activit?s
			activitiesMap.put(activity.getId(),activity);
    	}

		// Conversion de la liste en tableau d'items
		DisplayObject[] result = new ActivityItem[listActivitiesItems.size()];
		listActivitiesItems.toArray(result);
		
		// Création de la liste initialisée avec les valeurs à afficher
		ListActivitiesModel model = new ListActivitiesModel(result);
		listActivitiesForm.setDataModel(model);
	
		// Sauvegarde d'une HashMap stockant la liste des activités du participant
		context.session().setAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP,activitiesMap);
	}

	
	
	
	// ------------------------------------------------
	//          List-Control  Event Handler
    // ------------------------------------------------

	/**
	 * Cette méthode est appelée lorsque l'utilisateur demande un rafraîchissement de la liste 
	 * @param	context		contexte d'execution de la servlet
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listActivities_onRefresh(ControlActionContext ctx) throws Exception {
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
	public void listActivities_onSort(ControlActionContext context, String column, SortOrder direction) throws Exception {
		// Récupération de la liste dans le contexte
		ListActivitiesModel model = (ListActivitiesModel) context.control().getDataModel();
		
		// Effectue le tri sur la colonne demandée et enregistre les modification au niveau du contexte
		model.sortByColumn(column, direction);		
		context.control().execute(context, column,  direction);
	}
	
	
	/**
	 * Cette est appelée si le participant souhaite commencer ou terminer une activité
	 * @param context	contexte d'execution de la servlet
	 * @param key	identifiant d'une activité
	 * @throws IOException	indique qu'une erreur au niveau des entrées/sorties s'est produite 
	 * @throws ServletException	indique que le traitement demandé a généré une exception
	 */
	public void listActivities_onChange(ControlActionContext context, String key) throws IOException, ServletException {
		Integer activityId = new Integer(key);
		
		try {
			Activity activity = ActivityManager.getInstance().getActivityWithDependances(activityId);
			
			/* Test si le changement peut être effectué : ce traitement implique la vérification 
			des dépendances relatives à l'activité sélectionnée */
			if (!activity.process()) {
				/* Informe l'utilisateur : le message affiché au participant est fonction de l'action
				qu'il avait demandée */
				if (activity.getState() instanceof CreatedActivityState) {
					context.addGlobalError("msg.error.activity.change.state.created", activity.getName());
				} else if (activity.getState() instanceof InProgressActivityState) {
					context.addGlobalError("msg.error.activity.change.state.inprogress", activity.getName());
				}
			} else {
				// Met à jour en BD l'état de l'activité 
				ActivityManager.getInstance().update(activity);
				// Informe le participant que sa demande a été prise en compte
				if (activity.getState() instanceof InProgressActivityState) {
					context.addGlobalMessage("msg.info.activity.change.state.inprogress", activity.getName());
				} else if (activity.getState() instanceof FinishedActivityState) {
					context.addGlobalError("msg.error.activity.change.state.finished", activity.getName());
				}
			}
		} catch (PersistanceException pe) {
			logger.error(pe);
			context.addGlobalError("errors.persistance.select");
		} catch (Throwable t) {
			logger.error(t);
			context.addGlobalError("errors.global");
		}
		context.forwardByName(PresentationConstantes.FORWARD_ACTION);
	}
	
	
	
	
	
	
	
	public void listActivities_onEdit(ControlActionContext context, String activityIdString) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.UPDATE_MODE);
		context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,new Integer(activityIdString));
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT);
	}
	
	public void listActivities_onCreate(ControlActionContext context) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.INSERT_MODE);
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT);
	}
	
	
	
	
	
	
	public void listActivities_onDelete(ControlActionContext context, String activityIdString) throws IOException, ServletException, PersistanceException, ForeignKeyException {
	
		try{
			
			Integer activityId = new Integer(activityIdString);
			ActivityManager.getInstance().deleteLinksFromActivity(activityId);

		}
		catch (ForeignKeyException fke) {
			logger.error(fke);
			context.addGlobalError("errors.persistance.activity.foreignKey");
		}
		catch (PersistanceException pe) {
			logger.error(pe);
			context.addGlobalError("errors.persistance.select");
		} 
 		
		context.forwardByName(PresentationConstantes.FORWARD_ACTION);
		
	}
	
}



