package view.activity.manageActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.WoopsCCAction;
import view.activity.ActivityItem;
import view.activity.ListActivitiesModel;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.model.ListDataModel;
import com.cc.framework.util.StringHelp;


/**
 * @author Simon Reggiani
 * ManageActivityDependancesAction : Action permetant de gérer les dépendances d'une activité
 */
public class ManageActivityDependancesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ManageActivityDependancesAction.class);   
	
	ActionForward forward = null;
	
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
	    context.forward(context.mapping().findForward(PresentationConstantes.FORWARD_SUCCES)); 
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
		
		/**
		 * Récupération de la liste des dépendances possible de l'activité via le manager 
		 */
		ManageActivityDependancesForm madForm = (ManageActivityDependancesForm) context.form();
		possibleActivityDependancesMgr = ActivityManager.getInstance().getPossibleActivityDependances(new Integer(2));  	
		
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
		data = (ActivityItem[]) possibleActivityDependancesItems.toArray(data);
		
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
		
		activityDependances = ActivityManager.getInstance().getActivityDependances(new Integer(2));  	
		
		
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
	 * This Method is called if the Save-Button on the
	 * HTML-Page is pressed.
	 * 
	 * @param		ctx		FormActionContext
	 */
	public void save_onClick(FormActionContext context) {

		ManageActivityDependancesForm form = (ManageActivityDependancesForm) context.form();
		String[] realDepedancesKeys  = form.getRealDependancesKeys();  
		
		/**
		 * Récupération du tableau des anciennes clés des activité depandantes 
		 * depuis la session
		 */
		String [] oldActivityDependancesKeys = (String [])context.session().getAttribute(PresentationConstantes.KEY_OLD_DEPENDANCES_KEYS);
		
		/**
		 * String msg1 = (realDepedancesKeys.length == 0) ? "rien" : "["+StringHelp.join(realDepedancesKeys, ',')+"]";
		 * msg1 += (oldActivityDependancesKeys.length == 0) ? "rien" : "  ["+StringHelp.join(oldActivityDependancesKeys, ',')+"]";
		 * context.addGlobalMessage("message.insert.ok",msg1);
		 */

		/**
		 * Sauvegarde des dépendances
		 */
		try {
			ActivityManager.getInstance().saveActivityDependances(new Integer(2),oldActivityDependancesKeys,realDepedancesKeys);
			context.addGlobalMessage("message.insert.ok","");
		} catch (PersistanceException e) {
			context.addGlobalError("errors.persistance.global");
		} catch (DoublonException e) {
			// Ne doit pas passer par là
			e.printStackTrace();
		} catch (ForeignKeyException e) {
			// Ne doit pas passer par là
			e.printStackTrace();
		}
		
		
		/**
		 * Récupération des options possibles
		 */
		ListDataModel possibleDependancesOptions = (ListDataModel)context.session().getAttribute(PresentationConstantes.KEY_POSSIBLE_DEPENDANCES_OPTIONS);

		/**
		 * Remise à jour des 2 selects du swap select
		 * --> Remise à jour du Form
		 */
		form.setPossibleDependancesOptions(possibleDependancesOptions);
		form.setRealDependancesKeys(realDepedancesKeys);
		
		/**
		 * Suppression des attributs de la session
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * !!!!!!!! A ne pas faire ici si l'utilisateur veut refaire une modif	!!!
		 * !!!!!!!! A faire quand l'utilisateur veut quitter la page			!!!
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * context.session().removeAttribute(PresentationConstantes.KEY_OLD_DEPENDANCES_KEYS);
		 * context.session().removeAttribute(PresentationConstantes.KEY_POSSIBLE_DEPENDANCES_OPTIONS);
		 */
		
		/** Raffiche la page avec le message d'insertion **/
	    context.forward(context.mapping().findForward(PresentationConstantes.FORWARD_SUCCES)); 
	}

}
