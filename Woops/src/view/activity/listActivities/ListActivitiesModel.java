package view.activity.listActivities;

import com.cc.framework.common.DisplayObject;

import view.WoopsListDataModel;

public class ListActivitiesModel extends WoopsListDataModel {

	public ListActivitiesModel(DisplayObject[] elements) {
		super(elements);
	}

	public String getUniqueKey(int index) {
		return ((ActivityItem)data[index]).getName();
	}

}
