package view.admin;

import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;
import com.cc.framework.adapter.struts.FWActionForm;

public class AdminForm extends FWActionForm {
	private SimpleListControl listUsers = new SimpleListControl();
	
	public SimpleListControl getListUsers() {
		return listUsers;
	}
	
	public void setDataModelUser(ListDataModel dataModel) {
		listUsers.setDataModel(dataModel);
	}
}
