<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>


	<forms:message formid="frmError" caption="title.error" severity="error" width="350"/>

	<ctrl:list 
		id="list" 
		action="/listActivities.do" 
		name="listActivitiesForm" 
		property="listActivities" 
		title="table.title.listActivities" 
		width="500" 
		rows="15" 
		refreshButton="true" 
		createButton="true"
		rows="10"
		
		> 

			<ctrl:columndrilldown title="table.field.listActivities.name" property="name" width="150"/>
			<ctrl:columntext title="table.field.listActivities.details" property="details" width="350"/>
			<ctrl:columnbutton title="table.field.listActivities.state"/> 
			<ctrl:columnedit title="table.field.listActivities.edit"/> 
			<ctrl:columndelete  title="table.field.listActivities.delete"/>

	</ctrl:list>
 

  
