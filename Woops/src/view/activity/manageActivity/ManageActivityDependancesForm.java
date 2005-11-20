package view.activity.manageActivity;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ManageActivityDependancesForm extends FWActionForm {
	
	/* Liste représentant toutes les activités dont l'activité peut dépendre */
	private SimpleListControl possibleDependancesOptions = new SimpleListControl();
	
	/* Liste représentant les clés des dépendances sélectionnées */
	private String[] realDependancesKey = new String[0];
	

	public ListDataModel getPossibleDependancesOptions() {
		return (ListDataModel) possibleDependancesOptions.getDataModel();
	}
	
	public void setPossibleDependancesOptions(ListDataModel dataModel) {
		possibleDependancesOptions.setDataModel(dataModel);
	}

	public String[] getRealDependancesKey() {
		return realDependancesKey;
	}

	public void setRealDependancesKey(String[] realDependancesKey) {
		this.realDependancesKey = realDependancesKey;
	}
	
}
