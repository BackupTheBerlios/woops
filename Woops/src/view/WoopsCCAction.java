package view;


import javax.servlet.http.HttpSession;

import business.hibernate.PersistentObject;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FWAction;

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
