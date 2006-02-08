<%@ taglib uri="/cc-menu" prefix="menu" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<menu:menu  id="user"  type="sidebar"  width="150">
     	
     	<menu:menuitem   	
        	id="1"  
        	text="admin.admin"      
        	action="admin.do"/>
        
        <menu:menuitem   	
        	id="2"  
        	text="admin.menu.listUsers"      
        	action="listUsers.do"/>
        	
        <menu:menuitem   	
        	id="3"  
        	text="admin.menu.createUser"      
        	action="addUser.do"/>
     	
        <menu:menuitem   	
        	id="5"  
        	text="menu.deconnection"      
        	action="logoutUser.do"/>
        	
      
        

</menu:menu>
