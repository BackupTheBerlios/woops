package view.activity.summary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.activity.ActivityItem;
import view.activity.ActivitySequenceItem;
import view.activity.ActivitySequencesModel;
import view.common.WoopsCCAction;
import business.BusinessConstantes;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.sequence.ActivitySequence;
import business.format.Formatage;
import business.hibernate.exception.PersistanceException;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;


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
		
		
		try {
			// Récupération de la liste des prédécesseurs de l'activité
			Collection predecessorsMngr = ActivityManager.getInstance().getActivitySequencesPredecessors(activityId);
		
			//Convertion de cette liste en une liste d'ActivitySequenceItem
			Iterator iter = predecessorsMngr.iterator();
			ActivitySequence activitySequence;
			
			Collection activitySequenceItems = new ArrayList();
			while(iter.hasNext()) {
				activitySequence = (ActivitySequence)iter.next();
				ActivitySequenceItem activitySequenceItem = new ActivitySequenceItem();
				activitySequenceItem.setId(activitySequence.getId().toString());
				activitySequenceItem.setPredecessor(activitySequence.getPredecessor().getName());
				activitySequenceItem.setSuccessor(activitySequence.getSuccessor().getName());
				activitySequenceItem.setLinkType(activitySequence.getLinkType().getName());
				activitySequenceItems.add(activitySequenceItem);
			}
			
			/* Convertion de cette liste en tableau */
			DisplayObject[] data = new ActivitySequenceItem[activitySequenceItems.size()];
			data = (ActivitySequenceItem[]) activitySequenceItems.toArray(data);
			
			form.setPredecessorsList(new ActivitySequencesModel(data));
			
		} catch (PersistanceException pe) {
			logger.error(pe);
			context.addGlobalError("errors.persistance.select");
		}
		
		
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
