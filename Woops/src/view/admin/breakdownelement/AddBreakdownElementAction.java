package view.admin.breakdownelement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.admin.user.ListUsersModel;
import view.breakdownelement.KindItem;
import view.common.WoopsCCAction;
import view.user.UserItem;
import business.breakdownelement.BreakdownElement;
import business.breakdownelement.BreakdownElementKind;
import business.breakdownelement.BreakdownElementManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;

public class AddBreakdownElementAction extends WoopsCCAction {

	public void doExecute(ActionContext context) throws Exception {
		AddBreakdownElementForm form = (AddBreakdownElementForm) context.form();
		String mode = context.request().getParameter(PresentationConstantes.PARAM_MODE);
		
		if (mode.equals(PresentationConstantes.COPY_MODE)){
			String bkId = (String) context.request().getAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID);
			form.setBkId(bkId);	
		}
		else {
			if (mode.equals(PresentationConstantes.UPDATE_MODE)){
				String bkId = (String) context.request().getAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID);
	
				HashMap bkMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_BDE_MAP);
	
				BreakdownElement bke = (BreakdownElement)bkMap.get(new Integer(Integer.parseInt(bkId)));
				
				form.setBkId(bkId);				
				if (bke!=null) {
					form.setKindId(bke.getKind().getId().toString()) ;
					form.setDetails(bke.getDetails());
					form.setName(bke.getName());
					form.setPrefix(bke.getPrefix());
			
				}
				this.setUsersParticipation(context);		
			}
		
			// en mode UPDATE et INSERT on remplit le swap select
			this.setUserParticipationOptions(context);
			
		}
		
		this.setSelect(context) ;
		form.setMode(mode);
		context.forwardToInput();
	}

	private void setUserParticipationOptions(ActionContext context) throws PersistanceException {
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
			//TODO
			item.setRole(user.getRole().getCode());
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
		
		userParticipationMgr = UserManager.getInstance().getUsersByBDE(new Integer(Integer.parseInt(madForm.getBkId())));
	
		
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
		 * Mis ? jour de l'attribut usersParticipation du Form
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
    		String prefix = madForm.getPrefix();
    		String name = madForm.getName();
    		String details = madForm.getDetails();
    		BreakdownElement bke = new BreakdownElement ();
    		bke.setId(null);
    		bke.setPrefix(prefix);
    		bke.setName(name);
    		bke.setDetails(details);
    		bke.setDateCreation(null);
    		bke.setEndDate(null);
    		bke.setKind(new BreakdownElementKind(new Integer(Integer.parseInt(madForm.getKindId()))));      		
	    	
    		if (!mode.equals(PresentationConstantes.COPY_MODE)) {
    			String [] usersKeys  = madForm.getUsersParticipation();
	    		Set users = new HashSet();
	    		if (usersKeys != null) {
	        		User user;
	        		for (int i=0; i<usersKeys.length;i++) {
	        			if (usersKeys[i]!= "") {
	        				bke.setId(new Integer(1));            				
	            			user = new User();
	            			user.setId(new Integer(Integer.parseInt(usersKeys[i])));
	                		users.add(user);        					
	        			}
	        		}
	    		}
	    		bke.setUsers(users);
    		}
    		
			try {
				if (mode.equals(PresentationConstantes.UPDATE_MODE)){
					Integer id = new Integer (Integer.parseInt((String)madForm.getBkId()));
					bke.setId(id);
					BreakdownElementManager.getInstance().update(bke);
					context.addGlobalMessage("admin.msg.info.breakdownelement.modify");
				}
				else if (mode.equals(PresentationConstantes.INSERT_MODE)){
					BreakdownElementManager.getInstance().insert(bke);
					context.addGlobalMessage("admin.msg.info.breakdownelement.insert");    					
				}
				else if (mode.equals(PresentationConstantes.COPY_MODE)){
					BreakdownElementManager.getInstance().copyBreakdownElement(new Integer(madForm.getBkId()),bke);
					context.addGlobalMessage("admin.msg.info.breakdownelement.copy");    					
				}
				
				retour = context.mapping().findForward(PresentationConstantes.FORWARD_ADMIN);
			}
			catch (PersistanceException p)
			{
				if (mode.equals(PresentationConstantes.UPDATE_MODE)){
					context.request().setAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID,madForm.getBkId());
					context.addGlobalError("admin.msg.error.breakdownelement.modify.global");
				}
				else if (mode.equals(PresentationConstantes.INSERT_MODE)) {
					context.addGlobalError("admin.msg.error.breakdownelement.insert.global");
				}
				else if (mode.equals(PresentationConstantes.COPY_MODE)) {
					context.addGlobalError("admin.msg.error.breakdownelement.copy.global");
				}
				
				retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
			}
			catch(DoublonException e)
			{
				if (mode.equals(PresentationConstantes.UPDATE_MODE)){
					context.request().setAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID,madForm.getBkId());
					context.addGlobalError("admin.msg.error.breakdownelement.modify.doublon");
				}
				else if (mode.equals(PresentationConstantes.INSERT_MODE)) {
					context.addGlobalError("admin.msg.error.breakdownelement.insert.doublon");
				}
				else if (mode.equals(PresentationConstantes.COPY_MODE)) {
					context.addGlobalError("admin.msg.error.breakdownelement.copy.doublon");
				}
				
				retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
			}
			
        } else {
        	if (mode.equals(PresentationConstantes.UPDATE_MODE))
        		context.request().setAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID,madForm.getBkId());
        	retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
        }
	
		context.forward(retour);
	}    
}
