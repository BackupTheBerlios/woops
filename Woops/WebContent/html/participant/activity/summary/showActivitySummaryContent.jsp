<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>

<html:form action="showActivitySummary.do">	
	
	<forms:form 
		type="display" 
	    name="showActivitySummaryForm" 
	    caption="form.title.showActivitySummary" 
	    formid="frmShowActivitySummary" 
	    width="550"
	    >
    
    
		<forms:text        
			label="form.field.activity.name"         
		 	property="name"        
		 	/>
 
       	<forms:text
			label="form.field.activity.details"
            property="details"          
          	/>
          
        <forms:text
            label="form.field.activity.state"        
    		property="state"
    		/>
          
        <forms:text
			label="form.field.activity.startDate"
            property="startDate"          
          	/>
        
        <forms:text
			label="form.field.activity.endDate"
            property="endDate"          
          	/>    
            
        <forms:buttonsection default="btnPrevious">
        
            <forms:button
				name="btnPrevious"
				text="form.button.previous"
				/>
 
		</forms:buttonsection>
        
    </forms:form>
	
</html:form>
 

  
