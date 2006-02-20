package view.admin.summary;

import java.util.HashMap;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;

public class ShowUserSummaryAction extends WoopsCCAction {
	
	public ShowUserSummaryAction () {
		super() ;
	}
	
	public void doExecute(ActionContext context) {
		ShowUserSummaryForm form = (ShowUserSummaryForm) context.form();
	
		HashMap usersMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_USERS_MAP);
		
		Integer userId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_USER_ID);

		User user = (User)usersMap.get(userId);
		
		form.setUserID(userId.toString()) ;
		form.setFirstName(user.getFirstName());
		form.setLastName(user.getLastName());
		form.setLogin(user.getLogin());
		form.setRole(user.getRole().getName());
		
		this.loadListBde(context, user);
		context.forwardToInput();
	}
	
	public void loadListBde (ActionContext context, User user){
					
	}

}
