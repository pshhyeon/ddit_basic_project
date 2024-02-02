package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class LectureDao {
	private static LectureDao instance = null;

	private LectureDao() {
	}

	public static LectureDao getInstance() {
		if (instance == null) {
			instance = new LectureDao();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();
	

	
	
	   public List<Map<String, Object>> lectureList() {
			String sql = " SELECT L.LECTURE_NO AS LECTURE_NO,\r\n" + 
					"       L.LECTURE_NAME AS LECTURE_NAME,\r\n" + 
					"       L.LECTURE_CONTENT AS LECTURE_CONTENT,\r\n" + 
					"       U.USER_NAME AS USER_NAME,\r\n" + 
					"       LE.LEVEL_NAME AS LEVEL_NAME,\r\n" + 
					"       B.BOOK_NAME AS BOOK_NAME,\r\n" + 
					"       BC.BOOKCATEGORY_NAME AS BOOKCATEGORY_NAME\r\n" + 
					" FROM LECTURE L , USER_ U , \"LEVEL\" LE , BOOK B , BOOK_CATEGORY BC\r\n" + 
					" WHERE L.USER_NO = U.USER_NO\r\n" + 
					" AND L.LEVEL_NO = LE.LEVEL_NO\r\n" + 
					" AND L.BOOK_NO = B.BOOK_NO\r\n" + 
					" AND B.BOOKCATEGORY_NO = BC.BOOKCATEGORY_NO ";
		      return jdbc.selectList(sql);
		   }
	   
	   
	   
	   public Map<String, Object> lectureDetail(List<Object> param) {
		      String sql = " SELECT L.LECTURE_NO AS LECTURE_NO,\r\n" + 
		      		"L.LECTURE_NAME AS LECTURE_NAME,\r\n" + 
		      		"L.LECTURE_CONTENT AS LECTURE_CONTENT,+ \r\n" + 
		      		"U.USER_NAME AS USER_NAME,\r\n" + 
		      		"LE.LEVEL_NAME AS LEVEL_NAME,\r\n" + 
		      		" B.BOOK_NAME AS BOOK_NAME, \r\n" + 
		      		"BC.BOOKCATEGORY_NAME AS BOOKCATEGORY_NAME\r\n" + 
		      		"FROM LECTURE L , USER_ U ,\"LEVEL\" LE , BOOK B , BOOK_CATEGORY BC\r\n" + 
		      		"WHERE L.USER_NO = U.USER_NO \r\n" + 
		      		"AND L.LEVEL_NO = LE.LEVEL_NO\r\n" + 
		      		"AND L.BOOK_NO = B.BOOK_NO\r\n" + 
		      		"AND B.BOOKCATEGORY_NO = BC.BOOKCATEGORY_NO\r\n" + 
		      		"AND L.LECTURE_NO = ? ";
		      return jdbc.selectOne(sql,param);
		   }

}
