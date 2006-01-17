package view.activity;

import java.util.Arrays;

import view.WoopsListDataModel;
import business.format.ColumnComparator;

import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;

public class ListActivitiesModel extends WoopsListDataModel {

	/**
	 * Constructeur par défaut
	 */
	public ListActivitiesModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste d'activités
	 * pour le contrôleur c'est à dire les lignes de la liste qui 
	 * sera affichée au participant
	 */
	public ListActivitiesModel(DisplayObject[] elements) {
		super(elements);
	}
	
	/**
	 * @see view.WoopsListDataModel#getUniqueKey(int)
	 */
	public String getUniqueKey(int index) {
		return ((ActivityItem)data[index]).getId();
	}
	
	/**
	 * Trie la liste à partir de la colonne selectionnée  
	 * @param column		propriété qui définie la colonne
	 * @param direction		ordre de tri : ascendant ou descendant
	 */	
	public void sortByColumn(String column, SortOrder direction) {
		Arrays.sort(data, new ColumnComparator(column, direction));
	}

}
