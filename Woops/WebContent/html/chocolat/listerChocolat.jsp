<%@ page language="java"  contentType="text/html; charset=iso-8859-15" %>
<%@ taglib uri="../../WEB-INF/tags/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/tags/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/tags/struts-logic.tld" prefix="logic" %>

<html>

	<HEAD>
		<title>WOOPS</title>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-15">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">
	</HEAD>
	<body>

	<html:form action="/html/chocolat/listerChocolat">
		<html:hidden property="actionSubmit" />
		
		
				<table cellSpacing="0" cellPadding="0" border="0" width="810">
					<tr>
						<td align="center">
							Numéro
						</td>
						<td>
							Nom du chocolat
						</td>
						<td align="center">
							Calories au kilo
						</td>
						<td>
							Fabricant
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<hr>
						</td>
					</tr>
					<logic:iterate id="liste" name="listerChocolatForm" property="listeItems" > 
						<tr>								
							<td align="center">
								<span class="textetab"><bean:write name="liste" property="id"/></span>
							</td>
							
							<td>
								<span class="textetab"><bean:write name="liste" property="nom"/></span>	
							</td>							    
   		
							<td align="center">
								<span class="textetab"><bean:write name="liste" property="calorie"/></span>
							</td>	    
				
							<td>
								<span class="textetab"><bean:write name="liste" property="fabricant"/></span>
							</td>	    			
			      							    						    							    																				
								
						</tr>
					</logic:iterate> 
					<tr><td class="ligneBleue" height="1" width="836" colspan="17"></td></tr>	
				</table>
				
				
	</html:form>
	</body>
</html>
