/**
 * 
 */
package view.activity.event;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.event.Event;
import business.event.EventManager;

import com.cc.framework.adapter.struts.ActionContext;

/**
 * @author Simon REGGIANI
 * Permet de gérer ( créer ou modifier ) un evenement
 */
public class ManageEventAction extends WoopsCCAction {

	public void doExecute(ActionContext context) throws Exception {
		ManageEventForm form = (ManageEventForm)context.form();
		EventManager evtMngr = EventManager.getInstance();
		
		String mode = (String)context.request().getAttribute(PresentationConstantes.PARAM_MODE);
		
		if (mode.equals(PresentationConstantes.UPDATE_MODE)){
			Integer eventId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_EVENT_ID);
		
			/* Récupération de l'evenement à modifier */
			Event event = (Event)evtMngr.get(Event.class,eventId);
		}
	}

}
