package dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.ReviewVo;

public class ReviewDao {
	private static ReviewDao instance = null;

	private ReviewDao() {
	}

	public static ReviewDao getInstance() {
		if (instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public void makingReview(List<Object> param) {
		String sql  = " INSERT INTO REVIEW (REVIEW_NO,\"CONTENT\",RATED,USER_NO,LECTURE_NO)\r\n" + 
				"VALUES((SELECT MAX(REVIEW_NO)+1 FROM REVIEW),?,?,?,?) ";
		jdbc.update(sql,param);

	}
	public List<ReviewVo> reviewlist(int userNo) {
		String sql =" SELECT REVIEW_NO AS REVIEW_NO,\r\n" + 
				"       CONTENT AS CONTENT,\r\n" + 
				"       RATED AS RATED,\r\n" + 
				"       TO_CHAR(REVIEW_DATE) AS REVIEW_DATE,\r\n" + 
				"       USER_NO AS USER_NO,\r\n" + 
				"       LECTURE_NO AS LECTURE_NO\r\n" + 
				"       \r\n" + 
				"FROM REVIEW WHERE USER_NO = "+userNo + " AND DELYN IS NULL ";
		   return jdbc.selectList(sql, ReviewVo.class);
		   
	}
	public int reviewUpdate(List<Object> param ) {
		
		String sql = " UPDATE REVIEW\r\n" + 
				"SET CONTENT = ? , RATED = ? \r\n " + 
				"WHERE USER_NO = ? AND LECTURE_NO = ? ";
		
		jdbc.update(sql,param);
		
		sql = " SELECT REVIEW_NO FROM REVIEW WHERE USER_NO =" +param.get(2)+ "AND LECTURE_NO = "+ param.get(3);
		
		
		ReviewVo review = (ReviewVo)jdbc.selectOne(sql,ReviewVo.class);
		
		return review.getReview_no();
	}
	
	public ReviewVo reviewChk(List<Object> param) {
		String sql = " SELECT REVIEW_NO FROM REVIEW WHERE USER_NO = ? AND LECTURE_NO = ? ";
		return jdbc.selectOne(sql, param, ReviewVo.class);
	}
	
	public ReviewVo reviewDetail(int reviewnum) { // 0205추가

	      String sql = " SELECT REVIEW_NO AS REVIEW_NO,\r\n" + 
	            "CONTENT AS CONTENT,\r\n" + 
	            "RATED AS RATED,\r\n" + 
	            "TO_CHAR(REVIEW_DATE) AS REVIEW_DATE,+ \r\n" + 
	            "USER_NO AS USER_NO,\r\n" + 
	            "LECTURE_NO AS LECTURE_NO \r\n" + 
	            "FROM REVIEW \r\n" + 
	            "WHERE REVIEW_NO =  " + reviewnum;
	      return jdbc.selectOne(sql,ReviewVo.class);
	   }
	 

	 public List<Map<String, Object>> lectureReview(List<Object> param) { // 0205 추가 00
			String sql =" SELECT REVIEW_NO AS REVIEW_NO,\r\n" + 
					"       \"CONTENT\" AS \"CONTENT\",\r\n" + 
					"       RATED AS RATED,\r\n" + 
					"       TO_CHAR(REVIEW_DATE) AS REVIEW_DATE\r\n" + 
					"FROM REVIEW\r\n" + 
					"WHERE LECTURE_NO = ? AND DELYN IS NULL ORDER BY REVIEW_NO";
			return jdbc.selectList(sql,param);
		}

	 public List<ReviewVo> adminReviewList(List<Object> param) {
		 String sql = "SELECT REVIEW_NO, CONTENT, RATED, TO_CHAR(REVIEW_DATE, 'YYYYMMDD') AS REVIEW_DATE, USER_NO, LECTURE_NO, DELYN\r\n" + 
					"FROM (SELECT ROWNUM AS RN, A.*\r\n" + 
					"        FROM (SELECT * FROM REVIEW ORDER BY REVIEW_NO) A)\r\n" + 
					"WHERE RN BETWEEN ? AND ? ORDER BY REVIEW_NO";
			return jdbc.selectList(sql, param, ReviewVo.class);
	 }
	 
	 public int getMaxReview() {
		 String sql = "SELECT MAX(ROWNUM) AS RN FROM (SELECT * FROM REVIEW)";
		 Map<String, Object> map = jdbc.selectOne(sql);
		 return ((BigDecimal) map.get("RN")).intValue();
	 }
	 
	 public Map<String, Object> adminReviewDetail(int sel) {
		 String sql = " SELECT R.REVIEW_NO AS REVIEW_NO, R.CONTENT AS CONTENT, R.RATED AS RATED, TO_CHAR(R.REVIEW_DATE) AS REVIEW_DATE, U.USER_NAME AS USER_NAME, R.LECTURE_NO AS LECTURE_NO, R.DELYN AS DELYN " + 
		 		" FROM REVIEW R, USER_ U WHERE R.USER_NO = U.USER_NO AND REVIEW_NO = " + sel;
		 return jdbc.selectOne(sql);
	 }
	 
	 public void reviewDelyn(List<Object> param) {
		 String sql = " UPDATE REVIEW SET DELYN = ? WHERE REVIEW_NO = ? ";
		 jdbc.update(sql,param);
		 sql = " UPDATE REPLY SET DELYN = ? WHERE REVIEW_NO = ? ";
		 jdbc.update(sql,param);
	 }
	 
		public void reviewDelete(List<Object> param) {
			String sql = " UPDATE REVIEW SET DELYN = 'Y'  WHERE REVIEW_NO= ? ";
			jdbc.update(sql, param);
		}

}