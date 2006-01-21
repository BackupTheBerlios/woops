package business.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import business.BusinessConstantes;


public class Formatage {
	/**
	 * convertit une date String en un objet DateDate
	 * @param String date 
	 * @return Date date
	 */
	public static Date stringToDate(String d) {
	    Date date = null;
	    
	    if (!Controleur.isVide(d) 
	            && Controleur.isInteger(d.replaceAll("/","")) 
	            && (d.replaceAll("/","")).length()<=8) {
	    
		    SimpleDateFormat dateFormat = new SimpleDateFormat(BusinessConstantes.DATE_FORMAT);		
			try {	
			    if (d.indexOf("/")<=0)
			        d = d.substring(0,2) + "/" + d.substring(2,4) + "/" + d.substring(4);
			    dateFormat.setLenient(false);
				date = dateFormat.parse(d);            
	        } catch (ParseException e) {
	        } catch (IndexOutOfBoundsException e) {	            
	        }
	    }
		return date;
	}

	/**
	 * convertit un String en un objet Integer
	 * @param String s 
	 * @return Integer
	 */
	public static Integer stringToInteger(String s) {
	    Integer ret = null;
	    if (!Controleur.isVide(s))
	        try {
                ret = Integer.valueOf(s);
            } catch (NumberFormatException e) {
            }
		return ret;
	}
	
    /**
     * convertit un String en un objet Integer
     * @param String s 
     * @return Integer
     */
    public static String integerToString(Integer i) {
        String ret = "";
        if (i!=null)
                ret = i.toString();

        return ret;
    }	
	
	/**
	 * convertit un objet Date en une date String 
	 * @param Date date 
	 * @return String date
	 */
	public static String dateToString(Date date) {
	
		String result = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat(BusinessConstantes.DATE_FORMAT);

		if (date != null) {
			result = dateFormat.format(date);
		}
	
		return result;
	}
	
	
	
	/**
	 * convertit un objet Double en String 
	 * @param Double d 
	 * @return String date
	 */
	public static String doubleToString(double d) {
	
		String result = "";
		
		result = (new Double(d)).toString();
	
		return result;
	}
	
	
	/**
	 * @param valeur
	 * @return
	 */
	public static Double stringToDouble(String s) throws NumberFormatException {
		Double ret = null;
	    if (!Controleur.isVide(s))
	    	try {
	    		ret = Double.valueOf(s);
	    	} catch (NumberFormatException e) {}
		return ret;
	}	
	

	/**
	 * Retourne un champs vide ("") si le String est null
	 * @param p_in chaine 
	 * @return String
	 */
	public static String notNull(String p_in) {
		String ret = p_in;
		if (ret==null) ret="";
		return ret;

	}


	/**
	 * Retourne un 0 si le Double est null
	 * @param p_in chaine 
	 * @return Double
	 */
	public static Double notNull(Double p_in) {
		Double ret = p_in;
		if (ret==null) ret= new Double (0);
		return ret;

	}



	/**
	 * Retourne le champs complet? devant du caractere jusqu'au nombre indiqu? 
	 * @param p_in chaine, char, nbr 
	 * @return String
	 */
	public static String completeChaine(String p_in, char c, int n) {

		
		String ret=p_in	;
		if (ret!=null){
			for (int i=0;i<n-p_in.length();i++){
				ret = c + ret;
			}
		}
		return ret;
	}


	/**
	 * Retourne le champs complet? apres du caractere jusqu'au nombre indiqu? 
	 * @param p_in chaine, char, nbr 
	 * @return String
	 */
	public static String termineChaine(String p_in, char c, int n) {

		
		String ret=p_in	;
		if (ret!=null){
			for (int i=0;i<n-p_in.length();i++){
				ret = ret + c;
			}
		}
		return ret;
	}



	/**
	 * Retourne la valeur arrondie
	 * @param double, nombre de decimal 
	 * @return double
	 */
	public static double arrondir(double p_in, int p_decimal) {
		
		double dec = Math.pow(10,p_decimal);
		double ret = p_in * dec;

		return Math.round(ret)/dec;
	}


	/**
	 * Remplace toutes les occurences d'une chaine par une autre
	 * @param p_in chaine, p_old chaines ? remplacer, p_new nouvelles chaines
	 * @return String 
	 */

	public static String remplaceChaine(String p_texte, String p_old, String p_new) {

		StringBuffer ret = new StringBuffer(p_texte);
		if (ret!=null){
			for (int i=0; i< ret.length(); ) {
				int deb = ret.toString().indexOf(p_old,i);
				if (deb<0) break;
				ret.replace(deb,deb+p_old.length(),p_new);
				i= deb + p_new.length();
			}
		}

		return ret.toString();
	}


	/**
	 * Remplace les @ (ou autre) par les parametre pass?s
	 * @param p_in chaine, p_params 
	 * @return String 
	 */
	public static String remplaceParams(String p_in, String[] p_params) {

		StringBuffer ret = new StringBuffer(p_in);
		if (ret!=null){
			String replace = "@";
			for (int i=0;i<p_params.length;i++) {
				String s1 = p_params[i];
				int deb = ret.toString().indexOf(replace);
				if (deb<0) break ;
				ret.replace(deb,deb+replace.length(),s1);
			}
		}		
		return ret.toString();
	}


	/**
	 * Retourne un tableau de valeur ? partir d'une liste de valeurs
	 * @return Vector
	 */
	public static Collection getListeValeurs(String p_chaine){

        Collection liste= new ArrayList();

		if (!Controleur.isVide(p_chaine)){
			int separ = -1;
			do {
				int curIndex = separ+1;
				separ = p_chaine.indexOf(";",curIndex);
				if (separ >0)
					liste.add(p_chaine.substring(curIndex,separ).trim());
				else
					liste.add(p_chaine.substring(curIndex).trim());
	
			}while (separ>0);
		}
		
		return liste;
	}
    

	public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar(); 
        calendar.setTime( new Date() ); 
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        System.out.println(calendar.getTime());
    }

	
}
