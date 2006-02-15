<%@ taglib uri="/cc-menu" prefix="menu" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<menu:menu  id="user"  type="sidebar"  width="150">

 
        <menu:menuitem   	
        	id="1"  
        	text="menu.listActivities"      
        	action="listActivities.do"/>
        	
        <menu:menuitem    	
        	id="2"  
        	text="menu.manageActivityCreation"      
        	action="manageActivityCreation.do"/>
        
        <menu:menuitem   	
        	id="3"  
        	text="menu.historizedActivities"      
        	action="historizedActivities.do"/>
        	
        <menu:menuitem   	
        	id="4"  
        	text="table.title.listFreeActivities"      
        	action="listFreeActivities.do"/>
        	
        <menu:menuitem   	
        	id="5"  
        	text="menu.showActivityGraph"      
        	action="showActivityGraph.do"/>

</menu:menu>
