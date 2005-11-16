package view.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import org.apache.struts.action.ActionForward;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;

import view.PresentationConstantes;
import view.WoopsCCAction;

public class ListActivitiesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ListActivitiesAction.class);    
    
	public void doExecute(ActionContext context) throws IOException, ServletException {
		logger.debug("ListActivitiesAction");
		
		ActionForward retour = null;
    	
		try {
	    	ListActivitiesForm listActivitiesForm = (ListActivitiesForm) context.form();
			
	    	// Get the Displaydata for our List
			Collection listActivities = new ArrayList();
			listActivities.add("activity 1");
			listActivities.add("activity 2");
			listActivities.add("activity 3");
			listActivities.add("activity 4");
			
			// Convert the List into DisplayObject tab
			DisplayObject[] data = new DisplayObject[0];
			
			/* Create the ListControl and populate it.
			with the Data to be displayed */
			ListActivitiesModel model = new ListActivitiesModel(data);
			listActivitiesForm.setDataModel(model);
		
			/* Put the ListControl into the Session-Object
			The ListControl is a statefull Object.*/
			//ctx.session().setAttribute("activities", listActivitiesForm);
			
			retour = context.mapping().findForward(PresentationConstantes.FORWARD_SUCCES);
	        
		} catch (Throwable t) {
			context.addGlobalError("action.activities.error");
		}

		/* Display the Page with the UserList */
        context.forward(retour); 		
	}
}
