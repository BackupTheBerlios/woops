<%@ taglib uri="/cc-template" prefix="template" %>

<template:insert base="/html" template="$/template.jsp">

    <template:put  name="title"     content="page.title.login" direct="true" />
    <template:put  name="contents"  content="$/logon/loginUserContent.jsp" />
    
</template:insert>