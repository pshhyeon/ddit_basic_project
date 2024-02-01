package service;

import java.util.List;

import controller.MainController;
import dao.UserDao;
import util.View;
import vo.UserVo;

public class UserService {
	private static UserService instance = null;

	private UserService() {
	}

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	UserDao userDao = UserDao.getInstance();

	public boolean login(List<Object> param, int mem) {
		UserVo user = userDao.login(param);
		if (user == null) {
			return false;
		}
		
		if(!(user.getMem_manager() == null) && mem == 1) {
			boolean chk = true;
			MainController.sessionStorage.put("page", View.ADMIN_HOME);
			MainController.sessionStorage.put("admin", chk);
		}
		MainController.sessionStorage.put("user", user);
		MainController.sessionStorage.remove("login");
		return true;
	}
	
	public boolean signIdCheck(List<Object> param) {
		return userDao.signIdCheck(param);
	}

	public void sign(List<Object> param) {
		userDao.sign(param);
	}

}
