<%@ taglib uri="/WEB-INF/tags/cc-utility.tld" prefix="util" %>
<%@ taglib uri="/WEB-INF/tags/cc-controls.tld" prefix="ctrl" %> 



<html>

<head>
 <util:jsp directive="includes"/>
 </head> 
  
  <body leftmargin="0" topmargin="0" onload="init();"> 



	<ctrl:list id="list" action="/listActivities.do" name="listActivitiesForm" property="listActivities" 
title="User List" width="500" rows="15" refreshButton="true" createButton="false"> 


		<ctrl:columndrilldown title="Name" property="name" width="350"/>
		<ctrl:columntext title="Description" property="description" width="350"/>
		
		<ctrl:columnedit title="Edit"/> 
		<ctrl:columndelete title="Delete"/>
		

</ctrl:list>
 

  <util:jsp directive="endofpage"/> 
  
  </BODY>
  
 </html>
  
