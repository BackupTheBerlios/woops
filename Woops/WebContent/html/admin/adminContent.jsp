<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-utility"  prefix="util" %>
<%@ page import="org.apache.struts.util.MessageResources" %>

<bean:define id="confirmMessageDeleteUser" value="<%=MessageResources.getMessageResources("ApplicationResources").getMessage("table.field.listUsers.deleteConfirmation")%>"/>

<html:form action="admin.do">	
	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
	
	<ctrl:list 
		id="list"
		property="listBreakDownElements" 
		title="table.title.listBreakDownElements"
		width="650" 
		rows="10" 
		refreshButton="false" 
		createButton="true"
		>
			<ctrl:columntext 
				title="table.field.listBreakDownElements.prefix"
				property="prefix" 
				width="350"
				sortable="true"/>

			<ctrl:columntext 
				title="table.field.listBreakDownElements.name"
				property="name" 
				width="350"
				sortable="true"/>
			
			<ctrl:columntext 
				title="table.field.listBreakDownElements.startDate"
				property="startDate"
				width="300"
				sortable="true"/>
				
			<ctrl:columntext 
				title="table.field.listBreakDownElements.endDate"
				property="endDate"
				width="300"
				sortable="true"/>
				
			<ctrl:columntext 
				title="table.field.listBreakDownElements.kind"
				property="kind"
				width="150"
				sortable="true"/>
				
			<ctrl:columnedit 
				title="table.field.listBreakDownElements.edit"/> 

			<ctrl:columndelete 
				title="table.field.listBreakDownElements.delete"
				onclick="return confirm('${confirmMessage}');"
				/>
	</ctrl:list>
<br>
	<ctrl:list 
		id="list"
		property="listUsers" 
		title="table.title.listUsers"
		width="650" 
		rows="10" 
		refreshButton="false" 
		createButton="true"
		>
			<ctrl:columndrilldown
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
				onclick="return confirm('${confirmMessageDeleteUser}');"
				/> 
	</ctrl:list>
	
</html:form>
 

  
