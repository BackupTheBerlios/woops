package view.admin.summary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.admin.AdminAction;
import view.common.WoopsCCAction;
import business.breakdownelement.BreakdownElementManager;
import business.breakdownelement.UserBDE;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;

public class ShowUserSummaryAction extends WoopsCCAction {

	private static Logger logger = Logger.getLogger(AdminAction.class); 
	
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
