package view.admin.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.struts.util.MessageResources;

import business.activity.Activity;
import business.activity.ActivityManager;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;

import view.PresentationConstantes;
import view.activity.ActivityItem;
import view.activity.manage.ManageActivityDependancesForm;
import view.activity.performing.ListActivitiesModel;
import view.admin.user.ListUsersModel;
import view.admin.user.UserItem;
import view.common.WoopsCCAction;

public class AddProjectAction extends WoopsCCAction {

	public void doExecute(ActionContext context) throws Exception {
		setuserParticipationOptions(context);
		context.forwardToInput();
	}

	private void setuserParticipationOptions(ActionContext context) throws PersistanceException {
		Collection userParticipationMgr = null;
		Collection userParticipationItems = null;
		UserItem item = null;
		
		AddProjectForm madForm = (AddProjectForm) context.form();
		
		/* Recup?ration de l'id de l'activit? dont on veut g?rer les d?pendances dans la requete*/
		//Integer projectId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_USER_ID);
		
		/* Sauvegarde dans le form */
		//madForm.setProjectId(projectId.toString());
		
		/* Récupération de la liste des utilisateurs */
		userParticipationMgr = UserManager.getInstance().getList(PresentationConstantes.TABLE_USER);
		
		/**
		 * Conversion de la liste d'Activity retourn?e par getPossibleActivityDependances
		 * en liste d'ActivityItem
		 */
    	Iterator iter = userParticipationMgr.iterator();
    	userParticipationItems = new ArrayList();

    	while (iter.hasNext()) {
    		User user = (User)iter.next();
			
			item = new UserItem();
			item.setId(user.getId().toString());
			item.setFirstName(user.getFirstName());
			item.setLastName(user.getLastName());
			
			userParticipationItems.add(item);
		}
		
		/**
		 * Convertion la liste d'ActivityItem en tableau
		 */
		DisplayObject[] data = new UserItem[userParticipationItems.size()];
		userParticipationItems.toArray(data);
		
		/**
		 * Mis ? jour de l'attribut possibleDependancesOptions du Form
		 * en passant par un ListActivitiesModel
		 */
		ListUsersModel model = new ListUsersModel(data);
		
		
		/**
		 * Sauvegarde du model dans la session
		 */
		//context.session().setAttribute(PresentationConstantes.KEY_POSSIBLE_DEPENDANCES_OPTIONS,model);
		
		madForm.setUserParticipationOptions(model);
	}
	
}
