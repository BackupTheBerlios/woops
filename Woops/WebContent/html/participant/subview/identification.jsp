<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/cc-menu" prefix="menu" %>
<%@ taglib uri="/cc-controls" prefix="ctrl" %>
<%@ taglib uri="/cc-base" prefix="base" %>

<bean:write name="USER" property="firstName"/> <bean:write name="USER" property="lastName"/>

<menu:menu  id="identification"  type="sidebar"  width="150">

        <menu:menuitem   	
        	id="1"  
        	text="menu.deconnection"      
        	action="logoutUser.do"/>
        	
</menu:menu>

<br>

<ctrl:select
        name="USER"
        property="defaultBDEId"
        scope="session"
        onchange="window.open('listBDEs.do')"
        >
        	<base:options name="listActivitiesForm" property="listBDEs" keyProperty="id" labelProperty="label" />
</ctrl:select>
		    
		    