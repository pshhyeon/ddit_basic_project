package dao;

import java.util.List;

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
				"FROM REVIEW WHERE USER_NO = "+userNo;
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
	
	 public ReviewVo reviewDetail(int reviewnum) {
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


}