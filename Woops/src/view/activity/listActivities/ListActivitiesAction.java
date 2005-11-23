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
import business.activity.Activity;
import business.activity.ActivityManager;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;

public class ListActivitiesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ListActivitiesAction.class);    
    
	public void doExecute(ActionContext context) throws IOException, ServletException {
		logger.debug("ListActivitiesAction");
		
		Collection listActivitiesMgr = null;
		Collection listActivitiesItems = null;
		ActivityItem item = null;
		ActionForward retour = null;
    	
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
				
				item.setId(activity.getId());
				item.setName(activity.getName());
				item.setDetails(activity.getDetails());
				
				listActivitiesItems.add(item);
			}

			// Convert the List into DisplayObject tab
			DisplayObject[] data = new ActivityItem[listActivitiesItems.size()];
			data = (ActivityItem[]) listActivitiesItems.toArray(data);
			
			/* Create the ListControl and populate it.
			with the Data to be displayed */
			ListActivitiesModel model = new ListActivitiesModel(data);
			listActivitiesForm.setDataModel(model);
			
			retour = context.mapping().findForward(PresentationConstantes.FORWARD_SUCCES);
	        
		} catch (Throwable t) {
			context.addGlobalError("action.activities.error");
		}

		/* Display the Page with the UserList */
        context.forward(retour); 		
	}
}
