package view.activity.manage;

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
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;


/**
 * @author Benjamin TALOU
 * ManageActivityCreationAction : permet de creer une nouvelle activite
 */
public class ManageActivityCreationAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ManageActivityDependancesAction.class);   
	
	/**
	 * Constructeur vide
	 *
	 */
	public ManageActivityCreationAction() {
		super();
	}
	
	
	/**
	 * 
	 * Action a realiser avant l'affichage du formulaire
	 */

	public void doExecute(ActionContext context) {

		
		ManageActivityCreationForm form = (ManageActivityCreationForm) context.form();
	
		String mode = (String)context.request().getAttribute(PresentationConstantes.PARAM_MODE);
		
		if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
			HashMap activitiesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP);
			
			Integer activityId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_ACTIVITY_ID);

			Activity activity = (Activity)activitiesMap.get(activityId);
				
			form.setActivityId(activityId.toString());
			form.setName(activity.getName());
			form.setDetails(activity.getDetails());			
			form.setCaption("form.title.manageActivityCreation.update");
			
			if ( activity.getState().equals(BusinessConstantes.ACTIVITY_STATE_CREATED) ) {	
				form.setDisableNext("false");
			}
			else {
				form.setDisableNext("true");
				context.addGlobalMessage("msg.info.activity.manageActivityDependances", activity.getName());
			}
				
			
		}
		else {
			mode = PresentationConstantes.INSERT_MODE;
			
			form.setCaption("form.title.manageActivityCreation.update");
			form.setDisableNext("false");
		}
		
		form.setMode(mode);
		context.forwardByName(PresentationConstantes.FORWARD_SUCCESS);
	
	}
	
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton finish (retour ? listActivities)
	 */
	
	public void finish_onClick(FormActionContext context) {
		
		saveActivity(context);
		context.forwardByName(PresentationConstantes.FORWARD_FINISH);
	}
	
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton next (gestion
	 * des d?pendances de l'activit?)
	 */
	
	public void next_onClick(FormActionContext context) {
		
		saveActivity(context);
		context.forwardByName(PresentationConstantes.FORWARD_NEXT);
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
	
	
	/**
	 * Methode effectuant la sauvegarde en base
	 * @param context
	 */
	public void saveActivity(FormActionContext context) {
		
		ManageActivityCreationForm form = (ManageActivityCreationForm) context.form();

		// controle de la validation du formulaire
		context.addErrors(form.validate(context.mapping(),context.request()));
		
	    if (!context.hasErrors()) {
			try {
				
				//R?cup?ration de l'identifiant du participant connect?
		    	User user = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
				
				String mode = context.request().getParameter(PresentationConstantes.PARAM_MODE);
				
				Integer activityId = null;
				
				if (mode.equals(PresentationConstantes.INSERT_MODE)) {
					Activity activity = new Activity();
					
					// R?cup?ration des champs que l'utilisateur a pu entrer
					activity.setDetails(form.getDetails());
					activity.setName(form.getName());
					
					activity.setState(new CreatedActivityState());
					
					activity.setUserId(user.getId());
					
					//activity.setUserCreation(user.getFirstName()+" "+user.getLastName());
					activity.setUserCreation((user.getId().toString()));
					activity.setDateCreation(new Date());
					
					activityId = (Integer)ActivityManager.getInstance().insertWithGetId(activity);
					
					context.addGlobalMessage("msg.info.activity.inserted", activity.getName());
				}
				else if (mode.equals(PresentationConstantes.UPDATE_MODE)) {
					
					HashMap activitiesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP);
				
					activityId = new Integer(form.getActivityId());
					
					Activity activity = (Activity)activitiesMap.get(activityId);

					//R?cup?ration des champs que l'utilisateur a pu entrer
					activity.setDetails(form.getDetails());
					activity.setName(form.getName());
					
					//activity.setUserModification(user.getFirstName()+" "+user.getLastName());
					activity.setUserModification((user.getId().toString()));
					activity.setDateModification(new Date());
					ActivityManager.getInstance().update(activity);
					
					context.addGlobalMessage("msg.info.activity.updated", activity.getName());
				}
				
				// R?percution de l'attribut
				context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,activityId);
				
			} catch (PersistanceException pe) {
				context.forwardByName(PresentationConstantes.FORWARD_ERROR);
                context.addGlobalError("errors.persistance.global");
			} catch (DoublonException de) {
				context.forwardByName(PresentationConstantes.FORWARD_ERROR);
                context.addGlobalError("errors.persistance.doublon");
			}	
        } else {
        	context.forwardByName(PresentationConstantes.FORWARD_ERROR);
        }
	}
	
	
	
}
