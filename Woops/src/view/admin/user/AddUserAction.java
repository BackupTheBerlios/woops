package view.admin.user;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.common.WoopsCCAction;

import com.cc.framework.adapter.struts.ActionContext;
				

public class AddUserAction extends WoopsCCAction {

	public void doExecute(ActionContext context) throws Exception {
		ActionForward retour = null;	
		
			AddUserForm addUserForm = (AddUserForm) context.form();

			// controle de la validation du formulaire
			context.addErrors(addUserForm.validate(context.mapping(),context.request()));
			
		    if (!context.hasErrors()) {
				
				
						
				retour = context.mapping().findForward(PresentationConstantes.FORWARD_SUCCESS);
				
					
				

	        } else {
	        	retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
	        }
		    System.err.println(retour+"caca prout caca prout");
		
		context.forward(retour);
	}

}
