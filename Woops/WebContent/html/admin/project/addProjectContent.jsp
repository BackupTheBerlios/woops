<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"    prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<html:form action="addProject.do" >
	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
    <forms:form 
    	formid="addProjectForm"
    	type="edit"
    	caption="admin.addProject.title"
    	width="600">
    	
    	<forms:text 
        	label="admin.addProject.name"
        	property="name"
        	required="true"/>
        	
        <forms:textarea
            label="admin.addProject.details"
            property="details"
            cols="64"
            rows="3"          
          />
          
       <forms:swapselect
            property="usersParticipation"
            label="admin.addProject.manageUserParticipation"
            orientation="horizontal"
            labelLeft="admin.addProject.manageUserParticipation.toAdd"
            labelRight="admin.addProject.manageUserParticipation.added"
            valign="top"
            size="10"
            style="width: 250;"
            align="center"
            filter="false"
            required="false"
            disabled="false">
            
            <base:options 
            	property="userParticipationOptions"  
            	keyProperty="id" 
            	labelProperty="name"/>
        
		</forms:swapselect>

       	<forms:buttonsection default="btnAdd">  
        	<forms:button
            	name="btnAdd"
            	default="true"
                title="admin.button.validate"
                text="admin.button.validate"
                width="100"/>     
        </forms:buttonsection>
    
	</forms:form>
	    
</html:form>
