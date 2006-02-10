package view.admin.breakdownelement;

import com.cc.framework.common.DisplayObject;

public class BreakDownElementItem implements DisplayObject
{
	private	String id;
	private String name;
	private String details;
		
	public BreakDownElementItem()
	{
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
