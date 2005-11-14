package view.chocolat;

import java.util.Collection;

import view.WoopsFormBean;

public class ListerChocolatForm extends WoopsFormBean {
    private Collection listeItems;
    static final long serialVersionUID = -3387516993124229948L;
    
	public Collection getListeItems() {
		return listeItems;
	}
	public void setListeItems(Collection listeItems) {
		this.listeItems = listeItems;
	}

}