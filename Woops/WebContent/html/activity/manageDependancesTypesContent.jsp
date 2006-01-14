<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"     prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="/cc-controls" prefix="ctrl" %>


<html:form action="/manageDependancesTypes">
	
	<forms:message formid="frmError" caption="title.error" severity="error" width="350"/>
	
    <ctrl:list
		name="KEY_DEPENDANCES_LIST"
		title="list.title.manageDependancesTypes">
       
        <ctrl:columntext    title="list.manageDependancesTypes.predecessor"         property="predecessor"/>
        <ctrl:columntext    title="list.manageDependancesTypes.successor"         	property="successor"/>
              
        <ctrl:columngroup title="list.manageDependancesTypes.columngroup.title" align="center">
			<ctrl:columnradio	title="list.manageDependancesTypes.columngroup.fs"     property="linkType"   value="finishToStart"		editable="true"/>
			<ctrl:columnradio	title="list.manageDependancesTypes.columngroup.ff"     property="linkType"   value="finishToFinish"    editable="true"/>
			<ctrl:columnradio	title="list.manageDependancesTypes.columngroup.ss"     property="linkType"   value="startToStart"  	editable="true"/>
			<ctrl:columnradio	title="list.manageDependancesTypes.columngroup.sf"   	property="linkType"   value="startToFinish"   	editable="true"/>
		</ctrl:columngroup>      
               
    </ctrl:list>
        
   	<br>
        
	<ctrl:button
		name="btnSave"
		text="list.button.manageDependancesTypes.submit"/>

	<html:hidden property="activityId"/>
   
</html:form>
