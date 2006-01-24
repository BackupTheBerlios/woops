package view.activity.manage;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import business.format.Controleur;

import com.cc.framework.adapter.struts.FWActionForm;

/**
 * @author Benjamin TALOU
 */

public class ManageActivityCreationForm extends FWActionForm {
	private static final long serialVersionUID = 1785279013061841305L; /** Generated Serial ID */
	
	private	String	activityId; 	/** identifiant de l'activit? */
	private String 	name; 			/** nom de l'activit? */
	private	String	details; 		/** description de l'activit? */
	private String	mode;			/** mode du formulaire ( insert ou update )	*/
	private String	caption;		/** libell? de l'entete du formulaire */
	private String	disableNext;	/** bool?en indiquant si le bouton next est d?sactiv? */
	
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
	
	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	public String getDisableNext() {
		return disableNext;
	}


	public void setDisableNext(String disableNext) {
		this.disableNext = disableNext;
	}


	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			
			if (Controleur.isVide(name)){
				errors.add("name", new ActionMessage("errors.champ.obligatoire","name"));
			}
			
			return errors;
		}
	
	
	
	
}
