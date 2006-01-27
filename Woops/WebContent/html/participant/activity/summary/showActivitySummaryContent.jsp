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
          
        <forms:html
            label="form.field.activity.state"
			>
    		<bean:message 
    			name="showActivitySummaryForm"
    			property="state"
    			/>
    	</forms:html>
          
        <forms:text
			label="form.field.activity.startDate"
            property="startDate"          
          	/>
        
        <forms:text
			label="form.field.activity.endDate"
            property="endDate"          
          	/>    
          	
    </forms:form>
    
	<br>
	
	<ctrl:list 
		id="predecessorsList" 
		property="predecessorsList" 
		title="predecesors"  
		rows="5"
		>
		
		<ctrl:columntext
			title="predecessor"
			property="predecessor"
			/>
		
			
	</ctrl:list>
	
	<br>
    
    <ctrl:button
		name="btnPrevious"
		text="form.button.previous"
		/>
		
</html:form>
 

  
