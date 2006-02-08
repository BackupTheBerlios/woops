package view.admin.project;

import com.cc.framework.adapter.struts.ActionContext;

import view.common.WoopsCCAction;

public class AddProjectAction extends WoopsCCAction {

	public void doExecute(ActionContext context) throws Exception {		
		context.forwardToInput();
	}

}
