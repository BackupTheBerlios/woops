package view;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Nicolas Ricard
 * 
 */
public class WoopsFormItem {

    private String couleurFond;
    
    
    public static void alterneCouleur(Collection liste){
        try {
            Iterator iter = liste.iterator();
            String couleur  = PresentationConstantes.STYLE_FOND1;
	        while (iter.hasNext()) {
	            WoopsFormItem element = (WoopsFormItem) iter.next();
	            element.setCouleurFond(couleur);
	            //alternance de la couleur de fond
	            couleur = (couleur.equals(PresentationConstantes.STYLE_FOND1))?
	                    PresentationConstantes.STYLE_FOND2:PresentationConstantes.STYLE_FOND1;
	                    
	        }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getCouleurFond() {
        return couleurFond;
    }
    public void setCouleurFond(String couleurFond) {
        this.couleurFond = couleurFond;
    }
}
