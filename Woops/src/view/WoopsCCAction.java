package view;


import javax.servlet.http.HttpSession;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FWAction;

import business.hibernate.ObjetPersistant;

public abstract class WoopsCCAction extends FWAction {
    
    
    public void saveInSession(ObjetPersistant objet, ActionContext context){
        HttpSession httpSession = context.session();
        if (httpSession!=null){
            httpSession.setAttribute(objet.getClass().getName(),objet);
        }
    }

    public ObjetPersistant getFromSession(Class classe, ActionContext context){
        HttpSession httpSession = context.session();
        ObjetPersistant objet = null;
        if (httpSession!=null){
            objet = (ObjetPersistant) httpSession.getAttribute(classe.getName());
        }
        return objet;
    }
    
    public void removeFromSession(ObjetPersistant objet, ActionContext context){
        HttpSession httpSession = context.session();
        if (httpSession!=null){
            httpSession.removeAttribute(objet.getClass().getName());
        }
    }
    
}
