<%@ taglib uri="/cc-template" prefix="template" %>
<%@ taglib uri="/cc-utility" prefix="util" %>



<html>

<head>
<title>woops</title>
<meta http-equiv="Content-Type" content="text/html;">
<link href="./html/images/css.css" rel="stylesheet" type="text/css">

<util:jsp directive="includes"/>
 
</head>

<body leftmargin="0" topmargin="0" onload="init();">

<center>
<table border="0" cellpadding="0" cellspacing="0" width="1022">

  <tr>
   <td rowspan="2"><img name="logo" src="./html/images/logo.jpg" width="224" height="132" border="0" alt=""></td>
   <td valign="top" width="798" height="57" style="padding-left: 20px ;padding-top: 24px ; background:url('./html/images/titre.jpg') no-repeat ;" >
   
  
  <!-------###########------->
  <!-------DEBUT TITRE------->
 <template:get name="title"/>
  <!-------FIN TITRE------->
  <!-------#########------->
  
  </td>
  </tr>
  <tr>
   <td valign="top" rowspan="3" width="798"  style=" padding-left: 20px ;padding-top: 10px ;background:url('./html/images/contenu.jpg') repeat-y ;" >
   
   
  <!-------#############------->
  <!-------DEBUT CONTENU------->
  
<template:get name="contents"/>
  
  <!-------FIN CONTENU------->
  <!-------###########------->
  
  
  </td>
  </tr>
  <tr>
   <td valign="top" width="224"  height="59" style="padding-left: 25px ; background:url('./html/images/entetemenu.jpg') no-repeat; " >
   
   
  <!-------#################------->
  <!-------DEBUT ENTETE MENU------->
<template:get name="menuHaut"/>
  <!-------FIN ENTETE MENU------->
  <!-------###############------->
  
  
  </td>
  </tr>
  <tr>
   <td valign="top" height="300" width="224"  style="padding-left: 25px ; background:url('./html/images/menu.jpg') repeat-y ; ">
   
   
  <!-------#################------->
  <!-------DEBUT MENU------->
  
  <template:get name="menu"/>
  
  <!-------FIN MENU------->
  <!-------########------->
  
  
  
  </td>
  </tr>
  <tr>
   <td><img name="basmenu" src="./html/images/basmenu.jpg" width="224" height="30" border="0" alt=""></td>
   <td><img name="bascontenu" src="./html/images/bascontenu.jpg" width="798" height="30" border="0" alt=""></td>
  </tr>

</table>
</center>

  <util:jsp directive="endofpage"/> 
  
</body>
</html>
