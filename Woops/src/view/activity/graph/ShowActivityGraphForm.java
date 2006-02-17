package view.activity.graph;

import com.cc.framework.adapter.struts.FWActionForm;

/**
 * @author Simon REGGIANI
 */

public class ShowActivityGraphForm extends FWActionForm {
	
	private String imageFilePath;

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	
	
}
