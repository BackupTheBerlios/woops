/**
 * 
 */
package view.activity.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.activity.Activity;
import business.event.Event;
import business.event.EventManager;
import business.hibernate.exception.PersistanceException;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;

/**
 * @author Simon REGGIANI
 * Action permettant de lister les evenements qui ne sont pas encore passés du projet
 */
public class ListEventsAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ListEventsAction.class);    

	public void doExecute(ActionContext context) throws Exception {
		try {
			this.loadLists(context);
			context.forwardToInput();
	    } catch (PersistanceException pe) {
	    	logger.error(pe);
			context.addGlobalError("errors.persistance.select");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);  
		} catch (Throwable t) {
			logger.error(t);
			context.addGlobalError("errors.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);  
		}	
	}
	
	/**
	 * Cette methode constitue les 2 listes a partir de la BD
	 * @param contexte	contexte d'execution de la servlet
	 * @throws Exception	indique qu'une erreur s'est produite pendant le traitement
	 */
	private void loadLists(ActionContext context) throws Exception {
		ListEventsForm form = (ListEventsForm)context.form();
		EventManager evtMngr = EventManager.getInstance();

		// Recuperation du participant connecte
		User sessionUser = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
    	
    	// Recuperation de la liste des evenements pas passés du projet
		Collection listEventsNotOccured = evtMngr.getEventsByBde(sessionUser.getDefaultBDEId(),false);
	
		// Recuperation de la liste des evenements pas passés du projet
		Collection listEventsOccured = evtMngr.getEventsByBde(sessionUser.getDefaultBDEId(),true);
	
		
		// Peuplage des 2 listes
		form.setListEventsNotOccured(fromEventsToEventItems(listEventsNotOccured,context));
		form.setListEventsOccured(fromEventsToEventItems(listEventsOccured,context));
	}

	/**
	 * Permet de transformer une collection d'Event en ListEventsModel d'EventItem
	 * @param list
	 * @return
	 */
	private ListEventsModel fromEventsToEventItems(Collection list, ActionContext context) {
		Iterator iter = list.iterator();
		
		HashMap activitiesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP);
		
		Event evt;
		EventItem evtItem = new EventItem();
		Activity activity;
		Collection listEventsItem = new ArrayList();
		
		while(iter.hasNext()) {
			evt = (Event)iter.next();
	
			// MAJ des infos de l'EventItem
			evtItem.setId(evt.getId().toString());
			evtItem.setName(evt.getName());
			evtItem.setDetails(evt.getDetails());
			
			// Récupération de l'activité dans la hashmap pour avoir son nom
			activity = (Activity)activitiesMap.get(evt.getActiviyId());
			evtItem.setActivityName(activity.getName());
			
			// Ajout de l'EventItem dans l'arrayList
			listEventsItem.add(evtItem);
		}
		
		//Conversion de la liste en tableau d'items
		DisplayObject[] result = new EventItem[listEventsItem.size()];
		listEventsItem.toArray(result);
		
		return new ListEventsModel(result);
	}
}
