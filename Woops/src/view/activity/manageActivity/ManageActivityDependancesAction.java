package view.activity.manageActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.model.ListDataModel;

import view.WoopsCCAction;
import view.activity.listActivities.ActivityItem;
import view.activity.listActivities.ListActivitiesAction;
import view.activity.listActivities.ListActivitiesForm;
import view.activity.listActivities.ListActivitiesModel;

public class ManageActivityDependancesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ManageActivityDependancesAction.class);   
	
	public ManageActivityDependancesAction() {
		super();
	}

	public void doExecute(ActionContext context) throws IOException, ServletException {
		logger.debug("ManageActivityDependancesAction.doExecute()");
		
		if (context.form()==null) {
			logger.debug("context.form()==null");
			context.request().setAttribute(context.mapping().getAttribute(), new ManageActivityDependancesForm());
		}
		
		ManageActivityDependancesForm madForm = (ManageActivityDependancesForm) context.form();
		
		Collection possibleActivitiesList = new ArrayList();
		
		ActivityItem ai1 = new ActivityItem();
		ai1.setId(new Integer(1));
		ai1.setName(new String("act1"));
		ai1.setDetails(new String(""));
		possibleActivitiesList.add(ai1);
		
		// Convertit la liste possibleActivitiesList en tableau
		DisplayObject[] data = new ActivityItem[possibleActivitiesList.size()];
		data = (ActivityItem[]) possibleActivitiesList.toArray(data);
		
		logger.debug("taille de data : "+data.length);
		
		ListActivitiesModel model = new ListActivitiesModel(data);
		madForm.setPossibleDependancesOptions(model);
		
		context.forwardToInput();
	}
	
	

}
