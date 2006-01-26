<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>

<template:insert template="/html/template.jsp">

    <template:put  name="title"  direct="true">
    	<util:resource key="page.title.listActivities"/>
    </template:put>
    
    <template:put  name="menuHaut"  content="/html/identifications.jsp" />
    <template:put  name="menu"  	content="/html/menu.jsp"/>

</template:insert>