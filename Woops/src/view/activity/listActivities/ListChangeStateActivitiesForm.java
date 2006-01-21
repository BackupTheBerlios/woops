package view.activity.listActivities;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ListChangeStateActivitiesForm extends FWActionForm {
	private static final long serialVersionUID = 1L;
	
	/** * Liste de contr�le que l'on souhaite afficher au participant */ 
	private SimpleListControl listChangeStateActivities = new SimpleListControl();
	
	/** Retourne la liste des activit�s. 
	 * 	Cet accesseur permet � la JSP d'acc�der � la liste
	 ** @return liste du contr�leur */
	public SimpleListControl getListChangeStateActivities() {
		return listChangeStateActivities;
	}
	
	/** * Modifie les donn�es de la liste des activit�s du participant
	 * @param dataModel mod�le de donn�es pour le contr�leur */
	public void setDataModel(ListDataModel dataModel) {
		listChangeStateActivities.setDataModel(dataModel);
	}
}
