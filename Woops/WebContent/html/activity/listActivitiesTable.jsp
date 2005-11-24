<%@ taglib uri="/cc-controls" prefix="ctrl" %> 



	<forms:message formid="frmError" caption="title.error" severity="error" width="350"/>

	<ctrl:list id="list" action="/listActivities.do" name="listActivitiesForm" property="listActivities" 
title="User List" width="500" rows="15" refreshButton="true" createButton="false"> 


		<ctrl:columndrilldown title="Name" property="name" width="150"/>
		<ctrl:columntext title="Details" property="details" width="350"/>
		
		<ctrl:columnedit title="Edit"/> 
		<ctrl:columndelete title="Delete"/>
		

</ctrl:list>
 

  
