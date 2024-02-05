package dao;

import java.math.BigDecimal;
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
   
   public int getMaxPage() {
		String sql = "SELECT MAX(LECTURE_NO) AS N FROM LECTURE";

		Map<String, Object> maxNum = jdbc.selectOne(sql);
		BigDecimal n = (BigDecimal)maxNum.get("N");
		return n.intValue();
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
		
		
		if (sel == 1 ) {
			sql += " AND L.LECTURE_NAME like '%' || ? || '%' ";
			if(sel == 3) {
				sql+= " AND ";
			}
		}
		else if (sel == 2 ) {
			sql += " AND LC.CATEGORY_NO like '%' || ? || '%' ";//AND

		}
		else if(sel == 3) 
		{
			sql += "AND (L.LECTURE_NAME like '%' || ? || '%' OR LC.CATEGORY_NAME like '%' || ? || '%')";
		}

		if (user.getDivi_no() == 2) {
			sql += " AND U.USER_NO = " + user.getUser_no();
		}

		return jdbc.selectList(sql, param);
	}
   
   //OVERIDING
   public List<Map<String, Object>> lectureList(List<Object> param) {
	   UserVo user = (UserVo) MainController.sessionStorage.get("user");
	   String sql = " SELECT *\r\n" + 
	   		"FROM (\r\n" + 
	   		"  SELECT ROWNUM AS RN, A.*\r\n" + 
	   		"  FROM (\r\n" + 
	   		"    SELECT L.LECTURE_NO AS LECTURE_NO\r\n" + 
	   		"          ,L.LECTURE_NAME AS LECTURE_NAME\r\n" + 
	   		"          ,L.LECTURE_CONTENT AS LECTURE_CONTENT\r\n" + 
	   		"          ,U.USER_NAME AS USER_NAME\r\n" + 
	   		"          ,LE.LEVEL_NAME AS LEVEL_NAME\r\n" + 
	   		"          ,B.BOOK_NAME AS BOOK_NAME\r\n" + 
	   		"          ,C.CATEGORY_NAME AS CATEGORY_NAME\r\n" + 
	   		"     FROM LECTURE L, USER_ U, \"LEVEL\" LE, BOOK B, CATEGORY C\r\n" + 
	   		"     WHERE L.USER_NO = U.USER_NO\r\n" + 
	   		"       AND L.LEVEL_NO = LE.LEVEL_NO\r\n" + 
	   		"       AND L.BOOK_NO = B.BOOK_NO\r\n" + 
	   		"       AND C.CATEGORY_NO = L.CATEGORY_NO\r\n" + 
	   		"     ORDER BY LECTURE_NO\r\n" + 
	   		"  ) A\r\n" + 
	   		")\r\n" + 
	   		"WHERE LECTURE_NO BETWEEN ? AND ? ";	   
	  
	   
	   return jdbc.selectList(sql, param);
   }
   
   public List<LectureCategoryVo> lectureCtegoryList() {
	   String sql = " select * from Category ";
	   
	   return jdbc.selectList(sql, LectureCategoryVo.class);
   }
   
   // 수강신청 취소
   public void lectureCancel(List<Object> param) {
		String sql = " UPDATE MYHOME\r\n" + "SET LECTURE_FINISH = SYSDATE \r\n" + "WHERE USER_NO = ?\r\n"
				+ "AND LECTURE_NO = ? ";
		jdbc.update(sql, param);

	}
   
   // 강사 강의 등록
   public void lectureInsert(List<Object> param) {
		String sql = " INSERT INTO LECTURE(LECTURE_NO , USER_NO ," + " LECTURE_NAME , LECTURE_CONTENT , CATEGORY_NO , "
				+ "LEVEL_NO ,BOOK_NO)\r\n" + "VALUES ((SELECT MAX(LECTURE_NO)+1 FROM LECTURE),?,?,?,?,?,?) ";
		jdbc.update(sql, param);

	}
   
   // 수강 신청
   public void lectureApply(List<Object> param) {
		String sql = " INSERT INTO MYHOME(USER_NO, LECTURE_NO) VALUES(?, ?) ";
		jdbc.update(sql, param);

	}
	
   // 수강신청 리스트
	public List<Map<String, Object>> lectureApplyList(List<Object> param) {
		String sql =" SELECT L.LECTURE_NAME AS LECTURE_NAME,\r\n" + 
				"       C.CATEGORY_NAME AS CATEGORY_NAME,\r\n" + 
				"       LE.LEVEL_NAME AS LEVEL_NAME,\r\n" + 
				"       TO_CHAR(M.LECTURE_START) AS LECTURE_START, \r\n" + 
				"       TO_CHAR(L.LECTURE_NO) AS LECTURE_NO\r\n" + 
				"FROM MYHOME M , USER_ U , LECTURE L , \"CATEGORY\" C , \"LEVEL\" LE\r\n" + 
				"WHERE M.USER_NO = U.USER_NO\r\n" + 
				"AND M.LECTURE_NO = L.LECTURE_NO\r\n" + 
				"AND C.CATEGORY_NO = L.CATEGORY_NO\r\n" + 
				"AND LE.LEVEL_NO = L.LEVEL_NO\r\n" + 
				"AND M.USER_NO = ? AND M.LECTURE_FINISH IS NULL";
		return jdbc.selectList(sql,param);
	}
	
	// 과거 수강신청 내역
	public List<Map<String, Object>> pastlectureApplyList(List<Object> param) {
		//강의번호 추가 및 순서변경2024-02-05
		String sql = " SELECT U.USER_NAME AS USER_NAME,L.LECTURE_NAME AS LECTURE_NAME,\r\n" + 
				" C.CATEGORY_NAME AS CATEGORY_NAME,LE.LEVEL_NAME AS LEVEL_NAME,\r\n" + 
				" TO_CHAR(M.LECTURE_START) AS LECTURE_START,\r\n" + 
				" TO_CHAR(M.LECTURE_FINISH) AS LECTURE_FINISH,\r\n" + 
				" L.LECTURE_NO AS LECTURE_NO\r\n" + 
				" FROM MYHOME M , USER_ U , LECTURE L , \"CATEGORY\" C , \"LEVEL\" LE\r\n" + 
				" WHERE M.USER_NO = U.USER_NO AND M.LECTURE_NO = L.LECTURE_NO\r\n" + 
				" AND C.CATEGORY_NO = L.CATEGORY_NO AND LE.LEVEL_NO = L.LEVEL_NO\r\n" + 
				" AND LECTURE_FINISH IS NOT NULL \r\n" + 
				" AND M.USER_NO = ? ";
		return jdbc.selectList(sql, param);
	}
   
}