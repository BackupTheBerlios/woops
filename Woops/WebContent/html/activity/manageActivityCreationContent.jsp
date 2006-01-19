<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"     prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>


<html:form action="manageActivityCreation.do">
	
	<logic:equal name="manageActivityCreationForm" property="actionSubmit" value="insert_mode">
		<bean:define id="message" value="actionSubmit" scope="page"/>
		${message}
	</logic:equal>
	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	
    <forms:form 
    type="edit" 
    name="manageActivityCreationForm" 
    caption="form.title.manageActivityCreation" 
    formid="frmActivityCreation" 
    width="550">
    
    
		 <forms:text        
		 label="form.text"         
		 property="name"    
		 size="20"  
		 maxlength="20" 
		 required="true"  
		 />
 
         <forms:textarea
            label="form.textarea"
            property="details"
            cols="64"
            rows="3"          
          />
            
            
        <forms:buttonsection>
			<forms:button
				name="btnSave"
				title="@{bean.actionSubmit}"/>
		</forms:buttonsection>
        
        
    </forms:form>
    
    <html:hidden property="activityId"/>
    <html:hidden property="actionSubmit"/>
    
</html:form>