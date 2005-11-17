<%@ taglib uri="/WEB-INF/tags/cc-template.tld" prefix="template" %>


<template:insert template="../template.jsp">

    <template:put  name="title"     content="connexion" direct="true"/>
    <template:put  name="menuHaut"  content="./identifications.jsp" />
    <template:put  name="menu"  	content="./menu.jsp"/>
    <template:put  name="contents"  content="" direct="true"/>
    
</template:insert>