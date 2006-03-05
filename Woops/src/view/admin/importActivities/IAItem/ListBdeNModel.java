package view.admin.importActivities.IAItem;

import com.cc.framework.common.DisplayObject;

import view.common.WoopsListDataModel;

public class ListBdeNModel extends WoopsListDataModel{

	public ListBdeNModel(DisplayObject[] elements) {
		super(elements);
	}
	
	@Override
	public String getUniqueKey(int index) {
		return ( (BreakDownElementNameItem)data[index] ).getId();
	}

}
