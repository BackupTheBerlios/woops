package view.admin.user;



import com.cc.framework.common.DisplayObject;


public class RoleItem implements DisplayObject{
	private	String code;
	private String name;

	public RoleItem() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String id) {
		this.code = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
