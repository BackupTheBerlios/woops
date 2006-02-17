<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>

<html:form action="showUserSummary.do">	
	
	<table>
	
	<tr>
	<td colspan="3">	
	<forms:form 
		type="display" 
	    name="showUserSummaryForm" 
	    caption="admin.showSummary.title" 
	    formid="frmShowUserSummary" 
	    width="550"
	    >

		<forms:text        
			label="admin.addUser.firstName"         
		 	property="firstName"        
		 	/>
 
       	<forms:text
			label="admin.addUser.lastName"
            property="lastName"          
          	/>
          
     	<forms:text
			label="table.field.listUsers.login"
	        property="login"          
      	/>
      	
  		<forms:text
			label="table.field.listUsers.role"
	        property="role"          
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
			id="bdeList" 
			property="bdeList" 
			title="form.table.predecessors.title"  
			rows="5"
			width="100%"
			>
			
			<ctrl:columntext
				title="form.table.predecessors.field.predecessor"
				property="bde"
				/>
		</ctrl:list>
		</td>
	</tr>
	</table>
		
</html:form>
 

  
