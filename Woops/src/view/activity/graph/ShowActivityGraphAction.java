package view.activity.graph;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.struts.util.MessageResources;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.BusinessConstantes;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.sequence.ActivitySequence;
import business.activity.sequence.ActivitySequenceManager;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;


/**
 * @author Simon REGGIANI
 * ShowActivitySummaryAction : permet de voir la fiche de d?tail d'une activit?
 */
public class ShowActivityGraphAction extends WoopsCCAction {
	
	/**
	 * Constructeur vide
	 *
	 */
	public ShowActivityGraphAction() {
		super();
	}
	
	
	/**
	 * 
	 * Action a realiser avant l'affichage du formulaire
	 */

	public void doExecute(ActionContext context) {

		ShowActivityGraphForm form = (ShowActivityGraphForm) context.form();
		
		//R?cup?ration de l'identifiant du participant connect?
    	User sessionUser = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
    	
		
		try {
			File tmp = File.createTempFile("dotFile",".dot");
			
			PrintStream p = new PrintStream(tmp);
            
            p.println("digraph G {");
            
            UserManager userManager = UserManager.getInstance();
            
            ArrayList listUsers = (ArrayList)userManager.getUsersByProject(new Integer(1));
            Iterator iterUsers = listUsers.iterator();
            int i = 0;
            while(iterUsers.hasNext()) {
            	User user = (User)iterUsers.next();
            	
            	p.println("\tsubgraph cluster"+i+" {");
            	if ( user.getId().equals(sessionUser.getId()) ) {
            		p.println("\t\tstyle=filled;");
            		p.println("\t\tcolor=lightgrey;");
            	}
            		
            	p.println("\t\tlabel = \""+user.getFirstName()+" "+user.getLastName()+"\";");
            	
				ActivityManager activityManager = ActivityManager.getInstance();
				ArrayList listActivities = (ArrayList) activityManager.getAllActivitiesByUser((Integer)user.getId());
				Iterator iterActivities = listActivities.iterator();
				while(iterActivities.hasNext()){
					Activity act = (Activity)iterActivities.next();
					String label = "label=\""+act.getName()+"\",";
					String color="color=white,";
					if (act.getState().equals(BusinessConstantes.ACTIVITY_STATE_CREATED))
						color="color=green,";
					if (act.getState().equals(BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS))
						color="color=orange,";
					if (act.getState().equals(BusinessConstantes.ACTIVITY_STATE_FINISHED))
						color="color=red,";
					String style="style=filled";
					p.println("\t\t"+act.getId().toString()+"["+label+" "+color+" "+style+"];");
				}
				
				p.println("\t}");
				
				i++;
            }
			
			ActivitySequenceManager activitySequenceManager = ActivitySequenceManager.getInstance();
			ArrayList listAllActivitySequences = (ArrayList) activitySequenceManager.getActivitySequencesByProject(new Integer(1));
			Iterator iterActivitySequences = listAllActivitySequences.iterator();
			while(iterActivitySequences.hasNext()){
				ActivitySequence actSeq = (ActivitySequence)iterActivitySequences.next();
				
				String predecessorId = actSeq.getPredecessor().getId().toString();
				String successorId = actSeq.getSuccessor().getId().toString();
				String linkType = actSeq.getLinkType().getName();
			
				linkType = MessageResources.getMessageResources("ApplicationResources").getMessage(linkType);
				
				p.println("\t"+predecessorId+" -> "+successorId+"[label=\""+linkType+"\"];");
			}
			
			p.println("}");
			p.close();
			
			String dotFilePath = tmp.getAbsolutePath();
			String imageFileType = "jpg";
			String imageFile = "graph"+sessionUser.getId().toString()+".jpg";
			
			String[] args = new String[3];
			args[0]=dotFilePath;
			args[1]="-T "+imageFileType;
			args[2]="-o "+imageFile;			
			
			String command = "dot";
			
			Process proc = Runtime.getRuntime().exec(command,args);
		
			form.setImageFilePath(imageFile);
			context.forwardByName(PresentationConstantes.FORWARD_SUCCESS);
		} catch (PersistanceException pe) {
			context.addGlobalError("errors.persistance.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		} catch (IOException e) {
			context.addGlobalError("errors.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		}
		
	}
}
