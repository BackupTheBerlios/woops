package view.admin.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;
import business.user.UserRole;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.control.ControlActionContext;
import com.cc.framework.ui.model.ListDataModel;
				

public class AddUserAction extends WoopsCCAction {

	public void doExecute(ActionContext context) throws Exception {
		AddUserForm form = (AddUserForm) context.form();
		
		String mode = (String)context.request().getAttribute(PresentationConstantes.PARAM_MODE);
		
		this.setSelect(context) ;
		
		if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
			
			String userId = (String) context.request().getAttribute(PresentationConstantes.PARAM_USER_ID);

			HashMap usersMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_USERS_MAP);

			User user = (User)usersMap.get(new Integer(Integer.parseInt(userId)));
			
			form.setUserId(userId);
			
			if (user!=null) {
				
				//form.s(userId.toString());
				form.setFirstName(user.getFirstName());
				form.setLastName(user.getLastName());
				form.setLogin(user.getLogin());
				form.setPassword(user.getPassword());
				form.setPassword2(user.getPassword());
				form.setRoleCode(user.getRole().getCode());
				
				
			}		
		}
		else {
			mode = PresentationConstantes.INSERT_MODE;
			
			
//			form.setCaption("form.title.manageActivityCreation.insert");
//			form.setDisableNext("false");
		}
		
		context.session().setAttribute(PresentationConstantes.KEY_ROLE_OPTIONS,form.getRoleOptions());
		form.setMode(mode);
		
		context.forwardToInput();
	}

	
	
	public void add_onClick(FormActionContext context) {
		ActionForward retour = null;	
		
		AddUserForm addUserForm = (AddUserForm) context.form();

		// controle de la validation du formulaire
		context.addErrors(addUserForm.validate(context.mapping(),context.request()));

		String mode = context.request().getParameter(PresentationConstantes.PARAM_MODE);
	    
		if (!context.hasErrors()) {
			retour = context.mapping().findForward(PresentationConstantes.FORWARD_ADMIN);
			User user = new User();
			user.setFirstName(addUserForm.getFirstName());
			user.setLastName(addUserForm.getLastName());
			user.setLogin(addUserForm.getLogin());
			user.setPassword(addUserForm.getPassword());
			user.setRole(new UserRole(addUserForm.getRoleCode(),this.getRoleName((ListDataModel)context.session().getAttribute(PresentationConstantes.KEY_ROLE_OPTIONS),addUserForm.getRoleCode())));
			try {
				if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
					Integer id = new Integer (Integer.parseInt((String)addUserForm.getUserId()));
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
	
	void setSelect (ActionContext context){
		
		AddUserForm madForm = (AddUserForm) context.form();
		RoleItem item = null;
		
		try {
			List userRoles = UserManager.getInstance().getList("UserRole");
			Collection listUserItem = new ArrayList () ;
			Iterator i = userRoles.iterator() ;
			UserRole ur ;
			while (i.hasNext())
			{
				ur = (UserRole)i.next();
				item = new RoleItem () ;
				item.setCode(ur.getCode());
				item.setName(ur.getName());
				listUserItem.add(item);
			}
			DisplayObject[] data = new DisplayObject[listUserItem.size()]; 
			listUserItem.toArray(data ) ; 
				
			
			ListRoleModel model = new ListRoleModel(data);
			madForm.setRoleOptions(model);
		} catch (PersistanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	private String getRoleName(ListDataModel l, String code){
		String retour = null ;
		for (int i = 0 ; i < l.size() ; i++){
			if (((RoleItem)l.getElementAt(i)).getCode().equals(code)){
				retour = ((RoleItem)l.getElementAt(i)).getName() ;
			}
		}
		return retour;
		
	}
}
