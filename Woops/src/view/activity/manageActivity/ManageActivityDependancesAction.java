package view.activity.manageActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.WoopsCCAction;
import view.activity.listActivities.ActivityItem;
import view.activity.listActivities.ListActivitiesModel;

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
		ManageActivityDependancesForm madForm = (ManageActivityDependancesForm) context.form();
		
		Collection possibleActivitiesList = new ArrayList();
		
		ActivityItem ai1 = new ActivityItem();
		ai1.setId(new Integer(1));
		ai1.setName(new String("action numero1"));
		ai1.setDetails(new String(""));
		possibleActivitiesList.add(ai1);
		
		ActivityItem ai2 = new ActivityItem();
		ai2.setId(new Integer(5));
		ai2.setName(new String("action numero2"));
		ai2.setDetails(new String(""));
		possibleActivitiesList.add(ai2);
		
		// Convertit la liste possibleActivitiesList en tableau
		DisplayObject[] data = new ActivityItem[possibleActivitiesList.size()];
		data = (ActivityItem[]) possibleActivitiesList.toArray(data);
		
		
		ListActivitiesModel model = new ListActivitiesModel(data);
		madForm.setPossibleDependancesOptions(model);
		
		forward = context.mapping().findForward(PresentationConstantes.FORWARD_SUCCES);
        
	} catch (Throwable t) {
		context.addGlobalError("action.activities.error");
	}

	/* Display the Page with the SwapSelect */
    context.forward(forward); 
	}
	
//	 ------------------------------------------------
	//                 Event Handler
	// ------------------------------------------------

	
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
