package business.breakdownelement;

import java.util.Date;

import business.hibernate.HistorizedObject;

public class BreakdownElement extends HistorizedObject{	
	private static final long serialVersionUID = 2763309763407190586L; /** Generated Serial ID */
	private	Integer	id;
	private String prefix;
	private Date startDate;
	private Date endDate;
	private BreakdownElementKind kind;
	
	public BreakdownElement() {
		super();
		this.id = null;
		this.prefix = null;
		this.startDate = null;
		this.endDate = null;
		this.kind = null;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Object getId() {
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
}
