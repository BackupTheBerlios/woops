<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"     prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>



<html:form action="manageActivityCreation.do">

	<logic:equal name="manageActivityCreationForm" property="actionSubmit" value="insert_mode">
		<bean:define id="button" value="form.button.manageActivityCreation.insert"/>
		<bean:define id="caption" value="form.title.manageActivityCreation.insert"/>
	</logic:equal>
	
	<logic:equal name="manageActivityCreationForm" property="actionSubmit" value="update_mode">
		<bean:define id="button" value="form.button.manageActivityCreation.update"/>
		<bean:define id="caption" value="form.title.manageActivityCreation.update"/>
	</logic:equal>

	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	
    <forms:form 
    type="edit" 
    name="manageActivityCreationForm" 
    caption="${pageScope.caption}" 
    formid="frmActivityCreation" 
    width="550">
    
    
		 <forms:text        
		 label="form.field.manageActivityCreation.name"         
		 property="name"    
		 size="20"  
		 maxlength="20" 
		 required="true"  
		 />
 
         <forms:textarea
            label="form.field.manageActivityCreation.details"
            property="details"
            cols="64"
            rows="3"          
          />
            
            
        <forms:buttonsection>
			<forms:button
				name="btnSave"
				text="${pageScope.button}"/>
		</forms:buttonsection>
        
    </forms:form>
    
    <html:hidden property="activityId"/>
    <html:hidden property="actionSubmit"/>
    
</html:form>