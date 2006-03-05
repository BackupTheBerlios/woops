package view.admin.importActivities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import view.PresentationConstantes;
import view.admin.importActivities.IAItem.BreakDownElementNameItem;
import view.admin.importActivities.IAItem.IAItem;
import view.admin.importActivities.IAItem.ListBdeNModel;
import view.admin.importActivities.IAItem.ListIAModel;
import view.common.WoopsCCAction;
import business.activity.Activity;
import business.breakdownelement.BreakdownElement;
import business.breakdownelement.BreakdownElementManager;
import business.hibernate.exception.PersistanceException;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.model.ListDataModel;

public class ManageDpeAction extends WoopsCCAction{

	public void doExecute(ActionContext context) throws Exception {
		// recuperation de la liste d'activites
		ManageDpeForm form = (ManageDpeForm)context.form() ;
		
		ListDataModel bdename = this.loadBde(context);
		form.setListBde(bdename) ;
		
		ListDataModel activityname = this.loadAct (context) ;
		form.setListActivities(activityname) ;
		context.forwardToInput();
		
	}
	
	private ListDataModel loadAct(ActionContext context) {
		// TODO Auto-generated method stub
		List listeActivites = (List)context.session().getAttribute(PresentationConstantes.FILE_IN_SESSION) ;
		ArrayList listeFinale = new ArrayList () ;
		IAItem item ;
		for (int i = 0 ; i < listeActivites.size() ; i++)
		{
			item = new IAItem() ;
			item.setId(Integer.toString(i));
			item.setName(((Activity)listeActivites.get(i)).getName());
			listeFinale.add(item);
		}
		DisplayObject[] result = new IAItem[listeFinale.size()];
		listeFinale.toArray(result);
		
		// Création de la liste initialisée avec les valeurs à afficher
		ListIAModel model = new ListIAModel(result);
		return model;
	}

	private ListDataModel loadBde (ActionContext context){
//		 recuperation des bde 
		List listeBde;
		try {
			listeBde = BreakdownElementManager.getInstance().getList("BreakdownElement");
			ArrayList listBdeName = new ArrayList () ;
			int indice = 0 ;
			BreakDownElementNameItem bdeitem ;
			for (Iterator i = listeBde.iterator() ; i.hasNext() ;) {
				BreakdownElement bde = (BreakdownElement)i.next() ;
				bdeitem = new BreakDownElementNameItem () ;
				bdeitem.setCurrentName(bde.getName());
				bdeitem.setId(Integer.toString(indice));
				listBdeName.add(bdeitem);
			}
			DisplayObject[] result = new BreakDownElementNameItem[listBdeName.size()];
			listBdeName.toArray(result);
			
			// Création de la liste initialisée avec les valeurs à afficher
			ListBdeNModel model = new ListBdeNModel(result);
			return (model);	
		} catch (PersistanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
}
