package view.activity.manage;

import java.util.Date;
import java.util.HashMap;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
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
	 */
	
	public void save_onClick(FormActionContext context) {
		
		ManageActivityCreationForm form = (ManageActivityCreationForm) context.form();

		// controle de la validation du formulaire
		context.addErrors(form.validate(context.mapping(),context.request()));
		
	    if (!context.hasErrors()) {
			try {
				
				//Récupération de l'identifiant du participant connect?
		    	User user = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
				
				String mode = context.request().getParameter(PresentationConstantes.PARAM_ACTION_SUBMIT);
				
				if (mode.equals(PresentationConstantes.INSERT_MODE)) {
					Activity activity = new Activity();
					
					// Récup?ration des champs que l'utilisateur a pu entrer
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
				
					Activity activity = (Activity)activitiesMap.get(new Integer(form.getActivityId()));

					//R?cup?ration des champs que l'utilisateur a pu entrer
					activity.setDetails(form.getDetails());
					activity.setName(form.getName());
					
					//activity.setUserModification(user.getFirstName()+" "+user.getLastName());
					activity.setUserModification((user.getId().toString()));
					activity.setDateModification(new Date());
					ActivityManager.getInstance().update(activity);
					
					context.addGlobalMessage("msg.info.activity.updated", activity.getName());
				}
					
				context.forwardByName(PresentationConstantes.FORWARD_ACTION);
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
	
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton de gestion
	 * des dépendances de l'activité
	 */
	
	public void manageDependances_onClick(FormActionContext context) {
		context.forwardByName(PresentationConstantes.FORWARD_DEPENDANCES);
	}
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton de gestion
	 * des types des dépendances de l'activité
	 */
	
	public void manageDependancesTypes_onClick(FormActionContext context) {
		context.forwardByName(PresentationConstantes.FORWARD_DEPENDANCES_TYPES);
	}
}
