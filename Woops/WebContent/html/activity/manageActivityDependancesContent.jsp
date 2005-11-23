<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"     prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>


<html>

<head>
 <util:jsp directive="includes"/>
 </head> 
  
  <body leftmargin="0" topmargin="0" onload="init();"> 


<html:form action="/manageActivityDependances">

	<forms:message formid="frmActivityDependances" caption="Information" severity="information" width="350"/>

    <forms:form type="edit" name="manageActivityDependancesForm" caption="G?rer les d?pendances d'une activit?" formid="frmActivityDependances" width="550">

        <forms:swapselect
            property="realDependancesKeys"
            label="Liste des activit?s d?pendantes"
            orientation="horizontal"
            labelLeft="Possibles"
            labelRight="Effectifs"
            valign="top"
            size="10"
            width="100%"
            align="center"
            filter="false"
            required="false"
            disabled="false">
            
            <base:options property="possibleDependancesOptions"  keyProperty="id" labelProperty="name"/>
        
        </forms:swapselect>
        
        <forms:buttonsection default="btnSave">
			<forms:button
				name="btnSave"
				text="Valider" 
			    width="100"/>
		</forms:buttonsection>
        
    </forms:form>

</html:form>



  <util:jsp directive="endofpage"/> 
  
  </BODY>
  
 </html>
