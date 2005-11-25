package view.activity.manageActivity;

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
import business.hibernate.exception.PersistanceException;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.util.StringHelp;

/**
 * @author m1isi27
 *
 */
public class ManageActivityDependancesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ManageActivityDependancesAction.class);   
	
	ActionForward forward = null;
	
	public ManageActivityDependancesAction() {
		super();
	}

	public void doExecute(ActionContext context) throws IOException, ServletException {
		logger.debug("ManageActivityDependancesAction.doExecute()");
		
		if (context.form()==null) {
			context.request().setAttribute(context.mapping().getAttribute(), new ManageActivityDependancesForm());
		}
	
		try {
			setPossibleDependancesOptions(context);
			setRealActivityDependancesKeys(context);
			forward = context.mapping().findForward(PresentationConstantes.FORWARD_SUCCES);
	        
		} catch (PersistanceException t) {
			context.addGlobalError("action.activities.error");
		}
		
		/* Display the Page with the SwapSelect */
	    context.forward(forward); 
	}
	
	
	private void setPossibleDependancesOptions(ActionContext context) throws PersistanceException {
		Collection possibleActivityDependancesMgr = null;
		Collection possibleActivityDependancesItems = null;
		Collection activityDependances = null;
		ActivityItem item = null;
		
		ManageActivityDependancesForm madForm = (ManageActivityDependancesForm) context.form();
		
		possibleActivityDependancesMgr = ActivityManager.getInstance().getPossibleActivityDependances(new Integer(2));  	
		activityDependances = ActivityManager.getInstance().getActivityDependances(new Integer(2));
		
    	Iterator iter = possibleActivityDependancesMgr.iterator();
    	possibleActivityDependancesItems = new ArrayList();

    	while (iter.hasNext()) {
    		Activity activity = (Activity)iter.next();
			
			item = new ActivityItem();
			item.setId(activity.getId().toString());
			item.setName(activity.getName());
			
			possibleActivityDependancesItems.add(item);
		}
		
		// Convertit la liste possibleActivitiesList en tableau
		DisplayObject[] data = new ActivityItem[possibleActivityDependancesItems.size()];
		data = (ActivityItem[]) possibleActivityDependancesItems.toArray(data);
		
		
		ListActivitiesModel model = new ListActivitiesModel(data);
		madForm.setPossibleDependancesOptions(model);
	}
	
	
	private void setRealActivityDependancesKeys(ActionContext context) throws PersistanceException {
		Collection activityDependances = null;

		ManageActivityDependancesForm madForm = (ManageActivityDependancesForm) context.form();
		
		activityDependances = ActivityManager.getInstance().getActivityDependances(new Integer(2));  	
		
		// Convertit la liste des clés de type Integer 
		// de la liste activityDependancesKeys en tableau de clés de type String
    	String[] listStringKeys = new String[activityDependances.size()];
		
		Iterator iter = activityDependances.iterator();
    	
		for (int i=0; iter.hasNext(); i++) {
			listStringKeys[i]=((Activity)iter.next()).getId().toString();
		}
		
		madForm.setRealDependancesKeys(listStringKeys);
			
			
	}
	
	/**
	 * This Method is called if the Save-Button on the
	 * HTML-Page is pressed.
	 * 
	 * @param		ctx		FormActionContext
	 */
	public void save_onClick(FormActionContext ctx) throws Exception {

		ManageActivityDependancesForm form = (ManageActivityDependancesForm) ctx.form();
		String[] realDepedancesKeys  = form.getRealDependancesKeys();       
		String msg1 = (realDepedancesKeys.length == 0) ? "0" : StringHelp.join(realDepedancesKeys, ',');
		ctx.addGlobalMessage("message.insert.ok",msg1);
		ctx.forwardToInput();
	}

}
