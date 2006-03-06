package view.admin.importActivities;

import java.io.File;
import java.util.List;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import view.util.FileParseException;
import view.util.ProcessControler;

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
			File f = new File ("../workspace/Woops/test/util/processus.dpe") ;
			try {
				List l = ProcessControler.load(f) ;
				if (l.isEmpty())
				{
					context.addGlobalError("admin.manageDpe.emptyList") ;
					context.forwardByName(PresentationConstantes.FORWARD_ERROR);
				}
				else
				{
					context.session().setAttribute(PresentationConstantes.FILE_IN_SESSION,l);
					context.forwardByName(PresentationConstantes.FORWARD_SUCCESS);
				}
			} catch (FileParseException e) {
				// TODO Auto-generated catch block
				context.addGlobalError("admin.manageDpe.fileError") ;
				context.forwardByName(PresentationConstantes.FORWARD_ERROR);
			}			
		}
	}

}
