package view.common;


import javax.servlet.http.HttpSession;

import view.PresentationConstantes;

import business.hibernate.PersistentObject;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FWAction;

public abstract class WoopsCCAction extends FWAction {
    
    public boolean doPreExecute(ActionContext context) {
    	boolean resultat = true;
    	String formActif = context.form().toString();
    	
    	if (formActif.indexOf("LoginForm")==-1) {
	    	if (context.session().getAttribute(PresentationConstantes.KEY_USER)==null){
	    		resultat = false;
	    		context.forwardByName(PresentationConstantes.FORWARD_NOSESSION); 
	    	}
    	}
    	
    	return resultat;
    }
	
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