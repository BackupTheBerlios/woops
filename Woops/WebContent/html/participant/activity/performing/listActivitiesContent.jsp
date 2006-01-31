<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-utility"  prefix="util" %>
<%@ page import="org.apache.struts.util.MessageResources" %>

<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
<br>

<bean:define id="confirmMessage" value="<%=MessageResources.getMessageResources("ApplicationResources").getMessage("table.field.listActivities.deleteConfirmation")%>"/>

<html:form action="listActivities.do">	
	
	<ctrl:list 
		id="list" 
		property="listActivities" 
		title="table.title.listActivities" 
		width="650" 
		rows="5" 
		refreshButton="true" 
		createButton="true"
		>
		
		<util:designrule
        rule="@{bean.action == '' && bean.state == 'created'}"
        style="background-color: #FFC4C4;"/>
        
        <util:designrule
        rule="@{bean.action == '' && bean.state == 'inProgress'}"
        style="background-color: #FFC4C4; font-weight: bold;"/>
        
        <util:designrule
        rule="@{bean.action != '' && bean.state == 'created'}"
        style=""/>
        
        <util:designrule
        rule="@{bean.action != '' && bean.state == 'inProgress'}"
        style="font-weight: bold;"/>

			<ctrl:columndrilldown 
				title="table.field.listActivities.name" 
				property="name" 
				width="250"
				sortable="true"
				/>
				
			<ctrl:columnhtml id="activity"
				title="table.field.listActivities.state"
				width="150"
				>
					<bean:message
						name="activity"
						property="state"/>
			</ctrl:columnhtml> 
			
			<ctrl:columnedit 
				title="table.field.listActivities.edit"/> 

			<ctrl:columndelete 
				title="table.field.listActivities.delete"
				onclick="return confirm('${confirmMessage}');"
				/> 
				
			<ctrl:columnbutton 
				title="table.field.listActivities.action" 
				text="@{bean.action}" 
				align="center"
				command="change"
				width="150">
			</ctrl:columnbutton>
			
	</ctrl:list>
	
</html:form>
 

  
