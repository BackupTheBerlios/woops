package view.activity.summary;

import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.BusinessConstantes;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.state.CreatedActivityState;
import business.format.Formatage;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;


/**
 * @author Simon REGGIANI
 * ShowActivitySummaryAction : permet de creer une nouvelle activite
 */
public class ShowActivitySummaryAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ShowActivitySummaryAction.class);   
	
	/**
	 * Constructeur vide
	 *
	 */
	public ShowActivitySummaryAction() {
		super();
	}
	
	
	/**
	 * 
	 * Action a realiser avant l'affichage du formulaire
	 */

	public void doExecute(ActionContext context) {

		ShowActivitySummaryForm form = (ShowActivitySummaryForm) context.form();
	
		HashMap activitiesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP);
		
		Integer activityId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_ACTIVITY_ID);

		Activity activity = (Activity)activitiesMap.get(activityId);
		
		form.setActivityId(activityId.toString());
		form.setName(activity.getName());
		form.setDetails(activity.getDetails());
		String state = activity.getState().toString();
		form.setState(state);
		if ( state.equals(BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS) )
			form.setStartDate(Formatage.dateToString(activity.getStartDate()));
		
		if ( state.equals(BusinessConstantes.ACTIVITY_STATE_FINISHED) )
			form.setEndDate(Formatage.dateToString(activity.getEndDate()));
		
		context.forwardByName(PresentationConstantes.FORWARD_SUCCESS);
	}
	
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton previous (listActivities)
	 */
	
	public void previous_onClick(FormActionContext context) {
		
		context.forwardByName(PresentationConstantes.FORWARD_PREVIOUS);
	}
	
}
