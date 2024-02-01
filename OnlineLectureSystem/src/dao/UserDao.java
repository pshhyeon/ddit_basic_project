package dao;

import java.util.List;

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
		String sql = " SELECT * FROM USER_  \r\n " + 
				" WHERE USER_ID = ? \r\n " + 
				"   AND USER_PASS = ? \r\n " + 
				"   AND DIVI_NO = (SELECT DIVI_NO FROM USER_DIVI WHERE DIVI_NO = " + sel + " ) ";
		
		return jdbc.selectOne(sql, param, UserVo.class);
	}

}
