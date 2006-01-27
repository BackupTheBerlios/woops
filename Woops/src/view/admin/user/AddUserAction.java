package view.admin.user;

import java.util.HashMap;

import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.activity.manage.ManageActivityCreationForm;
import view.common.WoopsCCAction;
import business.BusinessConstantes;
import business.activity.Activity;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;
import business.user.UserRole;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
				

public class AddUserAction extends WoopsCCAction {

	public void doExecute(ActionContext context) throws Exception {
		AddUserForm form = (AddUserForm) context.form();
		
		String mode = (String)context.request().getAttribute(PresentationConstantes.PARAM_MODE);
		
		if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
			
			
			String login = (String) context.request().getAttribute(PresentationConstantes.PARAM_LOGIN);

			User user = UserManager.getInstance().getUser(login);
			
			//form.s(userId.toString());
			form.setFirstName(user.getFirstName());
			form.setLastName(user.getLastName());
			form.setLogin(user.getLogin());
			//form.setRole();			
		}
		else {
			mode = PresentationConstantes.INSERT_MODE;
			
//			form.setCaption("form.title.manageActivityCreation.insert");
//			form.setDisableNext("false");
		}
		
//		form.setMode(mode);
		
		context.forwardToInput();
	}

	public void add_onClick(FormActionContext context) {
		ActionForward retour = null;	
		
		AddUserForm addUserForm = (AddUserForm) context.form();

		// controle de la validation du formulaire
		context.addErrors(addUserForm.validate(context.mapping(),context.request()));
		
	    if (!context.hasErrors()) {
			retour = context.mapping().findForward(PresentationConstantes.FORWARD_SUCCESS);
			User user = new User();
			user.setFirstName(addUserForm.getFirstName());
			user.setLastName(addUserForm.getLastName());
			user.setLogin(addUserForm.getLogin());
			user.setPassword(addUserForm.getPassword());
			user.setRole(new UserRole("dev","developer"));
			try {
				UserManager.getInstance().insert(user);
				context.addGlobalMessage("admin.msg.info.user.validate");
			}
			catch (PersistanceException p)
			{
				context.addGlobalError("admin.msg.error.user.insert");
				System.out.println(p.getMessage());
			}
			catch(DoublonException e)
			{
				context.addGlobalError("admin.msg.error.user.insert");
				System.out.println(e.getMessage());
			}
			
			
        } else {
        	retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
        }
	
	context.forward(retour);
	}
	
}
