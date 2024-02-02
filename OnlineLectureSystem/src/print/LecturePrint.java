package print;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class LecturePrint {
	
	public void lectureList(List<Map<String, Object>> alllectureList){
		for (Map<String, Object> map : alllectureList) {
			BigDecimal lectureNo = (BigDecimal) map.get("LECTURE_NO");
			String lectureName = (String) map.get("LECTURE_NAME");
			String lectureContent = (String) map.get("LECTURE_CONTENT");
			String userName = (String) map.get("USER_NAME");
			String levlName = (String) map.get("LEVEL_NAME");
			String bookName = (String) map.get("BOOK_NAME");
			String bookCategory = (String) map.get("CATEGORY_NAME");
			
			System.out.println(lectureNo.intValue() + "\t" + lectureName + "\t" + lectureContent + "\t" + userName);
			System.out.println(levlName + "\t" + bookName + "\t" + bookCategory);
		}
	}

}
