package view.admin.breakdownelement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.admin.user.ListUsersModel;
import view.admin.user.UserItem;
import view.common.WoopsCCAction;
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
		String mode = (String)context.request().getAttribute(PresentationConstantes.PARAM_MODE);
		
		if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
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
		} else {
			mode = PresentationConstantes.INSERT_MODE;
		}
		this.setuserParticipationOptions(context);
		this.setSelect(context) ;
		form.setMode(mode);
		context.forwardToInput();
	}

	private void setuserParticipationOptions(ActionContext context) throws PersistanceException {
		Collection userParticipationMgr = null;
		Collection userParticipationItems = null;
		SwapUserItem item = null;
		
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
			
			item = new SwapUserItem();
			item.setId(user.getId().toString());
			item.setFirstandLastName(user.getFirstName()+" "+user.getLastName());
			item.setRole(user.getRole().getName());
			if (!user.getRole().getCode().equals(PresentationConstantes.ADMIN_ROLE_CODE))
					userParticipationItems.add(item);
		}
		
		/**
		 * Convertion la liste d'ActivityItem en tableau
		 */
		DisplayObject[] data = new SwapUserItem[userParticipationItems.size()];
		data =(SwapUserItem[]) userParticipationItems.toArray(data);
		
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

    			try {
    				if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
    					Integer id = new Integer (Integer.parseInt((String)madForm.getBkId()));
    					bke.setId(id);
    					BreakdownElementManager.getInstance().update(bke);
    					context.addGlobalMessage("admin.msg.info.breakdownelement.modify");
    				}
    				else {
    					BreakdownElementManager.getInstance().insert(bke);
    					context.addGlobalMessage("admin.msg.info.breakdownelement.validate");    					
    				}
    					
    			}
    			catch (PersistanceException p)
    			{
    				if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
    					context.addGlobalError("admin.msg.error.breakdownelement.modify");
    				}
    				else {
    					context.addGlobalError("admin.msg.error.breakdownelement.insert");
    				}
    				System.out.println(p.getMessage());
    			}
    			catch(DoublonException e)
    			{
    				if (mode!=null&&mode.equals(PresentationConstantes.UPDATE_MODE)){
    					context.addGlobalError("admin.msg.error.breakdownelement.modify");
    				}
    				else {
    					context.addGlobalError("admin.msg.error.breakdownelement.insert");
    			}
    				System.out.println(e.getMessage());
    			}
//    			String [] users  = madForm.getUsersParticipation();
//        		if (users != null) {
//        			UserBDE userBde;
//            		User user;
//            		for (int i=0; i<users.length;i++) {
//            			if (users[i]!= "") {
//            				bke.setId(new Integer(1));	
//            				userBde = new UserBDE();
//                			user = new User();
//                			user.setId(new Integer(Integer.parseInt(users[i])));
//                			userBde.setBde(bke);
//                			userBde.setUser(user);
//                    		try {
//        						BreakdownElementManager.getInstance().insert(userBde);
//        					} catch (PersistanceException e) {
//        						// TODO Auto-generated catch block
//        						e.printStackTrace();
//        					} catch (DoublonException e) {
//        						// TODO Auto-generated catch block
//        						e.printStackTrace();
//        					}
//            			}
//            		}
//        		}
    			
    			
            } else {
            	retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
            }
    	
    	context.forward(retour);
    	}    
}
