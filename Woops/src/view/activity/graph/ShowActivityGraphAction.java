package view.activity.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;

import view.PresentationConstantes;
import view.activity.performing.ListActivitiesAction;
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
 * ShowActivityGraphAction : permet de voir le graphe des activit�s du projet
 */
public class ShowActivityGraphAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ShowActivityGraphAction.class);    

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
			GraphViz gv = new GraphViz();
			
			gv.addln(gv.start_graph());
            
            UserManager userManager = UserManager.getInstance();
            
            ArrayList listUsers = (ArrayList)userManager.getUsersByProject(new Integer(1));
            Iterator iterUsers = listUsers.iterator();
            int i = 0;
            while(iterUsers.hasNext()) {
            	User user = (User)iterUsers.next();
            	
            	gv.addln("\tsubgraph cluster"+i+" {");
            	if ( user.getId().equals(sessionUser.getId()) ) {
            		gv.addln("\t\tstyle=filled;");
            		gv.addln("\t\tcolor=lightgrey;");
            	}
            		
            	gv.addln("\t\tlabel = \""+user.getFirstName()+" "+user.getLastName()+"\";");
            	
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
					gv.addln("\t\t"+act.getId().toString()+"["+label+" "+color+" "+style+"];");
				}
				
				gv.addln("\t}");
				
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
				
				gv.addln("\t"+predecessorId+" -> "+successorId+"[label=\""+linkType+"\"];");
			}
			
			gv.addln(gv.end_graph());
			
			//logger.debug(gv.getDotSource());
			
			
			String graphRealPath = getServlet().getServletContext().getRealPath("/") + "graph\\";
			//TODO g�rer les s�parateurs selon l'OS ( windows / unix )
			
			GraphViz.setDOT(graphRealPath+"dot.exe");
			GraphViz.setTEMP_DIR(graphRealPath);
			
			String imageFile = "graph"+sessionUser.getId().toString()+".jpg";
			
			File out = new File(graphRealPath+imageFile);
			gv.writeGraphToFile(gv.getGraph(gv.getDotSource()), out);
			
			String graphPath = context.request().getContextPath()+"/graph/";
			
			form.setImageFilePath(graphPath+imageFile);
			context.forwardByName(PresentationConstantes.FORWARD_SUCCESS);
		} catch (PersistanceException pe) {
			context.addGlobalError("errors.persistance.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		}
		
	}
}