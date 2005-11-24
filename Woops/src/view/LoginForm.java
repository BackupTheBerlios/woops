package view;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import business.format.Controleur;

public class LoginForm extends ActionForm {
	private String password;
	private String login;

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		
		if (Controleur.isVide(login)){
			errors.add("erreur",new ActionMessage("errors.champ.obligatoire","login"));
		}
		if (Controleur.isVide(password)){
			errors.add("erreur",new ActionMessage("errors.champ.obligatoire","password"));
		}
		
		return errors;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
