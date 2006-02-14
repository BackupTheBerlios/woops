package view.admin.breakdownelement;

import org.apache.struts.action.ActionForm;

import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class AddBreakdownElementForm extends ActionForm{
	private String prefix ;	
	private String kindId ;
	
	private SimpleListControl kindOptions = new SimpleListControl ();
	
	public ListDataModel getKindOptions() {
		return (ListDataModel) this.kindOptions.getDataModel();
	}
	
	public void setKindOptions(ListDataModel dataModel) {
		this.kindOptions.setDataModel(dataModel);
	}

	
	/* Liste representant toutes les utilisateurs affectables au projet */
	private SimpleListControl userParticipationOptions = new SimpleListControl();
	
	/* Liste representant les cles des utilisateurs selectionnes */
	private String[] usersParticipation = new String[0];


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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}
}
