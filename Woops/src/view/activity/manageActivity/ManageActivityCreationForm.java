package view.activity.manageActivity;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import business.activity.state.IActivityState;
import business.format.Controleur;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

/**
 * @author Benjamin TALOU
 */

public class ManageActivityCreationForm extends FWActionForm {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private	String	activityId; 	// identifiant de l'activité 
	private String 	name; 			// nom de l'activité 
	private	String	details; 		// description de l'activité 
	private String	actionSubmit;	// mode du formulaire ( insert ou update )	
	
	public ManageActivityCreationForm() {
		super();
	}

	
	/**
	 * Getters et setters generes
	 *
	 */
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActionSubmit() {
		return actionSubmit;
	}
	public void setActionSubmit(String actionSubmit) {
		this.actionSubmit = actionSubmit;
	}


	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			
			if (Controleur.isVide(name)){
				errors.add("name", new ActionMessage("errors.champ.obligatoire","name"));
			}
			
			return errors;
		}
	
	
	
	
}
