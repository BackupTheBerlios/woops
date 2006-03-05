<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"    prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>
<%@ taglib uri="/cc-controls" prefix="ctrl" %> 
<html:form action="manageDpe.do"  >
<ctrl:list 
		id="list"
		property="listActivities" 
		title="table.title.listBreakDownElements"
		width="650" 
		rows="10" 
		refreshButton="false" 
		createButton="false"
		>
		
		 <ctrl:columncheckbox title="Edit" property="selectionne" editable="true" select="multiple"/>
		
		<ctrl:columntext 
				title="table.field.listBreakDownElements.startDate"
				property="name"
				width="300"
				sortable="true"/>
				
		<ctrl:columnselect title="a changer" property="listBde" optionsProperty="currentName" />

			
		
		
</ctrl:list>
	    
</html:form>
