package view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.actions.DispatchAction;

import business.hibernate.PersistentObject;

/**
 * @author Nicolas Ricard
 * 
 */
public class WoopsAction extends DispatchAction {
    
    
    public void saveInSession(PersistentObject objet, HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        if (httpSession!=null){
            httpSession.setAttribute(objet.getClass().getName(),objet);
        }
    }

    public PersistentObject getFromSession(Class classe, HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        PersistentObject objet = null;
        if (httpSession!=null){
            objet = (PersistentObject) httpSession.getAttribute(classe.getName());
        }
        return objet;
    }
    
    public void removeFromSession(PersistentObject objet, HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        if (httpSession!=null){
            httpSession.removeAttribute(objet.getClass().getName());
        }
    }
    
}
