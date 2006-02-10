package view.admin;

import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;
import com.cc.framework.adapter.struts.FWActionForm;

public class AdminForm extends FWActionForm {
	
	private SimpleListControl listUsers = new SimpleListControl();
	private SimpleListControl listBreakDownElements = new SimpleListControl();
	
	public SimpleListControl getListUsers() {
		return listUsers;
	}
	
	public void setDataModelUser(ListDataModel dataModel) {
		listUsers.setDataModel(dataModel);
	}
	
	public SimpleListControl getListBreakDownElements() {
		return listBreakDownElements;
	}
	
	public void setDataModelListBreakDownElements(ListDataModel dataModel) {
		listBreakDownElements.setDataModel(dataModel);
	}
}
