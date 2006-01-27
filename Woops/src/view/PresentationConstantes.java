package view;

/**
 * @author Nicolas Ricard
 * 
 */
public interface PresentationConstantes {

	public static final String FORWARD_SUCCESS="success";
	public static final String FORWARD_ERROR="error";
	public static final String FORWARD_ACTION="action";
	public static final String FORWARD_SUPPRIMER="supprimer";	
	public static final String FORWARD_CREER="creer";
	public static final String FORWARD_RETOUR="retour";
    public static final String FORWARD_DECONNECT="deconnect";
    public static final String FORWARD_LOGOUT="logout"; 
    public static final String FORWARD_EDIT="edit";
    public static final String FORWARD_DRILLDOWN="drillDown";
    public static final String FORWARD_BACK="back"; 
	public static final String FORWARD_INDEX="index"; 
	public static final String FORWARD_NOSESSION="nosession";
	
	
    public static final String FORWARD_NEXT="next"; 
    public static final String FORWARD_PREVIOUS="previous"; 
    public static final String FORWARD_FINISH="finish"; 
    
	

	//Constantes de parametres de request
	public static final String PARAM_ACTION_SUBMIT="actionSubmit";
	public static final String PARAM_MODE="mode";
	public static final String PARAM_ACTIVITY_ID="activityId";

	
	//constantes relatives aux Styles
	public static final String STYLE_FOND1="fond1";
	public static final String STYLE_FOND2="fond2";
	
	//constantes de cl?s en session
	public static final String KEY_FORM="FORM";
	public static final String KEY_USER="USER";
	public static final String KEY_OLD_DEPENDANCES_KEYS="OLD_DEPENDANCES_KEYS";
	public static final String KEY_POSSIBLE_DEPENDANCES_OPTIONS="POSSIBLE_DEPENDANCES_OPTIONS";
	public static final String KEY_DEPENDANCES_LIST = "KEY_DEPENDANCES_LIST";
	public static final String KEY_DEPENDANCES_LIST_MNGR = "KEY_DEPENDANCES_LIST_MNGR";
	public static final String KEY_ACTIVITY="ACTIVITY";
	public static final String KEY_ACTIVITIES_MAP="ACTIVITIES_MAP";
	
	public static final String LIBELLE_OUI="O";
	public static final String LIBELLE_NON="N";
	
	//Constantes permettant de modifier l'?tat d'une activi?
    public static final String ACTIVITY_START = "start";
    public static final String ACTIVITY_FINISH = "finish";
    
    //Constantes permettant de connaitre le mode d'un formulaire ( create ou update )
    public static final String INSERT_MODE = "insert_mode";
    public static final String UPDATE_MODE = "update_mode";
    
    // Constantes forward pour admin
    public static final String FORWARD_ADMIN="admin";
	
}
