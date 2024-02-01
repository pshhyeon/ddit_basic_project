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


}
