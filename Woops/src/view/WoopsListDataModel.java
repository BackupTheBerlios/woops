package view;

import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.model.ListDataModel;

public abstract class WoopsListDataModel implements ListDataModel {
	protected DisplayObject[] data = new DisplayObject[0];
	
	/** Init the list with items */
	public WoopsListDataModel(DisplayObject[] elements) {
		this.data = elements; 
	}
	
	public Object getElementAt(int index) {
		return data[index];
	}
	
	public int size() {
		return data.length;
	}
	
	/** * Unique Key for each Row (Object) */ 
	public abstract String getUniqueKey(int index);
}
