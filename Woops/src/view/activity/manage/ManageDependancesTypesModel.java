package view.activity.manage;

import view.activity.ActivitySequenceItem;
import view.common.WoopsListDataModel;

import com.cc.framework.common.DisplayObject;

public class ManageDependancesTypesModel extends WoopsListDataModel {

	public ManageDependancesTypesModel(DisplayObject[] elements) {
		super(elements);
	}

	public String getUniqueKey(int index) {
		return ((ActivitySequenceItem)data[index]).getId();
	}

}
