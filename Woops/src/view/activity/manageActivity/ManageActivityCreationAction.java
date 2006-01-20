package view.activity.manageActivity;

import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.WoopsCCAction;
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
	
	private static Logger logger = Logger.getLogger(ManageActivityCreationAction.class); 
	ActionForward forward = null;
	
	
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
	
		String mode = (String)context.request().getAttribute(PresentationConstantes.PARAM_ACTION_SUBMIT);
		
		if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
			HashMap activitiesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP);
			
			Integer activityId = new Integer((String)context.request().getAttribute(PresentationConstantes.PARAM_ACTIVITY_ID));

			Activity activity = (Activity)activitiesMap.get(activityId);
				
			form.setActivityId(activityId.toString());
			form.setName(activity.getName());
			form.setDetails(activity.getDetails());			
			
		}
		else {
			mode = PresentationConstantes.INSERT_MODE;
		}
		
		form.setActionSubmit(mode);
		context.forward(context.mapping().findForward(PresentationConstantes.FORWARD_SUCCESS)); 
	}
	
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser apres validation du formulaire
	 * @throws DoublonException 
	 * @throws PersistanceException 
	 */
	
	public void save_onClick(FormActionContext context) {
		
		ManageActivityCreationForm form = (ManageActivityCreationForm) context.form();

		// controle de la validation du formulaire
		context.addErrors(form.validate(context.mapping(),context.request()));
		
	    if (!context.hasErrors()) {
			try {
				
				//R?cup?ration de l'identifiant du participant connect?
		    	User user = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
				
				String mode = context.request().getParameter(PresentationConstantes.PARAM_ACTION_SUBMIT);
				
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
					ActivityManager.getInstance().insert(activity);
					
					context.addGlobalMessage("msg.info.activity.inserted", activity.getName());
				}
				else if (mode.equals(PresentationConstantes.UPDATE_MODE)) {
					
					HashMap activitiesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP);
					
					Activity activity = (Activity)activitiesMap.get(form.getActivityId());
					
					//R?cup?ration des champs que l'utilisateur a pu entrer
					activity.setDetails(form.getDetails());
					activity.setName(form.getName());
					
					//activity.setUserModification(user.getFirstName()+" "+user.getLastName());
					activity.setUserModification((user.getId().toString()));
					activity.setDateModification(new Date());
					ActivityManager.getInstance().update(activity);
					
					context.addGlobalMessage("msg.info.activity.updated", activity.getName());
				}
					
				forward = context.mapping().findForward(PresentationConstantes.FORWARD_ACTION);
			} catch (PersistanceException pe) {
				forward = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
                pe.printStackTrace();
                context.addGlobalError("errors.persistance.global");
			} catch (DoublonException de) {
				forward = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
                context.addGlobalError("errors.persistance.doublon");
			}	

        } else {
        	forward = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
        }
			
		context.forward(forward);

	}
	
}
