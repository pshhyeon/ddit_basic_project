package service;

import java.util.List;

import controller.MainController;
import dao.UserDao;
import util.ScanUtil;
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
			switch (user.getDivi_no()) {
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

	public View delAcount(String pass) {
		UserVo user = (UserVo) MainController.sessionStorage.get("user");

		if (!user.getUser_pass().equals(pass)) {
			String input = pass;
			while (true) {
				System.out.println("비밀번호가 올바르지 않습니다.\n 1. 재입력, 2. 취소");
				int sel = ScanUtil.menu();
				if (sel == 1) {
					input = ScanUtil.nextLine("비밀번호 >>");
					if (user.getUser_pass().equals(input)) {
						dao.delAcount(user.getUser_id());
						MainController.sessionStorage.clear();
						return View.HOME;
					}
				} else if (sel == 2) {
					System.out.println("회원탈퇴를 취소하였습니다");
					break;
				} else {
					System.out.println("메뉴를 다시 선택해주세요.");
				}
			}
			return (View) MainController.sessionStorage.get("page");
		}
		dao.delAcount(user.getUser_id());
		MainController.sessionStorage.clear();
		return View.HOME;
	}

}
