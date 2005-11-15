<%@ page language="java"  contentType="text/html; charset=iso-8859-15" %>
<%@ taglib uri="../../WEB-INF/tags/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/tags/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/tags/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/tags/cc-forms.tld" prefix="forms" %>

<html>

	<HEAD>
		<title>WOOPS</title>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-15">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">
	</HEAD>
	<body>

	
	<html:form action="/html/activity/createActivity">
		<html:hidden property="actionSubmit" />
	</html:form>
		
	</body>
</html>