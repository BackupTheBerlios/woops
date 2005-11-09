package view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.actions.DispatchAction;

import business.hibernate.ObjetPersistant;

/**
 * @author Nicolas Ricard
 * 
 */
public class WoopsAction extends DispatchAction {
    
    
    public void saveInSession(ObjetPersistant objet, HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        if (httpSession!=null){
            httpSession.setAttribute(objet.getClass().getName(),objet);
        }
    }

    public ObjetPersistant getFromSession(Class classe, HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        ObjetPersistant objet = null;
        if (httpSession!=null){
            objet = (ObjetPersistant) httpSession.getAttribute(classe.getName());
        }
        return objet;
    }
    
    public void removeFromSession(ObjetPersistant objet, HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        if (httpSession!=null){
            httpSession.removeAttribute(objet.getClass().getName());
        }
    }
    
}
