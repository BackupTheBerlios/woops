<%@ taglib uri="/cc-template" prefix="template" %>


<template:insert template="/html/template.jsp">

    <template:put  name="title"     content="page.title.listActivities" direct="true"/>
    <template:put  name="menuHaut"  content="/html/user/subview/identification.jsp" />
    <template:put  name="menu"  	content="/html/user/subview/menu.jsp"/>
    <template:put  name="contents"  content="/html/user/activity/performing/listActivitiesContent.jsp"/>
    
</template:insert>