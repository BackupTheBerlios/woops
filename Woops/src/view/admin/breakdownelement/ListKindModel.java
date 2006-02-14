package view.admin.breakdownelement;

import com.cc.framework.common.DisplayObject;

import view.admin.user.UserItem;
import view.common.WoopsListDataModel;

public class ListKindModel extends WoopsListDataModel {

	
	/**
	 * Constructeur par d�faut
	 */
	public ListKindModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste d'activit�s
	 * pour le contr�leur c'est � dire les lignes de la liste qui 
	 * sera affich�e au participant
	 */
	public ListKindModel(DisplayObject[] elements) {
		super(elements);
	}
	
	/**
	 * @see view.common.WoopsListDataModel#getUniqueKey(int)
	 */
	public String getUniqueKey(int index) {
		return ((KindItem)data[index]).getId();
	}

}
