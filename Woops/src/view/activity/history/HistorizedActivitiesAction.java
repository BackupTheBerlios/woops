package view.activity.history;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.activity.performing.ListActivitiesForm;
import view.activity.performing.ListActivitiesModel;
import view.common.WoopsCCAction;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.hibernate.exception.PersistanceException;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;

public class HistorizedActivitiesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(HistorizedActivitiesAction.class);    

	/**
	 * Constructeur par défaut
	 */
	public HistorizedActivitiesAction() {
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
	 * Cette méthode constitue la liste à partir de la BD
	 * @param contexte	contexte d'execution de la servlet
	 * @throws Exception	indique qu'une erreur s'est produite pendant le traitement
	 */
	private void loadList(ActionContext context) throws Exception {
		logger.debug("ListActivitiesAction");
		
		Collection dbData = null;
		Collection listActivitiesItems = null;
		HistorizedActivityItem activityItem = null;
		User sessionUser = null;
		
		// Initialisation du form si celui-ci est nul
		if (context.form()==null) {
			context.session().setAttribute(context.mapping().getAttribute(), new ListActivitiesForm());
		}
		
		// Récupération du form bean nécessaire pour fournir les informations à la JSP
    	ListActivitiesForm listActivitiesForm = (ListActivitiesForm) context.form();

    	// Récupération de l'identifiant du participant connecté
    	sessionUser = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
    	dbData = ActivityManager.getInstance().getActivitiesHistoryByUser(sessionUser.getId());  	

    	// Constitue une liste d'ActivityItems à partir des données stockées en BD  
    	Iterator iter = dbData.iterator();
    	listActivitiesItems = new ArrayList();
    	while (iter.hasNext()) {
    		Activity activity = (Activity) iter.next();
    		activityItem = new HistorizedActivityItem();
			
    		activityItem.setId(activity.getId().toString());
    		activityItem.setName(activity.getName());
    		activityItem.setDetails(activity.getDetails());
    		activityItem.setStartDate(activity.getStartDate());
    		activityItem.setEndDate(activity.getEndDate());
    		
			listActivitiesItems.add(activityItem);
    	}

		// Conversion de la liste en tableau d'items
		DisplayObject[] result = new HistorizedActivityItem[listActivitiesItems.size()];
		listActivitiesItems.toArray(result);
		
		// Création de la liste initialisée avec les valeurs à afficher
		ListActivitiesModel model = new ListActivitiesModel(result);
		listActivitiesForm.setDataModel(model);
	}

}
