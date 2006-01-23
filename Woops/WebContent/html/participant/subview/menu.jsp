<%@ taglib uri="/cc-menu" prefix="menu" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<menu:menu  id="user"  type="sidebar"  width="150">

        <menu:menuitem   	
        	id="1"  
        	text="menu.deconnection"      
        	action="loginUser.do?actionSubmit=deconnect"/>
 
        <menu:menuitem   	
        	id="1"  
        	text="menu.listActivities"      
        	action="listActivities.do"/>
        	
        <menu:menuitem    	
        	id="2"  
        	text="menu.manageActivityCreation"      
        	action="manageActivityCreation.do"/>
        
		<menu:menuitem   	
        	id="1"  
        	text="menu.listChangeStateActivities"      
        	action="listChangeStateActivities.do"/>

</menu:menu>
