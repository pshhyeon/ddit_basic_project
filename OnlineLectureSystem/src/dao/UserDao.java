package dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import controller.MainController;
import util.JDBCUtil;
import vo.MyHomeVo;
import vo.UserVo;

public class UserDao {

	private static UserDao instance = null;

	private UserDao() {
	}

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public List<MyHomeVo> myhomeinput() {
		UserVo user = (UserVo)MainController.sessionStorage.get("user");
		String sql = " SELECT USER_NO AS USER_NO,\r\n" + 
				"       LECTURE_NO AS LECTURE_NO,\r\n" + 
				"       TO_CHAR(LECTURE_START) AS LECTURE_START,\r\n" + 
				"       TO_CHAR(LECTURE_FINISH) AS LECTURE_FINISH\r\n" + 
				"FROM MYHOME WHERE USER_NO = " + user.getUser_no();
		return jdbc.selectList(sql, MyHomeVo.class);
	}

	public UserVo login(List<Object> param, int sel) {
		String sql = " SELECT USER_NO, USER_ID, USER_PASS, USER_ADDRESS, USER_HP, TO_CHAR(USER_BIR,'YYYYMMDD'), USER_NAME, TO_CHAR(JOIN_DATE,'YYYYMMDD'), DIVI_NO FROM USER_  \r\n "
				+ " WHERE USER_ID = ? \r\n " + "   AND USER_PASS = ? \r\n "
				+ "   AND DIVI_NO = (SELECT DIVI_NO FROM USER_DIVI WHERE DIVI_NO = " + sel + " ) "
				+ " AND DELYN IS NULL ";

		return jdbc.selectOne(sql, param, UserVo.class);
	}

	public Map<String, Object> joinChk(List<Object> param) {
		String sql = " SELECT USER_ID from  USER_ WHERE USER_ID = ? ";
		return jdbc.selectOne(sql, param);
	}

	public UserVo join(List<Object> param, int sel) {
		String sql = "  INSERT INTO USER_ (USER_NO, USER_ID, USER_PASS, USER_HP, USER_BIR, USER_NAME, JOIN_DATE, DIVI_NO ,USER_ADDRESS ) \r\n"
				+ " VALUES((SELECT NVL(MAX(USER_NO)+1, 1) FROM USER_), ?, ?, ?, TO_DATE(?,'YYYY/MM/DD'),?, SYSDATE, "
				+ sel;

		if (param.get(param.size() - 1) == null) {
			sql += ", null ) ";
			param.remove(param.size() - 1);
		} else {
			sql += " , ?) ";
		}

		jdbc.update(sql, param);

		sql = " SELECT USER_NO, USER_ID, USER_PASS, USER_ADDRESS, USER_HP, TO_CHAR(USER_BIR,'YYYYMMDD'), USER_NAME, TO_CHAR(JOIN_DATE,'YYYYMMDD'), DIVI_NO, DELYN FROM  USER_ WHERE USER_ID = '" + (String)param.get(0) + "' ";
		return jdbc.selectOne(sql, UserVo.class);
	}

	public void delAcount(String id) {
		String sql = " UPDATE  USER_  SET DELYN = 'Y' WHERE USER_ID = '" + id + "'";

		jdbc.update(sql);
	}
	
	public List<UserVo> memList(List<Object> param, int sel) {
		String sql = "SELECT *\r\n" + 
				" FROM (SELECT ROWNUM AS RN, A.* \r\n " + 
				"        FROM (SELECT USER_NO, USER_ID, USER_PASS, USER_ADDRESS, USER_HP, TO_CHAR(USER_BIR,'YYYYMMDD') AS USER_BIR, USER_NAME, TO_CHAR(JOIN_DATE,'YYYYMMDD') AS JOIN_DATE, DIVI_NO, DELYN \r\n "  + 
				"                FROM USER_  \r\n " + 
				"               WHERE DIVI_NO = "+ sel+ " AND DELYN IS NULL ) A)\r\n " + 
				" WHERE RN BETWEEN ? AND ? ";
		return jdbc.selectList(sql, param, UserVo.class);
	}
	
	
	
	public int getMaxMem(int sel) {
		String sql = "SELECT MAX(ROWNUM) AS RN FROM (SELECT * FROM USER_ WHERE DIVI_NO = "+sel+")";
		Map<String, Object> map = jdbc.selectOne(sql);
		return ((BigDecimal) map.get("RN")).intValue();
	}
	
	public UserVo memDetail(int memNo) {
		String sql= " SELECT USER_NO, USER_ID, USER_PASS, USER_ADDRESS, USER_HP, TO_CHAR(USER_BIR, 'YYYYMMDD') AS USER_BIR, USER_NAME, TO_CHAR(JOIN_DATE, 'YYYYMMDD') AS JOIN_DATE, DIVI_NO, DELYN FROM USER_ WHERE USER_NO  = " + memNo;
		
		return jdbc.selectOne(sql, UserVo.class);
	}
	
	public void memDelyn(List<Object> param) {
		String sql = " UPDATE USER_ SET DELYN = ? WHERE USER_ID = ? ";
		
		jdbc.update(sql, param);		
	}

}