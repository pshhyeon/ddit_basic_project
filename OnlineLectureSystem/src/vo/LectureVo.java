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
	
	
}
