<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<html:form action="admin.do">	
	
	<ctrl:list 
		id="listUser" 
		property="listUsers" 
		title="table.title.listUsers"
		width="650" 
		rows="3" 
		refreshButton="false" 
		createButton="true"
		>
		
			<ctrl:columntext 
				title="table.field.listUsers.firstName" 
				property="firstName" 
				width="250"
				sortable="true"/>
			
			<ctrl:columntext 
				title="table.field.listUsers.lastName"
				property="lastName"
				width="350"
				sortable="true"/>
				
			<ctrl:columntext   
				title="table.field.listUsers.login"
				property="login"
				width="350"
				sortable="true"/>
				
			<ctrl:columntext   
				title="table.field.listUsers.role"
				property="role"
				width="350"
				sortable="true"/>
			
			<ctrl:columnedit 
				title="table.field.listUsers.edit"/> 

			<ctrl:columndelete 
				title="table.field.listUsers.delete"
				onclick="return confirm('table.field.listActivities.deleteConfirmation');"
				/> 
			
	</ctrl:list>
	
</html:form>
 

  
