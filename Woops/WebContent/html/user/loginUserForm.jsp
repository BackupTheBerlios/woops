<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>


<html:form action="/login" >
	<forms:message formid="frmError" caption="title.error" severity="error" width="350"/>
    <forms:message formid="frmError" caption="title.error" severity="information" width="350"/>
    <forms:form type="edit" caption="form.title.login" formid="loginForm" width="350">
    
        <forms:text			label="form.field.login"	property="login"    	required="true"/>
        <forms:password    	label="form.field.password"	property="password"   	required="true"/>

       <forms:buttonsection>  
               <forms:button
                name="btnLogin"
                title="button.title.login"
                text="button.title.login"
                width="100"/>     
        </forms:buttonsection>
    
    </forms:form>
    
</html:form>
