package view;


import javax.servlet.http.HttpSession;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FWAction;

import business.hibernate.PersistentObject;

public abstract class WoopsCCAction extends FWAction {
    
    
    public void saveInSession(PersistentObject objet, ActionContext context){
        HttpSession httpSession = context.session();
        if (httpSession!=null){
            httpSession.setAttribute(objet.getClass().getName(),objet);
        }
    }

    public PersistentObject getFromSession(Class classe, ActionContext context){
        HttpSession httpSession = context.session();
        PersistentObject objet = null;
        if (httpSession!=null){
            objet = (PersistentObject) httpSession.getAttribute(classe.getName());
        }
        return objet;
    }
    
    public void removeFromSession(PersistentObject objet, ActionContext context){
        HttpSession httpSession = context.session();
        if (httpSession!=null){
            httpSession.removeAttribute(objet.getClass().getName());
        }
    }
    
}
