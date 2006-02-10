package view.admin.summary;

import java.util.HashMap;

import business.activity.Activity;
import business.format.Formatage;
import business.hibernate.exception.PersistanceException;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;

import view.PresentationConstantes;
import view.common.WoopsCCAction;

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
		context.forwardToInput();
	}

}
