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
	
}
