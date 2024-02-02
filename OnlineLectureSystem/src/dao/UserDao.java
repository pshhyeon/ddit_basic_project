package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;
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

	public UserVo login(List<Object> param, int sel) {
		String sql = " SELECT USER_NO, USER_ID, USER_PASS, USER_ADDRESS, USER_HP, TO_CHAR(USER_BIR,'YYYYMMDD'), USER_NAME, TO_CHAR(JOIN_DATE,'YYYYMMDD'), DIVI_NO FROM USER_  \r\n "
				+ " WHERE USER_ID = ? \r\n " + "   AND USER_PASS = ? \r\n "
				+ "   AND DIVI_NO = (SELECT DIVI_NO FROM USER_DIVI WHERE DIVI_NO = " + sel + " ) ";

<<<<<<< HEAD
	     
		
=======
>>>>>>> branch 'main' of https://github.com/pshhyeon/ddit_basic_project.git
		return jdbc.selectOne(sql, param, UserVo.class);
	}

	public Map<String, Object> joinChk(List<Object> param) {
		String sql = " SELECT USER_ID from  USER_ WHERE USER_ID = ? ";
		return jdbc.selectOne(sql, param);
	}

	public UserVo join(List<Object> param, int sel) {
		String sql = "  INSERT INTO USER_ (USER_NO, USER_ID, USER_PASS, USER_HP, USER_BIR, USER_NAME, JOIN_DATE, DIVI_NO ,USER_ADDRESS ) \r\n" + 
				" VALUES((SELECT NVL(MAX(USER_NO)+1, 1) FROM USER_), ?, ?, ?, TO_DATE(?,'YYYY/MM/DD'),?, SYSDATE, " + sel;
		
		if(param.get(param.size() - 1) == null) {
			sql += ", null ) ";
			param.remove(param.size() - 1);
		}else {
			sql  += " , ?) ";
		}
		
		
		jdbc.update(sql, param);

		sql = " SELECT * FROM  USER_ WHERE USER_ID = " + (String)param.get(0);
		return jdbc.selectOne(sql, UserVo.class);
	}

}


