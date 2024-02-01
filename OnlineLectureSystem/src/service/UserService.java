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
		MainController.sessionStorage.put("user", dao.login(param, sel));
		if (MainController.sessionStorage.get("user") != null) {
			return true;
		}
		return false;
	}

	public boolean join(List<Object> param, int sel) {
		if (sel != 1 && sel != 2) {
			System.out.println("가입 유형을 다시 선택해주세요");
			return false;
		}

		UserVo user = dao.join(param, sel);
		if (user == null) {
			return false;
		}

		MainController.sessionStorage.put("user", user);
		if (sel == 1) {
			MainController.sessionStorage.put("user", View.USER_HOME);
		}
		if (sel == 2) {
			MainController.sessionStorage.put("user", View.LECTURE_HOME);
		}
		return true;
	}
}