package view.activity.manageActivity;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.WoopsCCAction;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.state.CreatedActivityState;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

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
				
				//Récupération de l'identifiant du participant connecté
		    	User user = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
				
				String mode = context.request().getParameter(PresentationConstantes.PARAM_ACTION_SUBMIT);
				
				if (mode.equals(PresentationConstantes.INSERT_MODE)) {
					Activity activity = new Activity();
					
					// Récupération des champs que l'utilisateur a pu entrer
					activity.setDetails(form.getDetails());
					activity.setName(form.getName());
					
					
					activity.setState(new CreatedActivityState());
					
					activity.setUserId(user.getId());
					
					//activity.setUserCreation(user.getFirstName()+" "+user.getLastName());
					activity.setUserCreation((user.getId().toString()));
					activity.setDateCreation(new Date());
					ActivityManager.getInstance().insert(activity);
				}
				else if (mode.equals(PresentationConstantes.UPDATE_MODE)) {
					
					// Récupération de l'activity en session
					Activity activity = (Activity)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITY);
					
					//Récupération des champs que l'utilisateur a pu entrer
					activity.setDetails(form.getDetails());
					activity.setName(form.getName());
					
					//activity.setUserModification(user.getFirstName()+" "+user.getLastName());
					activity.setUserModification((user.getId().toString()));
					activity.setDateModification(new Date());
					ActivityManager.getInstance().update(activity);
				}

				forward = context.mapping().findForward(PresentationConstantes.FORWARD_ACTION);
			} catch (PersistanceException pe) {
				forward = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
                pe.printStackTrace();
                context.addGlobalError("errors.persistance.global");
			} catch (DoublonException de) {
				// Ce cas est impossible car les ids sont auto-générés
			}	

        } else {
        	forward = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
        }
			
		context.forward(forward);

	}
	
	
	/**
	 * 
	 * Action a realiser avant l'affichage du formulaire
	 */

	public void doExecute(ActionContext context) {

		
		ManageActivityCreationForm form = (ManageActivityCreationForm) context.form();
	
		String mode = (String)context.request().getAttribute(PresentationConstantes.PARAM_ACTION_SUBMIT);
		
		if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
			
			Integer activityId = new Integer((String)context.request().getAttribute(PresentationConstantes.PARAM_ACTIVITY_ID));
			
			try {
				Activity activity = ActivityManager.getInstance().getActivityById(activityId);
				form.setActivityId(activityId.toString());
				form.setName(activity.getName());
				form.setDetails(activity.getDetails());
				
				//Sauvegarde de l'activity dans la session
				context.session().setAttribute(PresentationConstantes.KEY_ACTIVITY,activity);
				
			} catch (PersistanceException pe) {
				forward = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
                pe.printStackTrace();
                context.addGlobalError("errors.persistance.global");
			}
			
			
		}
		else
		{
			mode = PresentationConstantes.INSERT_MODE;
		}
		
		form.setActionSubmit(mode);
		context.forward(context.mapping().findForward(PresentationConstantes.FORWARD_SUCCESS)); 
	}
	
	
	
	
}
