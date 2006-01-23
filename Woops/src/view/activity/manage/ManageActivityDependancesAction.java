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
 * ManageActivityDependancesAction : Action permetant de gérer les dépendances d'une activité
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
	 * Permet d'initialiser le formulaire de gestion des dépendances d'une activité.
	 * 	-> Initialise les options possibles pour la liste des activités dépendantes
	 * 	-> Initialise les activités dépendantes déjà existantes de l'activité
	 */
	public void doExecute(ActionContext context) throws IOException, ServletException {
		logger.debug("ManageActivityDependancesAction.doExecute()");
		
		if (context.form()==null) {
			context.request().setAttribute(context.mapping().getAttribute(), new ManageActivityDependancesForm());
		}
	
		try {
			/** Met à jour les attributs du ManageActivityDependancesForm **/
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
	 * Permet de mettre à jour de l'attribut possibleDependancesOptions du Form
	 */
	private void setPossibleDependancesOptions(ActionContext context) throws PersistanceException {
		Collection possibleActivityDependancesMgr = null;
		Collection possibleActivityDependancesItems = null;
		ActivityItem item = null;
		
		ManageActivityDependancesForm madForm = (ManageActivityDependancesForm) context.form();
		
		/* Recupération de l'id de l'activité dont on veut gérer les dépendances dans la requete*/
		Integer activityId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_ACTIVITY_ID);
		
		/* Sauvegarde dans le form */
		madForm.setActivityId(activityId.toString());
		
		/* Récupération de la liste des dépendances possible de l'activité via le manager */
		possibleActivityDependancesMgr = ActivityManager.getInstance().getPossibleActivityPredecessors(activityId);  	
		
		/**
		 * Conversion de la liste d'Activity retournée par getPossibleActivityDependances
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
		 * Mis à jour de l'attribut possibleDependancesOptions du Form
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
	 * Permet de mettre à jour de l'attribut realActivityDependancesKeys du Form
	 */
	private void setRealActivityDependancesKeys(ActionContext context) throws PersistanceException {
		Collection activityDependances = null;

		ManageActivityDependancesForm madForm = (ManageActivityDependancesForm) context.form();
		
		/* Recupération de l'id de l'activité dont on veut gérer les dépendances dans le form 
		 * (il a été mis à jour dans la methode précédente : setPossibleDependancesOptions */
		Integer activityId = new Integer(madForm.getActivityId());
		activityDependances = ActivityManager.getInstance().getPredecessors(activityId);  	
		
		
		/**
		 * Convertit la liste des clés de type Integer
		 * de la liste activityDependancesKeys en tableau de clés de type String
		 */  
    	String[] listStringKeys = new String[activityDependances.size()];
		Iterator iter = activityDependances.iterator();
		for (int i=0; iter.hasNext(); i++) {
			listStringKeys[i]=((Activity)iter.next()).getId().toString();
		}
		
		/**
		 * Sauvegarde du tableau des anciennes clés des activité depandantes dans la session
		 */
		context.session().setAttribute(PresentationConstantes.KEY_OLD_DEPENDANCES_KEYS,listStringKeys);
		
		/**
		 * Mis à jour de l'attribut realDependancesKeys du Form
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
		 * Récupération du tableau des anciennes clés des activité depandantes 
		 * depuis la session
		 */
		String [] oldActivityDependancesKeys = (String [])context.session().getAttribute(PresentationConstantes.KEY_OLD_DEPENDANCES_KEYS);
	
		/**
		 * Sauvegarde des dépendances
		 */
		try {
			/* Recupération de l'id de l'activité dont on veut gérer les dépendances dans le form 
			 * (il a été mis à jour dans la methode précédente : setPossibleDependancesOptions */
			Integer activityId = new Integer(form.getActivityId());
			ActivityManager.getInstance().saveActivityDependances(activityId,oldActivityDependancesKeys,realDepedancesKeys);
			
			/**
			 * Suppression des attributs de la session
			 */
			context.session().removeAttribute(PresentationConstantes.KEY_OLD_DEPENDANCES_KEYS);
			context.session().removeAttribute(PresentationConstantes.KEY_POSSIBLE_DEPENDANCES_OPTIONS);
			
			//Répercution de l'attribut
			context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,activityId);
			
			
		} catch (PersistanceException e) {
			context.addGlobalError("errors.persistance.global");
			/** Rappel du formulaire avec le message d'erreur **/
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		} catch (DoublonException e) {
			// Ne doit pas passer par là
		} catch (ForeignKeyException e) {
			// Ne doit pas passer par là
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
	 * Action a realiser lorsque l'utilisateur clique sur le bouton finish (retour à listActivities)
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
	 * des types des dépendances de l'activité)
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
		//Répercution de l'attribut
		ManageActivityDependancesForm form = (ManageActivityDependancesForm) context.form();
		Integer activityId = new Integer(form.getActivityId());
		context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,activityId);
		
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.UPDATE_MODE);
		
		context.forwardByName(PresentationConstantes.FORWARD_PREVIOUS);
	}
}
