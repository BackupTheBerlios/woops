package view.admin.user;

import java.util.Arrays;

import business.format.ColumnComparator;
import business.format.StringColumnComparator;
import business.user.User;

import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;

import view.common.WoopsListDataModel;

public class ListUsersModel extends WoopsListDataModel {

	/**
	 * Constructeur par d�faut
	 */
	public ListUsersModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste d'activit�s
	 * pour le contr�leur c'est � dire les lignes de la liste qui 
	 * sera affich�e au participant
	 */
	public ListUsersModel(DisplayObject[] elements) {
		super(elements);
	}
	
	/**
	 * @see view.common.WoopsListDataModel#getUniqueKey(int)
	 */
	public String getUniqueKey(int index) {
		return ((User)data[index]).getId().toString();
	}
	
	/**
	 * Trie la liste � partir de la colonne selectionn�e  
	 * @param column		propri�t� qui d�finie la colonne
	 * @param direction		ordre de tri : ascendant ou descendant
	 */	
	public void sortByColumn(String column, SortOrder direction) {
		Arrays.sort(data, new StringColumnComparator(column, direction));
	}

}
