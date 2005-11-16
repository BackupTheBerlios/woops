<%@ taglib uri="/WEB-INF/tags/cc-utility.tld" prefix="util" %>
<%@ taglib uri="/WEB-INF/tags/cc-controls.tld" prefix="ctrl" %> 


<ctrl:list id="list" action="activty/listActivities" name="listActivitiesForm" 
title="User List" width="500" rows="15" refreshButton="true" createButton="true"> 


		<ctrl:columntext title="Name" property="name" width="350"/>
		
		<ctrl:columnedit title="Edit"/> 
		<ctrl:columndelete title="Delete"/>
		

</ctrl:list>
 
 
 
liste des activites



<html>

<head>
 <util:jsp directive="includes"/>
 </head> 
  
  <body leftmargin="0" topmargin="0" onload="init();"> 

  <util:jsp directive="endofpage"/> 
  
  </BODY>
  
 </html>
  
