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
	public List<ReviewVo> reviewlist() {
		String sql =" SELECT REVIEW_NO AS REVIEW_NO,\r\n" + 
				"       CONTENT AS CONTENT,\r\n" + 
				"       RATED AS RATED,\r\n" + 
				"       TO_CHAR(REVIEW_DATE) AS REVIEW_DATE,\r\n" + 
				"       USER_NO AS USER_NO,\r\n" + 
				"       LECTURE_NO AS LECTURE_NO\r\n" + 
				"       \r\n" + 
				"FROM REVIEW ";
		   return jdbc.selectList(sql, ReviewVo.class);
		   
	}
	public void reviewUpdate(List<Object> param ) {
		
		String sql = " UPDATE REVIEW\r\n" + 
				"SET REVIEW_NO = (SELECT MAX(LECTURE_NO)+1 FROM REVIEW), CONTENT = ? , RATED = ?\r\n" + 
				"WHERE USER_NO = ?\r\n" + 
				"AND LECTURE_NO = ? ";
		
		jdbc.update(sql,param);
	}
	
	

}
