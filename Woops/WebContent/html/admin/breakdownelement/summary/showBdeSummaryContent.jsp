<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>

<html:form action="showBdeSummary.do">	
	
	<table>
	
	<tr>
	<td colspan="3">	
	<forms:form 
		type="display" 
	    name="showBdeSummaryForm" 
	    caption="admin.showBdeSummary.title" 
	    formid="frmShowUserSummary" 
	    width="550"
	    >

		<forms:text        
			label="table.field.listBreakDownElements.prefix"         
		 	property="prefix"        
		 	/>
 
       	<forms:text
			label="table.field.listBreakDownElements.name"
            property="name"          
          	/>
          
     	<forms:text
			label="table.field.listBreakDownElements.details"
	        property="details"          
      	/>
      	
  		<forms:text
			label="table.field.listBreakDownElements.startDate"
	        property="startDate"          
      	/>
      	
      	<forms:text
			label="table.field.listBreakDownElements.endDate"
	        property="endDate"          
      	/>
      	
      	<forms:text
			label="table.field.listBreakDownElements.kind"
	        property="kind"          
      	/>
       
          	
   	</forms:form>
   
   	</td>
	
	</tr>
	
	<tr height="20">
   	<td colspan="3"></td>
   	</tr>
   
	<tr>
	<td valign="top" width="48%">
		<ctrl:list 
			id="usersList" 
			property="usersList" 
			title="admin.showSummary.bde"  
			rows="5"
			width="100%"
			>
			
			<ctrl:columntext
				title="admin.showSummary.tableBde"
				property="users"
				/>
		</ctrl:list>
		</td>
	</tr>
	</table>
		
</html:form>
 

  