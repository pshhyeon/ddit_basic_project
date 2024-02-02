package vo;

import lombok.Data;

@Data
public class UserVo {
	private int user_no;
	private String user_id;
	private String user_pass;
	private String user_address;
	private String user_hp;
	private String user_bir;
	private String user_name;
	private String join_date;
	private int divi_no;
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_hp() {
		return user_hp;
	}
	public void setUser_hp(String user_hp) {
		this.user_hp = user_hp;
	}
	public String getUser_bir() {
		return user_bir;
	}
	public void setUser_bir(String user_bir) {
		this.user_bir = user_bir;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public int getDivi_no() {
		return divi_no;
	}
	public void setDivi_no(int divi_no) {
		this.divi_no = divi_no;
	}

//	@Override
//	public String toString() {
//		return mem_name + "님 환영합니다 :)";
//	}

}
