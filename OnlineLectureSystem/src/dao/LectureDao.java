package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.LectureVo;

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
		String sql = " SELECT L.LECTURE_NO AS LECTURE_NO,\r\n" + "L.LECTURE_NAME AS LECTURE_NAME,\r\n"
				+ "L.LECTURE_CONTENT AS LECTURE_CONTENT,+ \r\n" + "U.USER_NAME AS USER_NAME,\r\n"
				+ "LE.LEVEL_NAME AS LEVEL_NAME,\r\n" + " B.BOOK_NAME AS BOOK_NAME, \r\n"
				+ "BC.BOOKCATEGORY_NAME AS BOOKCATEGORY_NAME\r\n"
				+ "FROM LECTURE L , USER_ U ,\"LEVEL\" LE , BOOK B , BOOK_CATEGORY BC\r\n"
				+ "WHERE L.USER_NO = U.USER_NO \r\n" + "AND L.LEVEL_NO = LE.LEVEL_NO\r\n"
				+ "AND L.BOOK_NO = B.BOOK_NO\r\n" + "AND B.BOOKCATEGORY_NO = BC.BOOKCATEGORY_NO\r\n"
				+ "AND L.LECTURE_NO = ? ";
		return jdbc.selectOne(sql, param);
	}

	public List<Map<String, Object>> lectureList() {
		String sql = " SELECT L.LECTURE_NO AS LECTURE_NO, L.LECTURE_NAME AS LECTURE_NAME,\r\n"
				+ "L.LECTURE_CONTENT AS LECTURE_CONTENT, U.USER_NAME AS USER_NAME,\r\n"
				+ "LE.LEVEL_NAME AS LEVEL_NAME, B.BOOK_NAME AS BOOK_NAME,\r\n"
				+ "BC.BOOKCATEGORY_NAME AS BOOKCATEGORY_NAME\r\n"
				+ "FROM LECTURE L , USER_ U , \"LEVEL\" LE , BOOK B , BOOK_CATEGORY BC\r\n"
				+ "WHERE L.USER_NO = U.USER_NO AND L.LEVEL_NO = LE.LEVEL_NO\r\n"
				+ "AND L.BOOK_NO = B.BOOK_NO AND B.BOOKCATEGORY_NO = BC.BOOKCATEGORY_NO\r\n" + "ORDER BY L.LECTURE_NO ";
		return jdbc.selectList(sql);
	}

	public void lectureApply(List<Object> param) {
		String sql = " INSERT INTO MYHOME(USER_NO, LECTURE_NO) VALUES(?, ?) ";
		jdbc.update(sql, param);

	}

	public void lectureCancel(List<Object> param) {
		String sql = " UPDATE MYHOME\r\n" + "SET LECTURE_FINISH = NULL\r\n" + "WHERE USER_NO = ?\r\n"
				+ "AND LECTURE_NO = ? ";
		jdbc.update(sql, param);

	}

	public List<Map<String, Object>> lectureApplyList(List<Object> param) {
		String sql = " SELECT U.USER_NAME AS USER_NAME, \r\n" + "       L.LECTURE_NAME AS LECTURE_NAME,\r\n"
				+ "       C.CATEGORY_NAME AS CATEGORY_NAME,\r\n" + "       LE.LEVEL_NAME AS LEVEL_NAME,\r\n"
				+ "       TO_CHAR(M.LECTURE_START) AS LECTURE_START\r\n" + "     \r\n"
				+ "FROM MYHOME M , USER_ U , LECTURE L , \"CATEGORY\" C , \"LEVEL\" LE\r\n"
				+ "WHERE M.USER_NO = U.USER_NO\r\n" + "AND M.LECTURE_NO = L.LECTURE_NO\r\n"
				+ "AND C.CATEGORY_NO = L.CATEGORY_NO\r\n" + "AND LE.LEVEL_NO = L.LEVEL_NO\r\n"
				+ "AND LECTURE_FINISH IS NULL\r\n" + "AND M.USER_NO = ? ";
		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> pastlectureApplyList(List<Object> param) {
		String sql = " SELECT U.USER_NAME AS USER_NAME, \r\n" + "       L.LECTURE_NAME AS LECTURE_NAME,\r\n"
				+ "       C.CATEGORY_NAME AS CATEGORY_NAME,\r\n" + "       LE.LEVEL_NAME AS LEVEL_NAME,\r\n"
				+ "       TO_CHAR(M.LECTURE_START) AS LECTURE_START,\r\n"
				+ "       TO_CHAR(M.LECTURE_FINISH) AS LECTURE_FINISH\r\n"
				+ "FROM MYHOME M , USER_ U , LECTURE L , \"CATEGORY\" C , \"LEVEL\" LE\r\n"
				+ "WHERE M.USER_NO = U.USER_NO\r\n" + "AND M.LECTURE_NO = L.LECTURE_NO\r\n"
				+ "AND C.CATEGORY_NO = L.CATEGORY_NO\r\n" + "AND LE.LEVEL_NO = L.LEVEL_NO\r\n"
				+ "AND LECTURE_FINISH IS NOT NULL\r\n" + "AND M.USER_NO = ? ";
		return jdbc.selectList(sql, param);
	}

	public void lectureInsert(List<Object> param) {
		String sql = " INSERT INTO LECTURE(LECTURE_NO , USER_NO ," + " LECTURE_NAME , LECTURE_CONTENT , CATEGORY_NO , "
				+ "LEVEL_NO ,BOOK_NO)\r\n" + "VALUES ((SELECT MAX(LECTURE_NO)+1 FROM LECTURE),?,?,?,?,?,?) ";
		jdbc.update(sql, param);

	}

}