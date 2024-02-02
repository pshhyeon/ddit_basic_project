package vo;

import lombok.Data;

@Data
public class MyHomeVo {
	private int user_no;
	private int lecture_no;
	private String lecture_start;
	private String lecture_finish;
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getLecture_no() {
		return lecture_no;
	}
	public void setLecture_no(int lecture_no) {
		this.lecture_no = lecture_no;
	}
	public String getLecture_start() {
		return lecture_start;
	}
	public void setLecture_start(String lecture_start) {
		this.lecture_start = lecture_start;
	}
	public String getLecture_finish() {
		return lecture_finish;
	}
	public void setLecture_finish(String lecture_finish) {
		this.lecture_finish = lecture_finish;
	}
	
}
