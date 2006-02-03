package view.admin.user;

import java.util.Arrays;

import business.format.ColumnComparator;
import business.format.StringColumnComparator;
import business.user.User;
import business.user.UserRole;

import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.model.ListDataModel;

import view.common.WoopsListDataModel;

public class ListRoleModel extends WoopsListDataModel {

	/**
	 * Constructeur par d�faut
	 */
	public ListRoleModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste d'activit�s
	 * pour le contr�leur c'est � dire les lignes de la liste qui 
	 * sera affich�e au participant
	 */
	public ListRoleModel(DisplayObject[] elements) {
		super(elements);
	}
	
	/**
	 * @see view.common.WoopsListDataModel#getUniqueKey(int)
	 */
	public String getUniqueKey(int index) {
		return ((RoleItem)data[index]).getCode();
	}

}
