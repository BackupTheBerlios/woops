<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>

<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
<br>

<html:form action="historizedActivities.do">	
	
	<ctrl:list 
		id="list" 
		property="listActivities" 
		title="table.title.listActivities" 
		width="500" 
		rows="5" 
		refreshButton="false" 
		createButton="false"
		>

			<ctrl:columndrilldown 
				title="table.field.listActivities.name" 
				property="name" 
				width="250"
				sortable="true"/>
			
			<ctrl:columntext 
				title="table.field.listActivities.details"
				property="details"
				width="350"/>			
	</ctrl:list>
	
</html:form>
 

  