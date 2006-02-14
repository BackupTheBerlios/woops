package view.admin.breakdownelement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

import business.activity.Activity;
import business.activity.ActivityManager;
import business.breakdownelement.BreakdownElement;
import business.breakdownelement.BreakdownElementKind;
import business.breakdownelement.BreakdownElementManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;
import business.user.UserRole;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.model.ListDataModel;

import view.PresentationConstantes;
import view.activity.ActivityItem;
import view.activity.manage.ManageActivityDependancesForm;
import view.activity.performing.ListActivitiesModel;
import view.admin.user.AddUserForm;
import view.admin.user.ListRoleModel;
import view.admin.user.ListUsersModel;
import view.admin.user.RoleItem;
import view.admin.user.UserItem;
import view.common.WoopsCCAction;

public class AddBreakdownElementAction extends WoopsCCAction {

	public void doExecute(ActionContext context) throws Exception {
		this.setuserParticipationOptions(context);
		this.setSelect(context) ;				
		context.forwardToInput();
	}

	private void setuserParticipationOptions(ActionContext context) throws PersistanceException {
		Collection userParticipationMgr = null;
		Collection userParticipationItems = null;
		UserItem item = null;
		
		AddBreakdownElementForm madForm = (AddBreakdownElementForm) context.form();
		
		/* Recup?ration de l'id de l'activit? dont on veut g?rer les d?pendances dans la requete*/
		//Integer projectId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_USER_ID);
		
		/* Sauvegarde dans le form */
		//madForm.setProjectId(projectId.toString());
		
		/* R?cup?ration de la liste des utilisateurs */
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
			item.setRole(user.getRole().getName());
			if (!user.getRole().getCode().equals(PresentationConstantes.ADMIN_ROLE_CODE))
					userParticipationItems.add(item);
		}
		
		/**
		 * Convertion la liste d'ActivityItem en tableau
		 */
		DisplayObject[] data = new UserItem[userParticipationItems.size()];
		data =(UserItem[]) userParticipationItems.toArray(data);
		
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
	
        private void setUsersParticipation(ActionContext context) throws PersistanceException {
		Collection userParticipationMgr = null;

		AddBreakdownElementForm madForm = (AddBreakdownElementForm) context.form();
		
		userParticipationMgr = UserManager.getInstance().getList(PresentationConstantes.TABLE_USER);
		
		
		/**
		 * Convertit la liste des cl?s de type Integer
		 * de la liste activityDependancesKeys en tableau de cl?s de type String
		 */  
    	String[] listStringKeys = new String[userParticipationMgr.size()];
		Iterator iter = userParticipationMgr.iterator();
		for (int i=0; iter.hasNext(); i++) {
			listStringKeys[i]=((User)iter.next()).getId().toString();
		}
		
		/**
		 * Sauvegarde du tableau des anciennes cl?s des activit? depandantes dans la session
		 */
		//context.session().setAttribute(PresentationConstantes.KEY_OLD_DEPENDANCES_KEYS,listStringKeys);
		
		/**
		 * Mis ? jour de l'attribut realDependancesKeys du Form
		 */
		madForm.setUsersParticipation(listStringKeys);			
	}
        
        
    private void setSelect (ActionContext context){
		
    	AddBreakdownElementForm madForm = (AddBreakdownElementForm) context.form();
    	KindItem item = null;
		
		try {
			List kinds = BreakdownElementManager.getInstance().getList(PresentationConstantes.TABLE_BREAKDOWN_KIND);
			Collection listKindItem = new ArrayList () ;
			Iterator i = kinds.iterator() ;
			BreakdownElementKind ur ;
			while (i.hasNext())
			{
				ur = (BreakdownElementKind)i.next();
				item = new KindItem () ;
				item.setId(ur.getId().toString());
				item.setName(ur.getName());
				listKindItem.add(item);
			}
			DisplayObject[] data = new DisplayObject[listKindItem.size()]; 
			listKindItem.toArray(data ) ; 
				
			
			ListKindModel model = new ListKindModel(data);
			madForm.setKindOptions(model);
		} catch (PersistanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
        
    	public void add_onClick(FormActionContext context) {
    		ActionForward retour = null;	
    		
    		AddBreakdownElementForm madForm = (AddBreakdownElementForm) context.form();

    		// controle de la validation du formulaire
    		context.addErrors(madForm.validate(context.mapping(),context.request()));

    		String mode = context.request().getParameter(PresentationConstantes.PARAM_MODE);
    	    
    		if (!context.hasErrors()) {
    			retour = context.mapping().findForward(PresentationConstantes.FORWARD_ADMIN);
        		String prefix = madForm.getPrefix();
        		BreakdownElement bke = new BreakdownElement ();
        		bke.setId(null);
        		bke.setPrefix(prefix);
        		bke.setDateCreation(null);
        		bke.setEndDate(null);
        		BreakdownElementKind bkk = new BreakdownElementKind();
        		bkk.setId(new Integer(1));
        		bke.setKind(bkk);
        		
    			try {
    				
    					BreakdownElementManager.getInstance().insert(bke);
    					context.addGlobalMessage("admin.msg.info.user.validate");

    			}
    			catch (PersistanceException p)
    			{
    				if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
    					context.addGlobalError("admin.msg.error.user.modify");
    				}
    				else {
    					context.addGlobalError("admin.msg.error.user.insert");
    				}
    				System.out.println(p.getMessage());
    			}
    			catch(DoublonException e)
    			{
    				if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
    					context.addGlobalError("admin.msg.error.user.modify");
    				}
    				else {
    					context.addGlobalError("admin.msg.error.user.insert");
    			}
    				System.out.println(e.getMessage());
    			}
    	    	
    			
    			
            } else {
            	retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
            }
    	
    	context.forward(retour);
    	}    
}
