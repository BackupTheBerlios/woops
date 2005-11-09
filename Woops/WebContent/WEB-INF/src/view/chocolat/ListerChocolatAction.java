package view.chocolat;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import view.PresentationConstantes;
import view.WoopsAction;
import business.chocolat.Chocolat;
import business.format.Formatage;

public class ListerChocolatAction  extends WoopsAction  {

	private static Logger logger = Logger.getLogger(ListerChocolatAction.class);    
    
	public ActionForward lister(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {

			logger.debug("listerChocolat()");
		
			ActionForward retour = null;
        	ActionMessages message = new ActionMessages();
			
        	if (form==null) {
				form = new ListerChocolatForm();
				request.setAttribute(mapping.getAttribute(), form);
        	}

        	ListerChocolatForm listeForm = (ListerChocolatForm) form;
			

			
			// récupération de la liste des chocolats en BD
			ArrayList listeChocolatBD = new ArrayList();
			
			Chocolat chocolat1 = new Chocolat(1,"nom1",1,"Liegeois");
			listeChocolatBD.add(chocolat1);
			Chocolat chocolat2 = new Chocolat(2,"nom2",2,"Liegeois");
			listeChocolatBD.add(chocolat2);
			Chocolat chocolat3 = new Chocolat(3,"nom3",3,"Liegeois");
			listeChocolatBD.add(chocolat3);
			Chocolat chocolat4 = new Chocolat(4,"nom4",4,"Liegeois");
			listeChocolatBD.add(chocolat4);
			Chocolat chocolat5 = new Chocolat(5,"nom5",5,"Liegeois");
			listeChocolatBD.add(chocolat5);
			Chocolat chocolat6 = new Chocolat(6,"nom6",6,"Liegeois");
			listeChocolatBD.add(chocolat6);
			Chocolat chocolat7 = new Chocolat(7,"nom7",7,"Liegeois");
			listeChocolatBD.add(chocolat7);
			
			////////////////////////////////////////////////
			
        	Collection liste = new ArrayList();
        	
			ListerChocolatFormItem item = null;
			
			for(int i=0;i<listeChocolatBD.size();i++) {
				item = new ListerChocolatFormItem();
				
				item.setId(Formatage.integerToString(((Chocolat)listeChocolatBD.get(i)).getId()));
				item.setCalorie(Formatage.integerToString(((Chocolat)listeChocolatBD.get(i)).getCalorie()));
				item.setNom(((Chocolat)listeChocolatBD.get(i)).getNom());
				item.setFabricant(((Chocolat)listeChocolatBD.get(i)).getFabricant());
				
				liste.add(item);
			}
			
			listeForm.setListeItems(liste);

			retour = mapping.findForward(PresentationConstantes.FORWARD_SUCCES);
	        
            saveMessages(request, message);

	        return retour; 		
			
		}
}
