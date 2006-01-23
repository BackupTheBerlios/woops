package view.activity.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.activity.ActivitySequenceItem;
import view.common.WoopsCCAction;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.sequence.ActivitySequence;
import business.activity.sequence.ActivitySequenceManager;
import business.activity.sequencetype.ActivitySequenceType;
import business.activity.sequencetype.ActivitySequenceTypeManager;
import business.hibernate.exception.PersistanceException;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.control.SimpleListControl;


/**
 * @author Simon Reggiani
 * ManageDependancesTypesAction : Action permetant de gérer les types des dépendances d'une activité
 */
public class ManageDependancesTypesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ManageDependancesTypesAction.class);   
	
	ActionForward forward = null;
	
	/**
	 * Constructeur vide
	 *
	 */
	public ManageDependancesTypesAction() {
		super();
	}


	
	/**
	 * @param context : contexte de l'action. Contient le form, la requette, ...
	 * @throws IOException, ServletException
	 * Permet d'initialiser le formulaire de gestion des types des dépendances d'une activité.
	 * 	-> Initialise la liste des dépendances avec leur type
	 */
	public void doExecute(ActionContext context) throws IOException, ServletException {
		logger.debug("ManageActivityDependancesAction.doExecute()");
		
		if (context.form()==null) {
			context.request().setAttribute(context.mapping().getAttribute(), new ManageActivityDependancesForm());
		}
	
		try {
			/** Met à jour les attributs du ManageDependancesTypesForm **/
			setDependancesList(context);
		} catch (PersistanceException pe) {
			context.addGlobalError("errors.persistance.global");
		} catch (Throwable t) {
			context.addGlobalError("errors.global");
			t.printStackTrace();
		}
		
		/** Affiche la page avec la liste à boutons radio **/
	    context.forward(context.mapping().findForward(PresentationConstantes.FORWARD_SUCCESS)); 
	}
	
	
	private void setDependancesList(ActionContext context) throws PersistanceException {
		Collection dependancesListMgr;
		
		
		/* Recupération de l'id de l'activité dont on veut gérer les dépendances dans la requete*/
		Integer activityId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_ACTIVITY_ID);
		
		/* Récupération des la liste des dépendances de cette activité en BD */
		dependancesListMgr = ActivityManager.getInstance().getActivitySequences(activityId);
	
		/* Convertion de cette liste en liste d'ActivitySequenceType */
		Iterator iter = dependancesListMgr.iterator();
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
		
	
		/* Sauvegarde de la simplListControl dans la session pour que la page jsp y accede*/
		SimpleListControl list = new SimpleListControl();
		list.setDataModel(new ManageDependancesTypesModel(data));
		context.session().setAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST,list);
		
		/* Sauvegarde de la liste d'ActivitySequence récupérée avec le manager */
		context.session().setAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST_MNGR,dependancesListMgr);
	}
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton finish (retour à listActivities)
	 */
	
	public void finish_onClick(FormActionContext context) {
		
		SimpleListControl dependancesListSlc = (SimpleListControl)context.session().getAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST);
		Collection dependancesListMgr = (Collection)context.session().getAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST_MNGR);
		
		ManageDependancesTypesModel listDataModel = (ManageDependancesTypesModel)dependancesListSlc.getDataModel();
		
		ActivitySequenceManager actSeqMngr = ActivitySequenceManager.getInstance();
		ActivitySequenceTypeManager actSeqTypeMngr = ActivitySequenceTypeManager.getInstance();
		
		ActivitySequenceType actSeqType;
		ActivitySequenceItem actSeqItem;
		ActivitySequence actSeq;
		
		Iterator iter = dependancesListMgr.iterator();
		for(int i=0; iter.hasNext(); i++) {
			actSeqItem = (ActivitySequenceItem)listDataModel.getElementAt(i);
			try {
				actSeqType = actSeqTypeMngr.getActivitySequenceTypeByName(actSeqItem.getLinkType());
				actSeq = (ActivitySequence)iter.next();
				
				actSeq.setLinkType(actSeqType);
				
				actSeqMngr.update(actSeq);
			} catch (PersistanceException e) {
				context.addGlobalError("errors.persistance.global");
			}
			
		}
		/* Récupération de l'activité dans la hashmap pour connaitre son nom */
		HashMap activitiesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP);
		
		ManageDependancesTypesForm form = (ManageDependancesTypesForm) context.form();
		
		Activity activity = (Activity)activitiesMap.get(new Integer(form.getActivityId()));
		
		context.addGlobalMessage("msg.info.activity.dependancesTypes.saved",activity.getName());
		
		/** Appel de la page de garde **/
		forward = context.mapping().findForward(PresentationConstantes.FORWARD_ACTION);
	
		context.forward(forward);
	}
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton previous (manageActivityCreation)
	 */
	
	public void previous_onClick(FormActionContext context) {
		//Répercution de l'attribut
		ManageDependancesTypesForm form = (ManageDependancesTypesForm) context.form();
		Integer activityId = new Integer(form.getActivityId());
		context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,activityId);
		
		context.forwardByName(PresentationConstantes.FORWARD_PREVIOUS);
	}

}
