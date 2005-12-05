package view;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

import business.BusinessConstantes;
import business.format.Controleur;

/**
 * @author Nicolas Ricard
 * 
 */
public class WoopsFormBean extends ActionForm {
	private static final long serialVersionUID = 1L;
	protected String fonction;
    protected String titre;
    protected String actionSubmit;
    protected String mode;
    protected String origine;
    protected String urlOrigine;
    
    protected String disabled;    

    public String getOrigine() {
        return origine;
    }
    public void setOrigine(String origine) {
        this.origine = origine;
    }
 
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
	public String getActionSubmit() {
		return actionSubmit;
	}
	public void setActionSubmit(String actionSubmit) {
		this.actionSubmit = actionSubmit;
	}	

	public String getFonction() {
        return fonction;
    }
    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    
    public String getUrlOrigine() {
        return urlOrigine;
    }
    public void setUrlOrigine(String urlOrigine) {
        this.urlOrigine = urlOrigine;
    }
    public void validerReglesGestion(ActionErrors errors ,HttpServletRequest request) {}

    public void setUrlOrigine(HttpServletRequest request) {
        this.urlOrigine = request.getRequestURI().substring(request.getContextPath().length());
    }
    public String getDisabled() {
        return disabled;
    }
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
    /**
     * convertit un top O (N) en String Oui (Non) 
     * @param String code 
     * @return String top
     */
    public static String topOuiNon(String top) {
        String ret = "";
        if (!Controleur.isVide(top)){
            ret = BusinessConstantes.OUI.equals(top)?
                  PresentationConstantes.LIBELLE_OUI:PresentationConstantes.LIBELLE_NON;
        }
        return ret;
    }     
    
}