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
//	@Override
//	public String toString() {
//		return mem_name + "님 환영합니다 :)";
//	}

}
