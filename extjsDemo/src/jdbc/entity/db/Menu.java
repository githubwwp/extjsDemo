package jdbc.entity.db;

public class Menu {

	private String menuId;

	private String pmenuId;

	private String menuName;

	private String menuUrl;

	private Byte leaf;

	private Byte level;

	private String menuRemark;

	private String order;

	private Byte used;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getPmenuId() {
		return pmenuId;
	}

	public void setPmenuId(String pmenuId) {
		this.pmenuId = pmenuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Byte getLeaf() {
		return leaf;
	}

	public void setLeaf(Byte leaf) {
		this.leaf = leaf;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public String getMenuRemark() {
		return menuRemark;
	}

	public void setMenuRemark(String menuRemark) {
		this.menuRemark = menuRemark;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Byte getUsed() {
		return used;
	}

	public void setUsed(Byte used) {
		this.used = used;
	}

}