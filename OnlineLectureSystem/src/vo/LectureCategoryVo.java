package vo;

import lombok.Data;

@Data
public class LectureCategoryVo {
	private int category_no;
	private String category_name;
	
	// 다시 정의
	@Override
	public String toString() {
		return "LectureCategoryVo [category_no=" + category_no + ", category_name=" + category_name + "]";
	}
	
	
}