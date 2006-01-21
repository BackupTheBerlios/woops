<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>

<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
<br>

<html:form action="listChangeStateActivities.do">	
	
	<ctrl:list 
		id="list" 
		property="listChangeStateActivities" 
		title="table.title.listChangeStateActivities" 
		width="500" 
		rows="5" 
		refreshButton="false" 
		createButton="true"
		>

			<ctrl:columndrilldown 
				title="table.field.listChangeStateActivities.name" 
				property="name" 
				width="250"
				sortable="true"/>
			
			<ctrl:columntext 
				title="table.field.listChangeStateActivities.details"
				property="details"
				width="350"/>
				
			<ctrl:columnhtml id="activity"
				title="table.field.listChangeStateActivities.state"
				width="150"
				>
					<bean:message
						name="activity"
						property="state"/>
			</ctrl:columnhtml> 
			
			<ctrl:columnedit 
				title="table.field.listChangeStateActivities.edit"/> 

			<ctrl:columnbutton 
				title="table.field.listChangeStateActivities.action" 
				text="@{bean.action}" 
				align="center"
				command="change"
				width="150">
			</ctrl:columnbutton>
			
	</ctrl:list>
	
</html:form>
 

  
