<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"     prefix="base" %>

<html:form action="/manageActivityDependances">

    <forms:form type="edit" name="manageActivityDependancesForm" caption="G?rer les d?pendances d'une activit?" formid="frmActivityDependances" width="550">

        <forms:swapselect
            property="realDependancesList"
            label="Liste des activit?s d?pendantes"
            orientation="horizontal"
            labelLeft="Possibles"
            labelRight="Effectifs"
            valign="top"
            size="10"
            width="100%"
            filter="false"
            required="false"
            disabled="false">

            <base:options property="possibleDependancesOptions"  keyProperty="id" labelProperty="name"/>

        </forms:swapselect>
    </forms:form>

</html:form>
