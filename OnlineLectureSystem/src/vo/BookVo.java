package vo;


import lombok.Data;

@Data
public class BookVo {
	private int book_no;
	private String book_name;
	private String book_content;
	private int category_no;
}
