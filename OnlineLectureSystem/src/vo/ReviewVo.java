package vo;


import lombok.Data;

@Data
public class ReviewVo {
	private int review_no;
	private String content;
	private String rated;
	private String review_date;
	private int user_no;
	private int lecture_no;
	
	
}
