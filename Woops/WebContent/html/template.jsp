<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>



<html>

<head>
<title>woops</title>
<meta http-equiv="Content-Type" content="text/html;">
<link href="./html/images/css.css" rel="stylesheet" type="text/css">

<util:jsp directive="includes"/>
</head>

<body>

<table border="0" cellpadding="0" cellspacing="0" >

<tr>
  <td width="227" height="100%" valign="top">
  
  
 <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
  
  <tr>
    <td colspan="3" height="122" style="background-image: url('./html/images/logo.jpg');">

    </td>
  </tr>
  
    <tr>
    <td colspan="3" height="23" style="background-image: url('./html/images/menuHaut.jpg');" >

    </td>
  </tr>
  
  <tr>
    <td width="35" height="35" style="background-image: url('./html/images/idGauche.jpg');">

    </td>
      <td width="171" height="35" style="background-image: url('./html/images/id.jpg');">
      
      
  <!-------#################------->
  <!-------DEBUT ENTETE MENU------->
<template:get name="menuHaut"/>
  <!-------FIN ENTETE MENU------->
  <!-------###############------->
  
  
    </td>
    <td width="21" height="35" style="background-image: url('./html/images/idDroit.jpg');" >

    </td>
  </tr>
  
  <tr>
    <td colspan="3" height="15" style="background-image: url('./html/images/sousId.jpg');" >

    </td>
  </tr>
  
  <tr>
    <td  width="35" height="100%" style="background-image: url('./html/images/menuGauche.jpg');">
    </td>
      <td width="171" valign="top" height="100%" style="background-image: url('./html/images/menu.jpg');" >
      
      
  <!-------#################------->
  <!-------DEBUT MENU------->
  
  <template:get name="menu"/>
  
  <!-------FIN MENU------->
  <!-------########------->
  
    </td>
    <td width="21" height="100%" style="background-image: url('./html/images/menuDroit.jpg');" >
    </td>
  </tr>
  

  
  <tr>
    <td colspan="3" height="36" style="background-image: url('./html/images/menuBas.jpg');" >

    </td>
  </tr>
  
</table>
  
  
  
  
  </td>
  
  <td width="801" valign="top">
  
  
   <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
  
  <tr>
    <td width="805" height="36" style="background-image: url('./html/images/contenuHaut.jpg');">

    </td>
  </tr>
  
  <tr>
    <td height="27" style="background-image: url('./html/images/titre.jpg');padding:0 0 5 20;" >
    
    
  <!-------###########------->
  <!-------DEBUT TITRE------->
 <template:get name="title"/>
  <!-------FIN TITRE------->
  <!-------#########------->
  
  
    </td>
  </tr>
  
  <tr>
    <td valign="top" width="805"  height="100%" style="background-image: url('./html/images/contenu.jpg'); padding:0 0 0 20;" >
    
    
  <!-------#############------->
  <!-------DEBUT CONTENU------->
  
<template:get name="contents"/>
  
  <!-------FIN CONTENU------->
  <!-------###########------->
 
  

    </td>
  </tr>
  
  <tr>
    <td height="36" style="background-image: url('./html/images/contenuBas.jpg');" >

    </td>
  </tr>
  
  
  </td>
</tr>



</table>


  <util:jsp directive="endofpage"/>  
</body>
</html>