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

	UserDao dao = UserDao.getInstance();

	public boolean login(List<Object> param, int sel) {
		UserVo user = dao.login(param, sel);
		MainController.sessionStorage.put("user", user);
		if (user != null) {
			switch(user.getDivi_no()){
				case 1:
					MainController.sessionStorage.put("page", View.MEM_HOME);
					break;
				case 2:
					MainController.sessionStorage.put("page", View.LECTURER_HOME);
					break;
				case 3:
					MainController.sessionStorage.put("page", View.ADMIN_HOME);
					break;
			}
			
			return true;
		}
		return false;
	}

	public boolean joinChk(List<Object> param) {
		if (dao.joinChk(param) == null) {
			return true;
		}
		System.out.println("중복된 ID입니다");
		return false;
	}

	public boolean join(List<Object> param, int sel) {
		UserVo user = dao.join(param, sel);

		MainController.sessionStorage.put("user", user);
		if (sel == 1) {
			MainController.sessionStorage.put("user", View.MEM_HOME);
		}
		if (sel == 2) {
			MainController.sessionStorage.put("user", View.LECTURER_HOME);
		}
		return true;
	}
}