package jdbc.entity.vo;

/**
 * extjs 测试model 使用
 * @author my computer
 *
 */
public class CompanyVo {

	private String name;
	private String addr;
	private String tel;
	private boolean isMarket;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public boolean isMarket() {
		return isMarket;
	}

	public void setMarket(boolean isMarket) {
		this.isMarket = isMarket;
	}

}
