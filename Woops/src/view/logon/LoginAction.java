package view.logon;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;

public class LoginAction extends WoopsCCAction {

	public void doExecute(ActionContext context) {
		ActionForward retour = null;
		HttpSession httpSession = context.request().getSession(false);
		
		if ((context.request().getParameter(PresentationConstantes.PARAM_ACTION_SUBMIT)==null)||(!context.request().getParameter(PresentationConstantes.PARAM_ACTION_SUBMIT).equals(PresentationConstantes.FORWARD_DECONNECT))) {
			LoginForm loginForm = (LoginForm) context.form();

			// controle de la validation du formulaire
			context.addErrors(loginForm.validate(context.mapping(),context.request()));
			
		    if (!context.hasErrors()) {
				
				try {
					//Controle de la validit? du couple login/mot de passe
					User user = UserManager.getInstance().isLoginValid(loginForm.getLogin(),loginForm.getPassword());
					
					if (user!=null) {
						//on met en session l'utilisateur
						httpSession.setAttribute(PresentationConstantes.KEY_USER,user);
						
						retour = context.mapping().findForward(PresentationConstantes.FORWARD_SUCCESS);
						if (user.getLogin().equals("faure"))
						{
							retour = retour = context.mapping().findForward(PresentationConstantes.FORWARD_ADMIN);
						}
					}
					else {
						context.addGlobalError("errors.login.invalide");
						retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
					}
				} catch (PersistanceException pe) {
					retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
                    pe.printStackTrace();
                    context.addGlobalError("errors.persistance.global");
				}	

	        } else {
	        	retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
	        }
		}
		else {
			httpSession.removeAttribute(PresentationConstantes.KEY_USER);
			
			context.session().invalidate();
              
            retour = context.mapping().findForward(PresentationConstantes.FORWARD_DECONNECT); 
		}
		context.forward(retour);
	}
}
	
	