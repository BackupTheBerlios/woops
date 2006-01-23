package view.activity.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.activity.ActivityItem;
import view.activity.performing.ListActivitiesModel;
import view.common.WoopsCCAction;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;


/**
 * @author Simon Reggiani
 * ManageActivityDependancesAction : Action permetant de g�rer les d�pendances d'une activit�
 */
public class ManageActivityDependancesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ManageActivityDependancesAction.class);   
	
	/**
	 * Constructeur vide
	 *
	 */
	public ManageActivityDependancesAction() {
		super();
	}


	
	
	/**
	 * @param context : contexte de l'action. Contient le form, la requette, ...
	 * @throws IOException, ServletException
	 * Permet d'initialiser le formulaire de gestion des d�pendances d'une activit�.
	 * 	-> Initialise les options possibles pour la liste des activit�s d�pendantes
	 * 	-> Initialise les activit�s d�pendantes d�j� existantes de l'activit�
	 */
	public void doExecute(ActionContext context) throws IOException, ServletException {
		logger.debug("ManageActivityDependancesAction.doExecute()");
		
		if (context.form()==null) {
			context.request().setAttribute(context.mapping().getAttribute(), new ManageActivityDependancesForm());
		}
	
		try {
			/** Met � jour les attributs du ManageActivityDependancesForm **/
			setPossibleDependancesOptions(context);
			setRealActivityDependancesKeys(context);
		} catch (PersistanceException pe) {
			context.addGlobalError("errors.persistance.global");
		} catch (Throwable t) {
			context.addGlobalError("errors.global");
		}
		
		/** Affiche la page avec le swap select **/
	    context.forwardByName(PresentationConstantes.FORWARD_SUCCESS); 
	}
	
	
	/**
	 * @param context
	 * @throws PersistanceException
	 * 
	 * Permet de mettre � jour de l'attribut possibleDependancesOptions du Form
	 */
	private void setPossibleDependancesOptions(ActionContext context) throws PersistanceException {
		Collection possibleActivityDependancesMgr = null;
		Collection possibleActivityDependancesItems = null;
		ActivityItem item = null;
		
		ManageActivityDependancesForm madForm = (ManageActivityDependancesForm) context.form();
		
		/* Recup�ration de l'id de l'activit� dont on veut g�rer les d�pendances dans la requete*/
		Integer activityId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_ACTIVITY_ID);
		
		/* Sauvegarde dans le form */
		madForm.setActivityId(activityId.toString());
		
		/* R�cup�ration de la liste des d�pendances possible de l'activit� via le manager */
		possibleActivityDependancesMgr = ActivityManager.getInstance().getPossibleActivityPredecessors(activityId);  	
		
		/**
		 * Conversion de la liste d'Activity retourn�e par getPossibleActivityDependances
		 * en liste d'ActivityItem
		 */
    	Iterator iter = possibleActivityDependancesMgr.iterator();
    	possibleActivityDependancesItems = new ArrayList();

    	while (iter.hasNext()) {
    		Activity activity = (Activity)iter.next();
			
			item = new ActivityItem();
			item.setId(activity.getId().toString());
			item.setName(activity.getName());
			
			possibleActivityDependancesItems.add(item);
		}
		
		/**
		 * Convertion la liste d'ActivityItem en tableau
		 */
		DisplayObject[] data = new ActivityItem[possibleActivityDependancesItems.size()];
		possibleActivityDependancesItems.toArray(data);
		
		/**
		 * Mis � jour de l'attribut possibleDependancesOptions du Form
		 * en passant par un ListActivitiesModel
		 */
		ListActivitiesModel model = new ListActivitiesModel(data);
		
		
		/**
		 * Sauvegarde du model dans la session
		 */
		context.session().setAttribute(PresentationConstantes.KEY_POSSIBLE_DEPENDANCES_OPTIONS,model);
		
		madForm.setPossibleDependancesOptions(model);
	}
	
	
	/**
	 * @param context
	 * @throws PersistanceException
	 * 
	 * Permet de mettre � jour de l'attribut realActivityDependancesKeys du Form
	 */
	private void setRealActivityDependancesKeys(ActionContext context) throws PersistanceException {
		Collection activityDependances = null;

		ManageActivityDependancesForm madForm = (ManageActivityDependancesForm) context.form();
		
		/* Recup�ration de l'id de l'activit� dont on veut g�rer les d�pendances dans le form 
		 * (il a �t� mis � jour dans la methode pr�c�dente : setPossibleDependancesOptions */
		Integer activityId = new Integer(madForm.getActivityId());
		activityDependances = ActivityManager.getInstance().getPredecessors(activityId);  	
		
		
		/**
		 * Convertit la liste des cl�s de type Integer
		 * de la liste activityDependancesKeys en tableau de cl�s de type String
		 */  
    	String[] listStringKeys = new String[activityDependances.size()];
		Iterator iter = activityDependances.iterator();
		for (int i=0; iter.hasNext(); i++) {
			listStringKeys[i]=((Activity)iter.next()).getId().toString();
		}
		
		/**
		 * Sauvegarde du tableau des anciennes cl�s des activit� depandantes dans la session
		 */
		context.session().setAttribute(PresentationConstantes.KEY_OLD_DEPENDANCES_KEYS,listStringKeys);
		
		/**
		 * Mis � jour de l'attribut realDependancesKeys du Form
		 */
		madForm.setRealDependancesKeys(listStringKeys);
			
			
	}
	
	/**
	 * @param		ctx		FormActionContext
	 */
	public void saveDependances(FormActionContext context) {

		ManageActivityDependancesForm form = (ManageActivityDependancesForm) context.form();
		String[] realDepedancesKeys  = form.getRealDependancesKeys();  
		
		/**
		 * R�cup�ration du tableau des anciennes cl�s des activit� depandantes 
		 * depuis la session
		 */
		String [] oldActivityDependancesKeys = (String [])context.session().getAttribute(PresentationConstantes.KEY_OLD_DEPENDANCES_KEYS);
	
		/**
		 * Sauvegarde des d�pendances
		 */
		try {
			/* Recup�ration de l'id de l'activit� dont on veut g�rer les d�pendances dans le form 
			 * (il a �t� mis � jour dans la methode pr�c�dente : setPossibleDependancesOptions */
			Integer activityId = new Integer(form.getActivityId());
			ActivityManager.getInstance().saveActivityDependances(activityId,oldActivityDependancesKeys,realDepedancesKeys);
			
			/**
			 * Suppression des attributs de la session
			 */
			context.session().removeAttribute(PresentationConstantes.KEY_OLD_DEPENDANCES_KEYS);
			context.session().removeAttribute(PresentationConstantes.KEY_POSSIBLE_DEPENDANCES_OPTIONS);
			
			//R�percution de l'attribut
			context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,activityId);
			
			
		} catch (PersistanceException e) {
			context.addGlobalError("errors.persistance.global");
			/** Rappel du formulaire avec le message d'erreur **/
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		} catch (DoublonException e) {
			// Ne doit pas passer par l�
		} catch (ForeignKeyException e) {
			// Ne doit pas passer par l�
		} catch (Throwable t) {
			logger.error(t);
			context.addGlobalError("errors.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);  
		}
	}
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton finish (retour � listActivities)
	 */
	
	public void finish_onClick(FormActionContext context) {
		
		saveDependances(context);
		context.forwardByName(PresentationConstantes.FORWARD_FINISH);
	}
	
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton next (gestion
	 * des types des d�pendances de l'activit�)
	 */
	
	public void next_onClick(FormActionContext context) {
		
		saveDependances(context);
		context.forwardByName(PresentationConstantes.FORWARD_NEXT);
	}
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton previous (manageActivityCreation)
	 */
	
	public void previous_onClick(FormActionContext context) {
		//R�percution de l'attribut
		ManageActivityDependancesForm form = (ManageActivityDependancesForm) context.form();
		Integer activityId = new Integer(form.getActivityId());
		context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,activityId);
		
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.UPDATE_MODE);
		
		context.forwardByName(PresentationConstantes.FORWARD_PREVIOUS);
	}
}
