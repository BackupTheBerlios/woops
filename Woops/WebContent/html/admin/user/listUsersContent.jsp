<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-utility"  prefix="util" %>

<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
<br>

<html:form action="listUsers.do">	
	
	<ctrl:list 
		id="list" 
		property="listUsers" 
		title="table.title.listUsers"
		width="650" 
		rows="5" 
		refreshButton="false" 
		createButton="true"
		>
		
		<util:designrule
        rule="@{bean.action == '' && bean.state == 'created'}"
        style="background-color: gray;"/>
        
        <util:designrule
        rule="@{bean.action == '' && bean.state == 'inProgress'}"
        style="background-color: gray; font-weight: bold;"/>
        
        <util:designrule
        rule="@{bean.action != '' && bean.state == 'created'}"
        style=""/>
        
        <util:designrule
        rule="@{bean.action != '' && bean.state == 'inProgress'}"
        style="font-weight: bold;"/>

			<ctrl:columndrilldown 
				title="table.field.listUsers.firstName" 
				property="firstName" 
				width="250"
				sortable="true"/>
			
			<ctrl:columntext 
				title="table.field.listUsers.lastName"
				property="lastName"
				width="350"/>
				
			<ctrl:columntext 
				title="table.field.listUsers.login"
				property="login"
				width="350"/>	
			
			<ctrl:columnedit 
				title="table.field.listUsers.edit"/> 

			<ctrl:columndelete 
				title="table.field.listUsers.delete"
				onclick="return confirm('table.field.listActivities.deleteConfirmation');"
				/> 
				
			<ctrl:columnbutton 
				title="table.field.listUsers.action" 
				text="@{bean.action}" 
				align="center"
				command="change"
				width="150">
			</ctrl:columnbutton>
			
	</ctrl:list>
	
</html:form>
 

  
