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
	
	public void save_onClick(FormActionContext context) throws PersistanceException, DoublonException {
		
		ManageActivityCreationForm form = (ManageActivityCreationForm) context.form();


		
		Activity activity = new Activity();
		
		activity.setDetails(form.getDetails());
		activity.setName(form.getName());
		activity.setState(new CreatedActivityState());
		
		// Récupération de l'identifiant du participant connecté
    	User user = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
		activity.setUserId(user.getId());
		//activity.setUserCreation(user.getFirstName()+" "+user.getLastName());
		activity.setUserCreation((user.getId().toString()));
		
		activity.setDateCreation(new Date());
		ActivityManager.getInstance().insert(activity);
		
		
		forward = context.mapping().findForward(PresentationConstantes.FORWARD_ACTION);			
		context.forward(forward);

	}
	
	
	/**
	 * 
	 * Action a realiser avant validation du formulaire
	 */

	public void doExecute(ActionContext context) throws Exception {

		
		ManageActivityCreationForm form = (ManageActivityCreationForm) context.form();
		logger.debug("\n\n\n\n 1+"+form.getId()+" \n\n\n\n");
	
		if (context.request().getParameter(PresentationConstantes.PARAM_ACTIVITY_ID)!=null){
			
			Integer activityId = new Integer(context.request().getParameter(PresentationConstantes.PARAM_ACTIVITY_ID));
		
			logger.debug("\n\n\n\n 2 \n\n\n\n");
			
			Activity activity = new Activity();
			activity = ActivityManager.getInstance().getActivityById(activityId);

			
		}
	
		context.forward(context.mapping().findForward(PresentationConstantes.FORWARD_SUCCESS)); 
	}
	
	
	
	
}
