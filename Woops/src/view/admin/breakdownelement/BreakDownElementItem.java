package view.admin.breakdownelement;

import java.util.Date;

import business.breakdownelement.BreakdownElementKind;

import com.cc.framework.common.DisplayObject;

public class BreakDownElementItem implements DisplayObject
{
	private	Integer	id;
	private String prefix;
	private String name;
	private Date startDate;
	private Date endDate;
	private String kind;
	
	public BreakDownElementItem()
	{
		super();
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
