/**
 * 
 */
package view.activity.event;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

/**
 * @author Simon REGGIANI
 *
 */
public class ListEventsForm extends FWActionForm {
	private SimpleListControl listEventsNotOccured;
	private SimpleListControl listEventsOccured;

	/**
	 * @return Returns the dataModel of listEventsNotOccured.
	 */
	public ListDataModel getListEventsNotOccured() {
		return (ListDataModel)listEventsNotOccured.getDataModel();
	}

	/**
	 * @param dataModel the dataModel of listEventsNotOccured to set.
	 */
	public void setListEventsNotOccured(ListDataModel dataModel) {
		this.listEventsNotOccured.setDataModel(dataModel);
	}
	
	/**
	 * @return Returns the dataModel of listEventsOccured.
	 */
	public ListDataModel getListEventsOccured() {
		return (ListDataModel)listEventsOccured.getDataModel();
	}

	/**
	 * @param dataModel the dataModel of listEventsOccured to set.
	 */
	public void setListEventsOccured(ListDataModel dataModel) {
		this.listEventsOccured.setDataModel(dataModel);
	}
	
	
}
