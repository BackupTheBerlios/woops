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
import business.chocolat.ChocolatManager;
import business.format.Formatage;
import business.hibernate.exception.PersistanceException;

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
			
	
        	try {
        		//r?cup?ration de la liste des chocolats en BD
				ArrayList listeChocolatBD = (ArrayList) ChocolatManager.getInstance().listeChocolats();
				
	        	Collection liste = new ArrayList();
	        	
				ListerChocolatFormItem item = null;
				
				for(int i=0;i<listeChocolatBD.size();i++) {
					item = new ListerChocolatFormItem();
					
					item.setId(Formatage.integerToString((Integer) ((Chocolat)listeChocolatBD.get(i)).getId()));
					item.setCalorie(Formatage.integerToString(new Integer(((Chocolat)listeChocolatBD.get(i)).getCalorie())));
					item.setNom(((Chocolat)listeChocolatBD.get(i)).getNom());
					item.setFabricant(((Chocolat)listeChocolatBD.get(i)).getFabricant());
					
					liste.add(item);
				}
				
				listeForm.setListeItems(liste);
	
        		
				retour = mapping.findForward(PresentationConstantes.FORWARD_SUCCESS);
        	}
        	catch (PersistanceException pe) {
        		retour = mapping.findForward(PresentationConstantes.FORWARD_ERROR);
        	}
        	
        	
	        
            saveMessages(request, message);

	        return retour; 		
			
		}
}
