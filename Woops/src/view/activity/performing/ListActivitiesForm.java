package view.activity.performing;

import view.breakdownelement.ListBreakDownElementsModel;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ListActivitiesForm extends FWActionForm {
	private static final long serialVersionUID = -8539904306657854815L; /** Generated Serial ID */
	
	/** * Liste de contr�le des activites que l'on souhaite afficher au participant */ 
	private SimpleListControl listActivities = new SimpleListControl();
	
	/** * Liste de contr�le des projets que l'on souhaite afficher au participant */ 
	private SimpleListControl listBDEs = new SimpleListControl();
	
	/** Retourne la liste des activit�s. 
	 * 	Cet accesseur permet � la JSP d'acc�der � la liste
	 ** @return liste du contr�leur */
	public SimpleListControl getListActivities() {
		return listActivities;
	}
	
	/** * Modifie les donn�es de la liste des activit�s du participant
	 * @param dataModel mod�le de donn�es pour le contr�leur */
	public void setDataModel(ListDataModel dataModel) {
		listActivities.setDataModel(dataModel);
	}


	/** Retourne la liste des entites. 
	 * 	Cet accesseur permet � la JSP d'afficher une liste deroulante d'entites
	 ** @return liste du contr�leur */
	public ListDataModel getListBDEs() {
		return (ListBreakDownElementsModel)listBDEs.getDataModel();
	}

	/** * Modifie les donn�es de la liste des entites du participant
	 * @param dataModel mod�le de donn�es pour le contr�leur */
	public void setBDEDataModel(ListDataModel dataModel) {
		listBDEs.setDataModel(dataModel);
	}
}
