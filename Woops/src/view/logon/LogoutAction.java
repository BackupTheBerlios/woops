package view.logon;

import javax.servlet.http.HttpSession;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.security.SecurityUtil;

import view.PresentationConstantes;
import view.common.WoopsCCAction;

public class LogoutAction extends WoopsCCAction {

	/**
	 * Constructeur
	 */
	public LogoutAction() {
		super();
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#doExecute(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void doExecute(ActionContext context) throws Exception {
		HttpSession httpSession = context.request().getSession(false);
		httpSession.removeAttribute(PresentationConstantes.KEY_USER);
		
		SecurityUtil.unregisterPrincipal(context.session());
		
		context.session().invalidate();
          
		context.forwardByName(PresentationConstantes.FORWARD_DECONNECT); 
	}
}


