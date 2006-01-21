<%@ taglib uri="/cc-template" prefix="template" %>


<template:insert template="/html/template.jsp">

    <template:put  name="title"     content="page.title.listChangeStateActivities" direct="true"/>
    <template:put  name="menuHaut"  content="/html/identifications.jsp" />
    <template:put  name="menu"  	content="/html/menu.jsp"/>
    <template:put  name="contents"  content="/html/activity/listChangeStateActivitiesContent.jsp"/>
    
</template:insert>