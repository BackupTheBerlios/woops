<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>

<template:insert base="/html" template="$/template.jsp">

     <template:put  name="title"  direct="true">
    	<util:resource key="admin.listUsers.title" /></template:put>
    <template:put  name="menuHaut"  content="$/admin/subview/identification.jsp" />
    <template:put  name="menu"  	content="$/admin/subview/menu.jsp"/>
	<forms:message formid="frmError" caption="msg.error" severity="error" width="350"/>
	<forms:message formid="frmInfo" caption="msg.info" severity="information" width="350"/>
    <template:put  name="UserContents"  content="$/admin/adminListUserContent.jsp"/>
    
</template:insert>