package view.activity;

import view.WoopsListDataModel;

import com.cc.framework.common.DisplayObject;

public class ListActivitiesModel extends WoopsListDataModel {

	public ListActivitiesModel(DisplayObject[] elements) {
		super(elements);
	}

	public String getUniqueKey(int index) {
		return ((ActivityItem)data[index]).getId();
	}

}
