<%@ taglib uri="/cc-template" prefix="template" %>


<template:insert base="/html" template="$/template.jsp">

    <template:put  name="title"     content="page.title.manageDependancesTypes" direct="true"/>
    <template:put  name="menuHaut"  content="$/participant/subview/identification.jsp" />
    <template:put  name="menu"  	content="$/participant/subview/menu.jsp" />
    <template:put  name="contents"  content="$/participant/activity/manage/manageDependancesTypesContent.jsp"/>
    
</template:insert>