package view.admin.importActivities;

import java.util.Collection;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ManageDpeForm extends FWActionForm{
	private SimpleListControl listActivities = new SimpleListControl () ;
	private SimpleListControl listBde = new SimpleListControl () ;
	private String selectionne ; 
	
	/**
	 * @return Returns the seletionne.
	 */
	public String getSelectionne() {
		return selectionne;
	}

	/**
	 * @param seletionne The seletionne to set.
	 */
	public void setSelectionne(String seletionne) {
		this.selectionne = seletionne;
	}

	public SimpleListControl getListBde() {
		return listBde;
	}
	
	public void setListBde(ListDataModel dataModel) {
		listBde.setDataModel(dataModel);
	}
	
	public SimpleListControl getListActivities() {
		return listActivities;
	}
	
	public void setListActivities(ListDataModel dataModel) {
		listActivities.setDataModel(dataModel);
	}
}
