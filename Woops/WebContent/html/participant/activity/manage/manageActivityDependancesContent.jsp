<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"     prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<html:form action="manageActivityDependances.do">
	
	
	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	
    <forms:form type="edit" name="manageActivityDependancesForm" caption="form.title.manageActivityDependances" formid="frmActivityDependances" width="550">

        <forms:swapselect
            property="realDependancesKeys"
            label="form.field.manageActivityDependances"
            orientation="horizontal"
            labelLeft="form.field.manageActivityDependances.toAdd"
            labelRight="form.field.manageActivityDependances.added"
            valign="top"
            size="10"
            style="width: 200;"
            align="center"
            filter="false"
            required="false"
            disabled="false">
            
            <base:options 
            	property="possibleDependancesOptions"  
            	keyProperty="id" 
            	labelProperty="name"/>
        
		</forms:swapselect>
        
        <forms:buttonsection>
        
            <forms:button
				name="btnPrevious"
				text="previous..."/>
				
        	<forms:button
				name="btnNext"
				text="next..."/>
				
			<forms:button
				name="btnFinish"
				text="finish"/>
		    
		</forms:buttonsection>
        
        <html:hidden property="activityId"/>
        
    </forms:form>
    
</html:form>
