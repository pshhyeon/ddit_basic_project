package vo;

import lombok.Data;

@Data
public class LectureVo {
	private int lecture_no;
	private int user_no;
	private String lecture_name;
	private String lecture_content;
	private int category_no;
	private int level_no;
	private int book_no;
	@Override
	public String toString() {
		return "LectureVo [lecture_no=" + lecture_no + ", user_no=" + user_no + ", lecture_name=" + lecture_name
				+ ", lecture_content=" + lecture_content + ", category_no=" + category_no + ", level_no=" + level_no
				+ ", book_no=" + book_no + "]";
	}
	public int getLecture_no() {
		return lecture_no;
	}
	public void setLecture_no(int lecture_no) {
		this.lecture_no = lecture_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getLecture_name() {
		return lecture_name;
	}
	public void setLecture_name(String lecture_name) {
		this.lecture_name = lecture_name;
	}
	public String getLecture_content() {
		return lecture_content;
	}
	public void setLecture_content(String lecture_content) {
		this.lecture_content = lecture_content;
	}
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	public int getLevel_no() {
		return level_no;
	}
	public void setLevel_no(int level_no) {
		this.level_no = level_no;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	
	
}
