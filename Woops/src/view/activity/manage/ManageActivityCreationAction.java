package view.activity.manage;

import java.util.Date;
import java.util.HashMap;

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
			

			if (activity.getOnGoing().equals(PresentationConstantes.YES))
				form.setActivityOnGoing("true");
			else
				form.setActivityOnGoing("false");
			
			
			// si activité en cours, on desactive le checkbox
			
			if (! activity.getState().equals(BusinessConstantes.ACTIVITY_STATE_CREATED) ){
				
				System.out.print("inProgress\n\n");
				form.setDisableFreeActivityCheckbox("true");
				form.setDisableActivityOnGoingCheckbox("true");
			}
			// sinon on affichage le checkbox selon son cas
			else{
				System.out.print("NOT inProgress\n\n");
				form.setDisableFreeActivityCheckbox("false");
				if (activity.getUserId()==null){
					form.setFreeActivity("true");
				}else{
					form.setFreeActivity("false");
				}

				form.setDisableActivityOnGoingCheckbox("false");
			}
			
			
			
			form.setCaption("form.title.manageActivityCreation.update");
			form.setTooltipFinish("form.tooltip.manageActivityCreation.finish.update");
			form.setTooltipNext("form.tooltip.manageActivityCreation.next.update");
			
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
			
			form.setCaption("form.title.manageActivityCreation.insert");
			form.setTooltipFinish("form.tooltip.manageActivityCreation.finish.insert");
			form.setTooltipNext("form.tooltip.manageActivityCreation.next.insert");
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
		
		Activity activity = new Activity();
		activity = saveActivity(context);
		
		if(activity!=null){
			
			// on forwarde selon le cas
			// on ne forwarde pas si l'objet n'a pas été crée
			
			if (activity.getId()!=null)
				
				if ((activity.getUserId()==null)&&(activity.getName()!=null)){
					context.forwardByName(PresentationConstantes.FORWARD_FINISH_FREE_ACTIVITIES);
				}else{
					context.forwardByName(PresentationConstantes.FORWARD_FINISH);
				}
			
			}
		}
	
	
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton next (gestion
	 * des d?pendances de l'activit?)
	 */
	
	public void next_onClick(FormActionContext context) {
		
		Activity activity = null;
		activity = saveActivity(context);
		
		if(activity.getId()!= null) context.forwardByName(PresentationConstantes.FORWARD_NEXT);
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
	 * @return true si ca s'est bien pass?
	 */
	public Activity saveActivity(FormActionContext context) {
		

		
		// activité retournée
		Activity act = new Activity();
		
		ManageActivityCreationForm form = (ManageActivityCreationForm) context.form();

		
		String mode = context.request().getParameter(PresentationConstantes.PARAM_MODE);
		
		//R?percution de l'attribut mode en cas d'erreur
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,mode);
		
		Integer activityId = null;
		
		if (mode.equals(PresentationConstantes.UPDATE_MODE)) {
			//R?percution de l'attribut activityId
			activityId = new Integer(form.getActivityId());
			context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,activityId);
		}
		
			
		
		
		//controle de la validation du formulaire
		context.addErrors(form.validate(context.mapping(),context.request()));
		
	    if (!context.hasErrors()) {
			try {
				
				// Recuperation de l'identifiant du participant connecte et du projet
		    	User user = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
		    	
				// modif => message affiché a l'utilisateur apres ajout ou modification d'une activité
				// 0 : pas de modif
				// 1 : l'utilisateur courant affecté
				// 2 : la tache est libre
		    	// 3 : affectation à l'utilisateur lors de la modification
		    	// 4 : liberation de l'activité lors de la modification
		    	
		    	Integer modif = new Integer(0);
	
		    	
		    	
				if (mode.equals(PresentationConstantes.INSERT_MODE)) {
					Activity activity = new Activity();
										
					// R?cup?ration des champs que l'utilisateur a pu entrer
					activity.setDetails(form.getDetails());
					activity.setName(form.getName().trim());
					activity.setState(new CreatedActivityState());
					activity.setUserCreation((user.getId().toString()));
					activity.setDateCreation(new Date());
					activity.setBdeId(user.getDefaultBDEId());
					
					if (form.getActivityOnGoing()!=null)
						activity.setOnGoing(PresentationConstantes.YES);
					else
						activity.setOnGoing(PresentationConstantes.NO);
					
					// La reaffection de l'activité (soit libre (null) soit à un user)
					activity.setUserId((form.getFreeActivity()!=null)?(null):((Integer) user.getId()));
					
					
					modif = (activity.getUserId()==null)?(new Integer(2)):(new Integer(1));
					
					// on insere l'activité
					activityId = (Integer)ActivityManager.getInstance().insert(activity);
					
					// R?cup?ration la hashmap pour y rajouter l'activit? 
					HashMap activitiesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP);		
					activitiesMap.put(activityId,activity);
					context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,activityId);
					
					
					String message = new String("");
					
					switch(modif.intValue()){
					
					case(1):
						message = "msg.info.activity.inserted.notFree";
					break;
					
					case(2):
						message = "msg.info.activity.inserted.free";
					break;
					
					default :
						message = "msg.info.activity.inserted";
					}
					
					
					act = activity;
					
					context.addGlobalMessage(message, activity.getName());
					

				}
				else if (mode.equals(PresentationConstantes.UPDATE_MODE)) {
					
					HashMap activitiesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP);
					Activity activity = (Activity)activitiesMap.get(activityId);
							
					
					// on prend l'etat de l'utlisateur associé AVANT la possible réaffection
					Integer idbefore = activity.getUserId();
					
					// La reaffection de l'activité (soit libre (null) soit à un user)
					activity.setUserId((form.getFreeActivity()!=null)?(null):((Integer) user.getId()));
					
					// on prend l'etat de l'utlisateur associé APRES la possible réaffection
					Integer idafter = activity.getUserId();
					
				
					// si il y a une difference entre les etats on reecrit le message
					if(idbefore != idafter){	
						// on regarde sil s'agit d'un affectation ou d'une liberation
						if (idbefore==null) modif = new Integer(4);
						if (idafter==null) modif = new Integer(5);
					}else{
						modif = new Integer(0);
					}
					

					
					/* V?rification que l'utilisateur a bien modifi? quelque chose */					
					if ( 	modif.intValue()!=0 ||
							!form.getName().trim().equals(activity.getName()) ||
							!form.getDetails().equals(activity.getDetails()) ||
							(activity.getOnGoing().equals(PresentationConstantes.YES) && form.getActivityOnGoing()==null) |
							(activity.getOnGoing().equals(PresentationConstantes.NO) && form.getActivityOnGoing()!=null)
						) {
				
						
						//R?cup?ration des champs que l'utilisateur a pu entrer
						activity.setDetails(form.getDetails());
						activity.setName(form.getName().trim());
						activity.setUserModification((user.getId().toString()));
						activity.setDateModification(new Date());
						
						if (form.getActivityOnGoing()!=null)
							activity.setOnGoing(PresentationConstantes.YES);
						else
							activity.setOnGoing(PresentationConstantes.NO);
						
						ActivityManager.getInstance().update(activity);
						
						
						String message = new String("");
						
						switch(modif.intValue()){
						
						case(1):
							message = "msg.info.activity.updated.notFree";
						break;
						
						case(2):
							message = "msg.info.activity.updated.free";
						break;
						
						case(4):
							message = "msg.info.activity.updated.affectation";
						break;
						
						case(5):
							message = "msg.info.activity.updated.liberation";
						break;
						
						default :
							message = "msg.info.activity.updated";
						}
						
						context.addGlobalMessage(message, activity.getName());
						
					}
					
					act = activity;

				}
				

			} catch (PersistanceException pe) {
                context.addGlobalError("errors.persistance.global");
			} catch (DoublonException de) {
                context.addGlobalError("errors.persistance.doublon",form.getName());
			}	
        } else {
        	context.forwardByName(PresentationConstantes.FORWARD_ERROR);
        }
	    
	    return act;
	}
	
	
	
}
