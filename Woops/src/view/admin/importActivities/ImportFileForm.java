package view.admin.importActivities;



import org.apache.struts.upload.FormFile;

import com.cc.framework.adapter.struts.FWActionForm;

public class ImportFileForm extends FWActionForm{
	
	FormFile pathFile ;

	/**
	 * @return Returns the pathFile.
	 */
	public FormFile getPathFile() {
		return pathFile;
	}

	/**
	 * @param pathFile The pathFile to set.
	 */
	public void setPathFile(FormFile pathFile) {
		this.pathFile = pathFile;
	}
	
//	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request) {
//
//		ActionErrors errors = new ActionErrors();
//		
//		if (Controleur.isVide(path)){
//			errors.add("path", new ActionMessage("errors.champ.obligatoire","path"));
//		}
//		
//		return errors;
//	}


	
}
