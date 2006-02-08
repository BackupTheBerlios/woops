package view.admin.project;

import org.apache.struts.action.ActionForm;

public class AddProjectForm extends ActionForm{
	private String name ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
