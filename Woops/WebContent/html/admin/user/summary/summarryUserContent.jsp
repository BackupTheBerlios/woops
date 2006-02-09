<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>

<html:form action="showActivitySummary.do">	
	
	<table>
	
	<tr>
	<td colspan="3">	
	<forms:form 
		type="display" 
	    name="showUserSummaryForm" 
	    caption="admin.showSummary.title" 
	    formid="frmShowActivitySummary" 
	    width="550"
	    >
    
    
		<forms:text        
			label="form.field.activity.name"         
		 	property="firstName"        
		 	/>
 
       	<forms:text
			label="form.field.activity.details"
            property="lastname"          
          	/>
          
     	<forms:text
		label="form.field.activity.details"
        property="login"          
      	/>
      	
  		<forms:text
		label="form.field.activity.details"
        property="role"          
      	/>
       
          	
   	</forms:form>
   
   	</td>
	
	</tr>
	
	</table>
		
</html:form>
 

  
