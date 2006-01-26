<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-utility"  prefix="util" %>

<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
<br>

<html:form action="listActivities.do">	
	
	<ctrl:list 
		id="list" 
		property="listActivities" 
		title="table.title.listActivities" 
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
				title="table.field.listActivities.name" 
				property="name" 
				width="250"
				sortable="true"/>
			
			<ctrl:columntext 
				title="table.field.listActivities.details"
				property="details"
				width="350"/>
				
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
				onclick="return confirm('table.field.listActivities.deleteConfirmation');"
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
 

  
