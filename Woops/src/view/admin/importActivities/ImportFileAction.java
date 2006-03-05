package view.admin.importActivities;

import java.util.List;

import view.common.WoopsCCAction;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;

public class ImportFileAction extends WoopsCCAction{

	public void doExecute(ActionContext context) throws Exception {
		context.forwardToInput();
	}
	
	public void import_onClick(FormActionContext context) {
		if (!context.hasErrors()){
		//	ImportFileForm form = (ImportFileForm)context.form();
			//try {
//				FormFile fichierDpe = form.getPathFile();
//				System.out.println("dans le form " + form.getPathFile());
//				System.out.println("fichier " + fichierDpe);
				//List test = ProcessControler.load(fichierDpe);
//			} catch (FileParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
	}

}
