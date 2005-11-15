  <form action="#" method="POST">
  Nom de l'activité :<br>
  <input type="text" name="nom" size="80"><br><br>
    Description de l'activité :<br>
  <textarea name="description" cols="80" rows="5"></textarea><br><br>
  Début: 
<select name="dateSortie[d]"><option value="1">01</option><option value="2">02</option><option value="3">03</option><option value="4">04</option><option value="5">05</option><option value="6">06</option><option value="7" selected="selected">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option></select>-<select name="dateSortie[M]"><option value="1">Jan</option><option value="2">Fev</option><option value="3">Mar</option><option value="4">Avr</option><option value="5">Mai</option><option value="6">Jun</option><option value="7">Jul</option><option value="8">Aou</option><option value="9">Sep</option><option value="10">Oct</option><option value="11" selected="selected">Nov</option><option value="12">Dec</option></select>-<select name="dateSortie[Y]"><option value="1995">1995</option><option value="1996">1996</option><option value="1997">1997</option><option value="1998">1998</option><option value="1999">1999</option><option value="2000">2000</option><option value="2001">2001</option><option value="2002">2002</option><option value="2003">2003</option><option value="2004">2004</option><option value="2005" selected="selected">2005</option><option value="2006">2006</option></select>
  Fin prévue:
<select name="dateSortie[d]"><option value="1">01</option><option value="2">02</option><option value="3">03</option><option value="4">04</option><option value="5">05</option><option value="6">06</option><option value="7" selected="selected">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option></select>-<select name="dateSortie[M]"><option value="1">Jan</option><option value="2">Fev</option><option value="3">Mar</option><option value="4">Avr</option><option value="5">Mai</option><option value="6">Jun</option><option value="7">Jul</option><option value="8">Aou</option><option value="9">Sep</option><option value="10">Oct</option><option value="11" selected="selected">Nov</option><option value="12">Dec</option></select>-<select name="dateSortie[Y]"><option value="1995">1995</option><option value="1996">1996</option><option value="1997">1997</option><option value="1998">1998</option><option value="1999">1999</option><option value="2000">2000</option><option value="2001">2001</option><option value="2002">2002</option><option value="2003">2003</option><option value="2004">2004</option><option value="2005" selected="selected">2005</option><option value="2006">2006</option></select><br><br>
	
  
  
  Dépendances de l'activités :<br>
      <table  border="0" width="30%">
        <tr>

          <td rowspan="2" >
            <center>
              <select name="selectArtefactEntree" size="5" multiple="multiple" ondblclick="">
              <option value="artefact1"> Architecture </option>
              <option value="artefact2"> Modèle de cas d'utilisation </option>
              <option value="artefact3"> PDL </option></select>
            </center>
          </td>
          <td>

            <center>
              <input name="buttonAjouterArtefactEntree" value="&gt;" onclick="#" type="button">
            </center>
          </td>
          <td rowspan="2" >
            <center>
              <select name="selectArtefactEntreeChoisi" size="5" onclick="#"></select>
            </center>
          </td>

        </tr>
        <tr>
          <td>
            <center>
              <input name="buttonRetirerArtefactEntree" value="&lt;" onclick="#" type="button">
            </center>
          </td>
        </tr>
      </table>
      
      <br><br>
      <input type="submit" value="Ajouter">
</form>