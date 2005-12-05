package view;

/**
 * @author Nicolas Ricard
 * 
 */
public interface PresentationConstantes {

	public static final String FORWARD_SUCCESS="success";
	public static final String FORWARD_ERROR="error";
	public static final String FORWARD_ACTION="action";
	public static final String FORWARD_BACK="back"; 
	public static final String FORWARD_INDEX="index"; 
    public static final String FORWARD_EDIT="edit";
    
	//Constantes de parametres de request
	public static final String PARAM_ACTION_SUBMIT="actionSubmit";
	public static final String PARAM_MODE="mode";
	public static final String PARAM_ACTIVITY_ID="activityId";
	
	//constantes relatives aux Styles
	public static final String STYLE_FOND1="fond1";
	public static final String STYLE_FOND2="fond2";
	
	//constantes de cl�s en session
	public static final String KEY_FORM="FORM";
	public static final String KEY_USER="USER";
	public static final String KEY_OLD_DEPENDANCES_KEYS="OLD_DEPENDANCES_KEYS";
	public static final String KEY_POSSIBLE_DEPENDANCES_OPTIONS="POSSIBLE_DEPENDANCES_OPTIONS";
	
	public static final String LIBELLE_OUI="O";
	public static final String LIBELLE_NON="N";
	
	//Constantes permettant de modifier l'�tat d'une activi�
    public static final String ACTIVITY_START = "start";
    public static final String ACTIVITY_FINISH = "finish";
	
}
