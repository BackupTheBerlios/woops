package view.activity.manageActivity;

import java.sql.Date;
import business.activity.state.IActivityState;
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
	
	private	Integer			id; 			// identifiant de l'activité 
	private String 			name; 			// nom de l'activité 
	private	String			details; 		// description de l'activité 
	private Integer 		userId; 		// Id du participant responsable de la réalisation de l'activité 
	private IActivityState	state; 			// Etat actuel de l'activité 
	private Date			beginDate; 		// Date de creation de l'activité 
	private Date			finishDate; 	// Date de fin de l'activité 
	
	

	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IActivityState getState() {
		return state;
	}
	public void setState(IActivityState state) {
		this.state = state;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	
	
	
}
