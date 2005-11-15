package view.activity;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import view.PresentationConstantes;
import view.WoopsAction;


public class CreateActivityAction extends WoopsAction {
	private static Logger logger = Logger.getLogger(CreateActivityAction.class);    
    
	public ActionForward create(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {

			logger.debug("createActivity()");
		
			ActionForward retour = null;
        	ActionMessages message = new ActionMessages();
			
        	if (form==null) {
				form = new CreateActivityForm();
				request.setAttribute(mapping.getAttribute(), form);
        	}

        	CreateActivityForm createForm = (CreateActivityForm) form;
			

			
			// r?cup?ration de les noms de toutes les activités dans la BD
			ArrayList activitiesNamesList = new ArrayList();
			
			activitiesNamesList.add("activity 1");
			activitiesNamesList.add("activity 2");
			activitiesNamesList.add("activity 3");
			activitiesNamesList.add("activity 4");
			
			
			createForm.setActivitiesNamesList(activitiesNamesList);
			
			retour = mapping.findForward(PresentationConstantes.FORWARD_SUCCES);
	        
            saveMessages(request, message);

	        return retour; 		
			
		}

}
