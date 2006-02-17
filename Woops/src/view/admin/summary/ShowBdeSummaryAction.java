package view.admin.summary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.admin.AdminAction;
import view.common.WoopsCCAction;
import business.breakdownelement.BreakdownElement;
import business.breakdownelement.BreakdownElementManager;
import business.breakdownelement.UserBDE;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;

public class ShowBdeSummaryAction extends WoopsCCAction {

	private static Logger logger = Logger.getLogger(AdminAction.class); 
	
	public ShowBdeSummaryAction () {
		super() ;
	}
	
	public void doExecute(ActionContext context) {
		ShowBdeSummaryForm form = (ShowBdeSummaryForm) context.form();
	
		HashMap usersMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_BDE_MAP);
		
		Integer bdeId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID);

		BreakdownElement bde = (BreakdownElement)usersMap.get(bdeId);
		
		form.setPrefix(bde.getPrefix());
		form.setName(bde.getName());
		form.setDetails(bde.getDetails());
		form.setStartDate(bde.getStartDate().toString());
		form.setEndDate(bde.getEndDate().toString());
		form.setKind(bde.getKind().getName());
	
		context.forwardToInput();
	}
	
	

}
