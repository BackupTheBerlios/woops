<%@ taglib uri="/cc-template" prefix="template" %>


<template:insert template="../template.jsp">

    <template:put  name="title"     content="page.title.manageDependancesTypes" direct="true"/>
    <template:put  name="menuHaut"  content="./identifications.jsp" />
    <template:put  name="menu"  	content="./menu.jsp" />
    <template:put  name="contents"  content="./activity/manageDependancesTypesContent.jsp"/>
    
</template:insert>