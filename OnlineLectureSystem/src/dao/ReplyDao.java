package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class ReplyDao {
	private static ReplyDao instance = null;

	private ReplyDao() {
	}

	public static ReplyDao getInstance() {
		if (instance == null) {
			instance = new ReplyDao();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public void replyInsert(List<Object> param) {
		String sql = " INSERT INTO REPLY (REPLY_CONTENT , REVIEW_NO , USER_NO)\r\n" + "VALUES(?,?,?) ";
		jdbc.update(sql, param);

	}

	public List<Map<String, Object>> replyList(List<Object> param) {
		String sql = " SELECT REPLY_CONTENT AS REPLY_CONTENT,\r\n" + " TO_CHAR(REPLY_DATE) AS REPLY_DATE,\r\n"
				+ "       REVIEW_NO AS REVIEW_NO\r\n" + "FROM REPLY\r\n" + "WHERE USER_NO = ? ";
		return jdbc.selectList(sql, param);
	}

}
