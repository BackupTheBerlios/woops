<%@ taglib uri="/WEB-INF/tags/cc-template.tld" prefix="template" %>


<template:insert template="../template.jsp">

    <template:put  name="title"     content="G�rer les d�pendances des activit�s" direct="true"/>
    <template:put  name="menuHaut"  content="./identifications.jsp" />
    <template:put  name="menu"  	content="./menu.jsp" />
    <template:put  name="contents"  content="./activity/manageActivityDependancesContent.jsp.jsp"/>
    
</template:insert>