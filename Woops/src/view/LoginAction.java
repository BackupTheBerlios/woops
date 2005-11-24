package view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

public class LoginAction extends WoopsAction {

	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {

			LoginForm loginForm = (LoginForm) form;

			ActionForward retour = null;
			ActionMessages message = new ActionMessages();
			
			HttpSession httpSession = request.getSession(false);
			
			// controle de la validation du formulaire
			message = loginForm.validate(mapping,request);
			
		    if (message.isEmpty()) {
				
				try {
					//Controle de la validité du couple login/mot de passe
					User user = UserManager.getInstance().isLoginValid(loginForm.getLogin(),loginForm.getPassword());
					
					if (user!=null) {
						//on met en session l'utilisateur
						httpSession.setAttribute(PresentationConstantes.KEY_USER,user);
						
						retour = mapping.findForward(PresentationConstantes.FORWARD_SUCCES);
					}
					else {
						message.add("message", new ActionMessage("errors.login.invalide"));
						retour = mapping.findForward(PresentationConstantes.FORWARD_ERREUR);
					}
				} catch (PersistanceException pe) {
                    pe.printStackTrace();
					message.add("erreur", new ActionMessage("errors.persistance.global"));
				}	

	        } else {
	        	retour = mapping.findForward(PresentationConstantes.FORWARD_ERREUR);
	        }		
			
		    
            saveMessages(request, message);
	        	
			return retour; 
		}
}
	
	