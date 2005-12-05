package view.activity.listActivities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.WoopsCCAction;
import view.activity.ActivityItem;
import view.activity.ListActivitiesModel;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.state.CreatedActivityState;
import business.activity.state.InProgressActivityState;
import business.hibernate.exception.PersistanceException;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.control.ControlActionContext;

public class ListActivitiesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ListActivitiesAction.class);    

	public void doExecute(ActionContext context) throws IOException, ServletException {
		logger.debug("ListActivitiesAction");
		
		Collection listActivitiesMgr = null;
		Collection listActivitiesItems = null;
		ActivityItem item = null;
		ActionForward forward = null;
		User user = null;
    	
		if (context.form()==null) {
			context.request().setAttribute(context.mapping().getAttribute(), new ListActivitiesForm());
		}
		
		try {
			// Récupération du form bean nécessaire pour fournir les informations à la JSP
	    	ListActivitiesForm listActivitiesForm = (ListActivitiesForm) context.form();

	    	// Récupération de l'identifiant du participant connecté
	    	user = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
	    	if (user == null) {
	    		forward = context.mapping().findForward(PresentationConstantes.FORWARD_INDEX);	
	    	} else {
	    		listActivitiesMgr = ActivityManager.getInstance().getActivitiesByUser(user.getId());  	
	
	    		Iterator iter = listActivitiesMgr.iterator();
	    		listActivitiesItems = new ArrayList();
	    		while (iter.hasNext()) {
	    			Activity activity = (Activity)iter.next();
				
					item = new ActivityItem();
					
					item.setId(activity.getId().toString());
					item.setName(activity.getName());
					item.setDetails(activity.getDetails());
					item.setState(activity.getState().toString());
					if (activity.getState() instanceof CreatedActivityState) {
						item.setAction(PresentationConstantes.ACTIVITY_START);
					}
					else if (activity.getState() instanceof InProgressActivityState) {
							item.setAction(PresentationConstantes.ACTIVITY_FINISH);
					}
					listActivitiesItems.add(item);
	    		}

				// Conversion de la liste en tableau d'items
				DisplayObject[] data = new ActivityItem[listActivitiesItems.size()];
				data = (ActivityItem[]) listActivitiesItems.toArray(data);
				
				// Création de la liste initialisée avec les valeurs à afficher
				ListActivitiesModel model = new ListActivitiesModel(data);
				listActivitiesForm.setDataModel(model);
			
				forward = context.mapping().findForward(PresentationConstantes.FORWARD_SUCCESS);
	    	}
	    } catch (PersistanceException pe) {
			context.addGlobalError("errors.persistance.select");
			forward = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);  
		} catch (Throwable t) {
			context.addGlobalError("errors.global");
			forward = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);  
		} finally {
			context.forward(forward); 
		}		
	}
	
	/**
	 * 
	 * @param context
	 * @param key
	 * @throws IOException
	 * @throws ServletException
	 */
	public void listActivities_onChange(ControlActionContext context, String key) throws IOException, ServletException {
		Integer activityId = new Integer(key);
		ActionForward forward = null;
		
		try {
			Activity activity = ActivityManager.getInstance().getActivityWithDependances(activityId);
			
			if (!activity.process()) {
				ActivityManager.getInstance().update(activity);
				context.addGlobalError("msg.error.activity.change.state", activity.getName());
		} else {
			ActivityManager.getInstance().update(activity);
			context.addGlobalMessage("msg.info.activity.change.state", activity.getName());
		}
		} catch (PersistanceException pe) {
			context.addGlobalError("errors.persistance.select");
		} catch (Throwable t) {
			context.addGlobalError("errors.global");
		} finally {
			forward = context.mapping().findForward(PresentationConstantes.FORWARD_ACTION);
			context.forward(forward); 
		}
	}
	
	public void listActivities_onEdit(ControlActionContext context, String activityIdString) throws IOException, ServletException {
		Integer activityId = new Integer(activityIdString);
		//ActionForward forward = new ActionForward();
		
		try {
				Activity activity = ActivityManager.getInstance().getActivityById(activityId);
				
				if ( activity.getState() instanceof CreatedActivityState ) {
					context.forwardByName(PresentationConstantes.FORWARD_EDIT,activityId);
					//forward = context.mapping().
				}
				else
				{
					context.addGlobalError("errors.manageActivityDependances");
					context.forwardByName(PresentationConstantes.FORWARD_ERROR);
				}
		} catch (PersistanceException pe) {
			context.addGlobalError("errors.persistance.select");
		} catch (Throwable t) {
			context.addGlobalError("errors.global");
		}
		
	}
	
}

