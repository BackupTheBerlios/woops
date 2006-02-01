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
			context.session().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.UPDATE_MODE);
			
			String userId = (String) context.request().getAttribute(PresentationConstantes.PARAM_LOGIN);
			context.session().setAttribute(PresentationConstantes.KEY_USER_ID,userId);
			HashMap usersMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_USERS_MAP);

			User user = (User)usersMap.get(new Integer(Integer.parseInt(userId)));
			if (user!=null) {
				//form.s(userId.toString());
				form.setFirstName(user.getFirstName());
				form.setLastName(user.getLastName());
				form.setLogin(user.getLogin());
				form.setPassword(user.getPassword());
				form.setPassword2(user.getPassword());
				//form.setRole();	
			}		
		}
		else {
			context.session().setAttribute(PresentationConstantes.PARAM_MODE,null);
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

		String mode = (String)context.session().getAttribute(PresentationConstantes.PARAM_MODE);
	    
		if (!context.hasErrors()) {
			retour = context.mapping().findForward(PresentationConstantes.FORWARD_ADMIN);
			User user = new User();
			user.setFirstName(addUserForm.getFirstName());
			user.setLastName(addUserForm.getLastName());
			user.setLogin(addUserForm.getLogin());
			user.setPassword(addUserForm.getPassword());
			user.setRole(new UserRole("dev","developer"));
			try {
				if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
					Integer id = new Integer (Integer.parseInt((String)context.session().getAttribute(PresentationConstantes.KEY_USER_ID)));
					user.setId(id);
					UserManager.getInstance().update(user);
					context.addGlobalMessage("admin.msg.info.user.modify");
				}
				else {
					UserManager.getInstance().insert(user);
					context.addGlobalMessage("admin.msg.info.user.validate");
				}
			}
			catch (PersistanceException p)
			{
				if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
					context.addGlobalError("admin.msg.error.user.modify");
				}
				else {
					context.addGlobalError("admin.msg.error.user.insert");
				}
				System.out.println(p.getMessage());
			}
			catch(DoublonException e)
			{
				if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
					context.addGlobalError("admin.msg.error.user.modify");
				}
				else {
					context.addGlobalError("admin.msg.error.user.insert");
			}
				System.out.println(e.getMessage());
			}
	    	
			
			
        } else {
        	retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
        }
	
	context.forward(retour);
	}
	
}
