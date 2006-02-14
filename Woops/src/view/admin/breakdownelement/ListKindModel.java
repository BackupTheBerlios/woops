package view.admin.breakdownelement;

import com.cc.framework.common.DisplayObject;

import view.admin.user.UserItem;
import view.common.WoopsListDataModel;

public class ListKindModel extends WoopsListDataModel {

	
	/**
	 * Constructeur par défaut
	 */
	public ListKindModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste d'activités
	 * pour le contrôleur c'est à dire les lignes de la liste qui 
	 * sera affichée au participant
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
