package business.hibernate;

import java.util.Date;


public class HistorizedObject implements PersistentObject, Cloneable{
	private static final long serialVersionUID = -4486169491171535845L; /** Generated Serial ID */
	protected String userCreation;
    protected Date dateCreation;
    protected String userModification;    
    protected Date dateModification;
    
    
    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    public Date getDateModification() {
        return dateModification;
    }
    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }
    public String getUserCreation() {
        return userCreation;
    }
    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }
    public String getUserModification() {
        return userModification;
    }
    public void setUserModification(String userModification) {
        this.userModification = userModification;
    }    

    public void setId(Object id) {}   
    public Object getId() {return null;}


    /**
     * surcharge de la methode clone pour copier les objets
     */
    public Object clone()  {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace();
        }
        
        return o;
    }    
    
}
