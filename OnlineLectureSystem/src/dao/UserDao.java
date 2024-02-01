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

	public UserVo login(List<Object> param) {
		String sql = " select * from JAVA_USER where MEM_ID = ? and MEM_PASS = ? ";

		return jdbc.selectOne(sql, param, UserVo.class);
	}

	public boolean signIdCheck(List<Object> param) {
		String sql = "SELECT MEM_ID FROM JAVA_USER WHERE MEM_ID = ?";
		if (jdbc.selectOne(sql, param) == null) {
			return false;
		}
		return true;
	}

	public void sign(List<Object> param) {
		String sql = "INSERT INTO JAVA_USER (MEM_ID, MEM_PASS, MEM_NAME)   VALUES(?, ?, ?) ";
		jdbc.update(sql, param);
		System.out.println("회원가입 완료");
	}
}
