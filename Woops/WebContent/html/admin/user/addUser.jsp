<%@ taglib uri="/cc-template" prefix="template" %>

<template:insert base="/html" template="$/template.jsp">

    <template:put  name="title"     content="admin.title" direct="true" />
    <template:put  name="menuHaut"  content="$/admin/subview/identification.jsp" />
    <template:put  name="menu"  	content="$/admin/subview/menu.jsp"/>
    <template:put  name="contents"  content="$/admin/user/addUserContent.jsp" />
    
</template:insert>