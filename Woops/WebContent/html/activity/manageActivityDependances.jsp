<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/cc-forms.tld"    prefix="forms" %>
<%@ taglib uri="/WEB-INF/tlds/cc-base.tld"     prefix="base" %>

<html:form action="/manageActivityDependances">

    <forms:form type="edit" caption="G�rer les d�pendances d'une activit�" formid="frmActivityDependances" width="550">

        <forms:swapselect
            property="realDependancesList"
            label="Liste des activit�s d�pendantes"
            orientation="horizontal"
            labelLeft="Possibles"
            labelRight="Effectifs"
            valign="top"
            size="10"
            width="100%"
            filter="false"
            required="false"
            disabled="false">

            <base:options property="possibleDependancesList"  keyProperty="isbn" labelProperty="title"/>

        </forms:swapselect>
    </forms:form>

</html:form>
