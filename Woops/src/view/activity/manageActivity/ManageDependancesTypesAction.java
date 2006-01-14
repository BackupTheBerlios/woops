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
import view.activity.ActivitySequenceItem;
import view.activity.ListActiviySequencesModel;
import business.activity.ActivityManager;
import business.activity.ActivitySequence;
import business.activity.ActivitySequenceManager;
import business.activity.ActivitySequenceType;
import business.activity.ActivitySequenceTypeManager;
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
		
		
		/* Recupération de l'id de l'activité dont on veut gérer les types des dépendances */
		Integer activityId = new Integer(context.request().getParameter(PresentationConstantes.PARAM_ACTIVITY_ID));
		
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
		list.setDataModel(new ListActiviySequencesModel(data));
		context.session().setAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST,list);
		
		/* Sauvegarde de la liste d'ActivitySequence récupérée avec le manager */
		context.session().setAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST_MNGR,dependancesListMgr);
	}
	
	/**
	 * This Method is called if the Save-Button on the
	 * HTML-Page is pressed.
	 * 
	 * @param		context		FormActionContext
	 */
	public void save_onClick(FormActionContext context) {
		
		SimpleListControl dependancesListSlc = (SimpleListControl)context.session().getAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST);
		Collection dependancesListMgr = (Collection)context.session().getAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST_MNGR);
		
		ListActiviySequencesModel listDataModel = (ListActiviySequencesModel)dependancesListSlc.getDataModel();
		
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
		
		
	}

}
