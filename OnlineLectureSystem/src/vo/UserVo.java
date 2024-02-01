package vo;

import lombok.Data;

@Data
public class UserVo {
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private int mem_no;
	private String mem_manager;
	@Override
	public String toString() {
		return mem_name + "님 환영합니다 :)";
	}

	
}
