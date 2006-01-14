package view.activity;

import view.WoopsListDataModel;

import com.cc.framework.common.DisplayObject;

public class ListActiviySequencesModel extends WoopsListDataModel {

	public ListActiviySequencesModel(DisplayObject[] elements) {
		super(elements);
	}

	public String getUniqueKey(int index) {
		return ((ActivitySequenceItem)data[index]).getId();
	}

}
