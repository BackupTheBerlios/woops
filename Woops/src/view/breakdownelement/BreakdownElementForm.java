package view.breakdownelement;

import com.cc.framework.adapter.struts.FWActionForm;

public class BreakdownElementForm extends FWActionForm {
	private static final long serialVersionUID = -1465268217240508643L; /** Generated Serial ID */
	private String bdeId;

	public String getBdeId() {
		return bdeId;
	}

	public void setBdeId(String bdeId) {
		this.bdeId = bdeId;
	}
}
