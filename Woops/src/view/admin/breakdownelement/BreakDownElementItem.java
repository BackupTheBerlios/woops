package view.admin.breakdownelement;

import java.util.Date;

import business.breakdownelement.BreakdownElementKind;

import com.cc.framework.common.DisplayObject;

public class BreakDownElementItem implements DisplayObject
{
	private	Integer	id;
	private String prefix;
	private String name;
	private String details;
	private Date startDate;
	private Date endDate;
	private BreakdownElementKind kind;
	
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

	public BreakdownElementKind getKind() {
		return kind;
	}

	public void setKind(BreakdownElementKind kind) {
		this.kind = kind;
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
