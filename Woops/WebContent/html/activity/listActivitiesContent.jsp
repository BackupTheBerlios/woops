<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
	
	<ctrl:list 
		id="list" 
		action="listActivities.do" 
		name="listActivitiesForm" 
		property="listActivities" 
		title="table.title.listActivities" 
		width="500" 
		rows="15" 
		refreshButton="false" 
		createButton="false"
		rows="10">

			<ctrl:columndrilldown 
				title="table.field.listActivities.name" 
				property="name" 
				width="150"/>
			
			<ctrl:columntext 
				title="table.field.listActivities.details"
				property="details"
				width="350"/>
				
			<ctrl:columnhtml id="activity"
				title="table.field.listActivities.state"
				width="150">
					<bean:message
						name="activity"
						property="state"/>
			</ctrl:columnhtml> 
			
			<ctrl:columnedit 
				title="table.field.listActivities.edit"/> 
			
			<ctrl:columndelete  
				title="table.field.listActivities.delete"/>
			
			<ctrl:columnbutton 
				title="table.field.listActivities.action" 
				text="@{bean.action}" 
				align="center"
				command="change"
				width="150">
			</ctrl:columnbutton>
			
	</ctrl:list>
 

  
