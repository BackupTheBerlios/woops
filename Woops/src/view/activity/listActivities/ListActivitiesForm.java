package view.activity.listActivities;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ListActivitiesForm extends FWActionForm {
	private static final long serialVersionUID = 1L;
	
	/** * Liste de contrôle que l'on souhaite afficher au participant */ 
	private SimpleListControl listActivities = new SimpleListControl();
	
	/** Retourne la liste des activités. 
	 * 	Cet accesseur permet à la JSP d'accéder à la liste
	 ** @return liste du contrôleur */
	public SimpleListControl getListActivities() {
		return listActivities;
	}
	
	/** * Modifie les données de la liste des activités du participant
	 * @param dataModel modèle de données pour le contrôleur */
	public void setDataModel(ListDataModel dataModel) {
		listActivities.setDataModel(dataModel);
	}
}
