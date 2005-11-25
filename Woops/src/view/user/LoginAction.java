package view.user;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import view.PresentationConstantes;
import view.WoopsCCAction;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;

public class LoginAction extends WoopsCCAction {

	public void doExecute(ActionContext context) {

			LoginForm loginForm = (LoginForm) context.form();

			ActionForward retour = null;
			
			HttpSession httpSession = context.request().getSession(false);
			
			// controle de la validation du formulaire
			context.addErrors(loginForm.validate(context.mapping(),context.request()));
			
		    if (!context.hasErrors()) {
				
				try {
					//Controle de la validit? du couple login/mot de passe
					User user = UserManager.getInstance().isLoginValid(loginForm.getLogin(),loginForm.getPassword());
					
					if (user!=null) {
						//on met en session l'utilisateur
						httpSession.setAttribute(PresentationConstantes.KEY_USER,user);
						
						retour = context.mapping().findForward(PresentationConstantes.FORWARD_SUCCES);
					}
					else {
						context.addGlobalError("errors.login.invalide");
						retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERREUR);
					}
				} catch (PersistanceException pe) {
                    pe.printStackTrace();
                    context.addGlobalError("errors.persistance.global");
				}	

	        } else {
	        	retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERREUR);
	        }
		    
		    context.forward(retour);
		}
}
	
	