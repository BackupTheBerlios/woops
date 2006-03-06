package view.admin.importActivities;

import java.util.Collection;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ManageDpeForm extends FWActionForm{
	private SimpleListControl listActivities = new SimpleListControl () ;
	
	
	public SimpleListControl getListActivities() {
		return listActivities;
	}
	
	public void setListActivities(ListDataModel dataModel) {
		listActivities.setDataModel(dataModel);
	}
}
