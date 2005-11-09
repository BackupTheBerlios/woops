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
			
			Chocolat chocolat1 = new Chocolat(1,"Accord parfait",1,"Liegeois");
			listeChocolatBD.add(chocolat1);
			Chocolat chocolat2 = new Chocolat(2,"Délices suprêmes",2,"Lindt");
			listeChocolatBD.add(chocolat2);
			Chocolat chocolat3 = new Chocolat(3,"Magie des parfums",3,"Nestlé");
			listeChocolatBD.add(chocolat3);
			Chocolat chocolat4 = new Chocolat(4,"Secrète alchimie",4,"Milka");
			listeChocolatBD.add(chocolat4);
			Chocolat chocolat5 = new Chocolat(5,"A l'unisson",5,"Côte d'or");
			listeChocolatBD.add(chocolat5);
			Chocolat chocolat6 = new Chocolat(6,"Saveurs d'autrefois",6,"Nesquick");
			listeChocolatBD.add(chocolat6);
			Chocolat chocolat7 = new Chocolat(7,"Sarabande",7,"Cacao");
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
