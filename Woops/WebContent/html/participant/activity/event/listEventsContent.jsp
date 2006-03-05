<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-utility"  prefix="util" %>
<%@ page import="org.apache.struts.util.MessageResources" %>

<bean:define id="basename" value="<%=PresentationConstantes.BASENAME%>" scope="page" type="java.lang.String" />
<bean:define id="confirmMessage" value="<%=MessageResources.getMessageResources(basename).getMessage("table.field.listActivities.deleteConfirmation")%>"/>


<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
<br>

<html:form action="listEvents.do">	

	<ctrl:list 
		id="listEventsNotOccured" 
		property="listEventsNotOccured" 
		title="table.title.listEventsNotOccured" 
		width="650" 
		rows="10" 
		refreshButton="true" 
		createButton="true"
		>
	
		<ctrl:columntext
			title="table.field.listEvents.name"
			property="name"
			/>
		
		<ctrl:columntext
			title="table.field.listEvents.name"
			property="details"
			/>
			
		<ctrl:columntext
			title="table.field.listEvents.activityName"
			property="activityName"
			/>
			
		<ctrl:columnedit 
			title="table.field.listEventsNotOccured.edit"
			tooltip="table.tooltip.listEventsNotOccured.edit"
			/> 

		<ctrl:columndelete 
			title="table.field.listEventsNotOccured.delete"
			onclick="return confirm('${confirmMessage}');"
			tooltip="table.tooltip.listEventsNotOccured.delete"
			/> 
		
		<ctrl:columnbutton 
			title="table.field.listEventsNotOccured.signalOccurence" 
			text="table.field.listEventsNotOccured.signalOccurence" 
			align="center"
			command="signalOccurence"
			/>
	
	</ctrl:list>

</html:form>