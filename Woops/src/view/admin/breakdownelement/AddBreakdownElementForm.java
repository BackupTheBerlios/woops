package view.admin.breakdownelement;

import org.apache.struts.action.ActionForm;

import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class AddBreakdownElementForm extends ActionForm{
	private String name ;
	private String details ;
	/* Liste repr?sentant toutes les activit?s dont l'activit? peut d?pendre */
	private SimpleListControl userParticipationOptions = new SimpleListControl();
	
	/* Liste repr?sentant les cl?s des d?pendances s?lectionn?es */
	private String[] usersParticipation = new String[0];

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public ListDataModel getUserParticipationOptions() {
		return (ListDataModel) userParticipationOptions.getDataModel();
	}

	public void setUserParticipationOptions(
			ListDataModel dataModel) {
		this.userParticipationOptions.setDataModel(dataModel);
	}

	public String[] getUsersParticipation() {
		return usersParticipation;
	}

	public void setUsersParticipation(String[] usersParticipation) {
		this.usersParticipation = usersParticipation;
	}
}
