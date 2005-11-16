package view.activities;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ListActivitiesForm extends FWActionForm {
	
	/** * Instance of our list control to display the activities list */ 
	private SimpleListControl listActivities = new SimpleListControl();
	
	/** Returns the activities list
	 ** @return The list control */
	public SimpleListControl getListActivities() {
		return listActivities;
	}
	
	/** * Sets the data for the user list
	 * @param dataModel The datamodel for the control */
	public void setDataModel(ListDataModel dataModel) {
		listActivities.setDataModel(dataModel);
	}


}
