package view.admin.user;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ListUsersForm extends FWActionForm {
	/** * Liste de contr�le que l'on souhaite afficher au participant */ 
	private SimpleListControl listUsers = new SimpleListControl();
	
	/** Retourne la liste des activit�s. 
	 * 	Cet accesseur permet � la JSP d'acc�der � la liste
	 ** @return liste du contr�leur */
	public SimpleListControl getListUsers() {
		return listUsers;
	}
	
	/** * Modifie les donn�es de la liste des activit�s du participant
	 * @param dataModel mod�le de donn�es pour le contr�leur */
	public void setDataModel(ListDataModel dataModel) {
		listUsers.setDataModel(dataModel);
	}
}
