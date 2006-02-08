<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"     prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/cc-controls" prefix="ctrl" %>


<html:form action="manageActivityCreation.do">

	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<forms:message formid="frmWarning" caption="msg.warning" severity="warning" width="350"/>
	
    <forms:form 
    type="edit" 
    name="manageActivityCreationForm" 
    caption="${manageActivityCreationForm.caption}" 
    formid="frmActivityCreation" 
    width="550">
    
    
		 <forms:text        
		 label="form.field.activity.name"         
		 property="name"     
		 maxlength="50" 
		 required="true"  
		 />
 
         <forms:textarea
            label="form.field.activity.details"
            property="details"
            cols="64"
            rows="3"          
          />
            
            
        <forms:buttonsection default="btnNext">
        
            <forms:button
				name="btnPrevious"
				text="form.button.previous"
				title="form.tooltip.manageActivityCreation.previous"
				/>
				
        	<forms:button
				name="btnNext"
				text="form.button.next"
				disabled="${manageActivityCreationForm.disableNext}"
				title="${manageActivityCreationForm.tooltipNext}"
				/>
				
			<forms:button
				name="btnFinish"
				text="form.button.finish"
				title="${manageActivityCreationForm.tooltipFinish}"
				/>
 
		</forms:buttonsection>
        
    </forms:form>
    
    
    <html:hidden property="activityId"/>
    <html:hidden property="mode"/>
    
</html:form>