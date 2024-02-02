package dao;

import java.util.List;
import java.util.Map;

import controller.MainController;
import util.JDBCUtil;
import vo.LectureCategoryVo;
import vo.UserVo;

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
	
   public List<Map<String, Object>> lectureList() {
	   String sql = " SELECT L.LECTURE_NO AS LECTURE_NO,\r\n" + 
				"       L.LECTURE_NAME AS LECTURE_NAME,\r\n" + 
				"       L.LECTURE_CONTENT AS LECTURE_CONTENT,\r\n" + 
				"       U.USER_NAME AS USER_NAME,\r\n" + 
				"       LE.LEVEL_NAME AS LEVEL_NAME,\r\n" + 
				"       B.BOOK_NAME AS BOOK_NAME,\r\n" + 
				"       LC.CATEGORY_NAME AS CATEGORY_NAME\r\n" + 
				" FROM LECTURE L , USER_ U , \"LEVEL\" LE , BOOK B , CATEGORY LC\r\n" + 
				" WHERE L.USER_NO = U.USER_NO\r\n" + 
				" AND L.LEVEL_NO = LE.LEVEL_NO\r\n" + 
				" AND L.BOOK_NO = B.BOOK_NO\r\n" + 
				" AND LC.CATEGORY_NO = L.CATEGORY_NO ";
		
		UserVo user = (UserVo) MainController.sessionStorage.get("user");

		if (user.getDivi_no() == 2) {
			sql += " AND U.USER_NO = " + user.getUser_no();
		}
		return jdbc.selectList(sql);
	}
   
   
   // 나중에 리스트 함수 정리하기
   public List<Map<String, Object>> lectureList(List<Object> param, int sel) {
	   UserVo user = (UserVo) MainController.sessionStorage.get("user");
	   String sql = " SELECT L.LECTURE_NO AS LECTURE_NO,\r\n" + 
				"       L.LECTURE_NAME AS LECTURE_NAME,\r\n" + 
				"       L.LECTURE_CONTENT AS LECTURE_CONTENT,\r\n" + 
				"       U.USER_NAME AS USER_NAME,\r\n" + 
				"       LE.LEVEL_NAME AS LEVEL_NAME,\r\n" + 
				"       B.BOOK_NAME AS BOOK_NAME,\r\n" + 
				"       LC.CATEGORY_NAME AS CATEGORY_NAME\r\n" + 
				" FROM LECTURE L , USER_ U , \"LEVEL\" LE , BOOK B , CATEGORY LC\r\n" + 
				" WHERE L.USER_NO = U.USER_NO\r\n" + 
				" AND L.LEVEL_NO = LE.LEVEL_NO\r\n" + 
				" AND L.BOOK_NO = B.BOOK_NO\r\n" + 
				" AND LC.CATEGORY_NO = L.CATEGORY_NO ";
		
		
		if (sel == 1 || sel ==3) {
			sql += " AND L.LECTURE_NAME like '%' || ? || '%' ";
			if(sel == 3) {
				sql+= " AND ";
			}
		}
		if (sel == 2 || sel == 3) {
			sql += " LC.CATEGORY_NO like '%' || ? || '%' ";
		}

		if (user.getDivi_no() == 2) {
			sql += " AND U.USER_NO = " + user.getUser_no();
		}

		return jdbc.selectList(sql, param);
	}
   
   public List<LectureCategoryVo> lectureCtegoryList() {
	   String sql = " select * from Category ";
	   
	   return jdbc.selectList(sql, LectureCategoryVo.class);
   }
   
   
}
