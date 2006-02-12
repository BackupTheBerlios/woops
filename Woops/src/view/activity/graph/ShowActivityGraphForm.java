package view.activity.graph;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

/**
 * @author Simon REGGIANI
 */

public class ShowActivityGraphForm extends FWActionForm {
	private static final long serialVersionUID = -7623927860007234202L; /** Generated Serial ID */
	
	private String imageFilePath;

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	
	
}
