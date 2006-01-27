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
	 * Constructeur par défaut
	 */
	public ListUsersModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste d'activités
	 * pour le contrôleur c'est à dire les lignes de la liste qui 
	 * sera affichée au participant
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
	 * Trie la liste à partir de la colonne selectionnée  
	 * @param column		propriété qui définie la colonne
	 * @param direction		ordre de tri : ascendant ou descendant
	 */	
	public void sortByColumn(String column, SortOrder direction) {
		Arrays.sort(data, new StringColumnComparator(column, direction));
	}

}
