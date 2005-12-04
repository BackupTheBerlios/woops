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
import business.hibernate.exception.PersistanceException;

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
    	
		if (context.form()==null) {
			context.request().setAttribute(context.mapping().getAttribute(), new ListActivitiesForm());
		}
		try {
	    	ListActivitiesForm listActivitiesForm = (ListActivitiesForm) context.form();

	    	//Get the Display data for our List
	    	listActivitiesMgr = ActivityManager.getInstance().getActivitiesByUser(new Integer(1));  	
			
	    	Iterator iter = listActivitiesMgr.iterator();
			listActivitiesItems = new ArrayList();
	    	while (iter.hasNext()) {
	    		Activity activity = (Activity)iter.next();
				
				item = new ActivityItem();
				
				item.setId(activity.getId().toString());
				item.setName(activity.getName());
				item.setDetails(activity.getDetails());
				item.setState(activity.getState().toString());
				listActivitiesItems.add(item);
			}

			// Convert the List into DisplayObject tab
			DisplayObject[] data = new ActivityItem[listActivitiesItems.size()];
			data = (ActivityItem[]) listActivitiesItems.toArray(data);
			
			/* Create the ListControl and populate it.
			with the Data to be displayed */
			ListActivitiesModel model = new ListActivitiesModel(data);
			listActivitiesForm.setDataModel(model);
			
			
		} catch (PersistanceException pe) {
			context.addGlobalError("errors.persistance.select");
		} catch (Throwable t) {
			context.addGlobalError("errors.global");
		}

		forward = context.mapping().findForward(PresentationConstantes.FORWARD_SUCCES);
        
		/* Display the Page with the UserList */
        context.forward(forward); 		
	}
	
	public void listActivities_onChange(ControlActionContext context, String key) throws IOException, ServletException {
				Integer activityId = new Integer(key);
				ActionForward forward = null;
				
				try {
   					Activity activity = ActivityManager.getInstance().getActivityWithDependances(activityId);
   					
   					if (!activity.process()) {
   						context.addGlobalError("msg.error.activity.change.state", activity.getName());
   					} else {
   						context.addGlobalMessage("msg.info.activity.change.state", activity.getName());
   					}
   					forward = context.mapping().findForward(PresentationConstantes.FORWARD_ACTION);
				} catch (PersistanceException pe) {
					context.addGlobalError("errors.persistance.select");
				} catch (Throwable t) {
					context.addGlobalError("errors.global");
				}
				
				/* Display the Page with the UserList */
		        context.forward(forward); 		

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
