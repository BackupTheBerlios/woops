<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/cc-forms"    prefix="forms" %>
<%@ taglib uri="/cc-base"     prefix="base" %>
<%@ taglib uri="/cc-utility" prefix="util" %>



test accés page de creation d'activité

<html:form action="manageActivityCreation.do">
	
	
    <forms:form 
    type="edit" 
    name="manageActivityCreationForm" 
    caption="form.title.manageActivityCreation" 
    formid="frmActivityCreation" 
    width="550">
    
    
		 <forms:text        
		 label="form.text"         
		 property="name"    
		 size="20"  
		 maxlength="20" 
		 required="true"  
		 />
 
         <forms:textarea
            label="form.textarea"
            property="details"
            cols="64"
            rows="3"          
          />
            
            
        <forms:buttonsection>
			<forms:button
				name="btnSave"
				text="form.button.manageActivityCreation.submit"/>
		</forms:buttonsection>
        
        
    </forms:form>
    
</html:form>