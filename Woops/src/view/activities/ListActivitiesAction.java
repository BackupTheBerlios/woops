package view.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import org.apache.struts.action.ActionForward;

import business.chocolat.Chocolat;
import business.format.Formatage;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;

import view.PresentationConstantes;
import view.WoopsCCAction;
import view.chocolat.ListerChocolatFormItem;

public class ListActivitiesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ListActivitiesAction.class);    
    
	public void doExecute(ActionContext context) throws IOException, ServletException {
		logger.debug("ListActivitiesAction");
		
		Collection listActivitiesMgr = null;
		Collection listActivitiesItems = null;
		ActivityItem item = null;
		ActionForward retour = null;
    	
		try {
	    	ListActivitiesForm listActivitiesForm = (ListActivitiesForm) context.form();
			
	    	// Get the Displaydata for our List
	    	// listActivitiesMgr = ActivitytManager.getInstance().getActivities()
	    	
//			Iterator iter = listActivitiesMgr.iterator();
//			while (iter.hasNext()) {
//				Activity activity = (Activity)iter.next();
//				
				item = new ActivityItem();
				
				item.setName("Liste");
				item.setDescription("Liste");
				
				listActivitiesItems.add(item);
//			}
			
			// Convert the List into DisplayObject tab
			DisplayObject[] data = (ActivityItem[]) listActivitiesItems.toArray(new DisplayObject[0]);
			
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
