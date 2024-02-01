package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.UserService;
import util.ScanUtil;
import util.View;
import vo.UserVo;

// 출력부분 클래스 상속
public class MainController {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	UserService userService = UserService.getInstance();

	public static void main(String[] args) {
		new MainController().start();
	}

	private void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case LOGIN:
				view = login();
				break;
			default:
				break;
			}
		}
	}
	
	private View login() {
		System.out.println("로그인을 시작합니다");
		String id = ScanUtil.nextLine("id : ");
		String pass = ScanUtil.nextLine("pass : ");
		UserVo user = (UserVo)sessionStorage.get("user");
		MainController.sessionStorage.put("user",user);
		List<UserVo> param = new ArrayList<UserVo>();
		param.add(id);
		param.add(pass);
		UserVo userVosel= userService.login(param, (int)sessionStorage.get("login"));
		//홈에서 sessionStorage.put해서 키 login 에 sel (1,2,3)을 넣어줌
		//UserVo userVosel= userService.login(param, (int)sessionStorage.get("login"));
		//여기서 userService.login에 param(id와pass)그리고 sel 값을 보내준다
		int usersel = (int)sessionStorage.get("login");

		return View.HOME;
	}

	private View home() {
		System.out.println("1. 일반회원 로그인");
		System.out.println("2. 강사 로그인");
		System.out.println("3. 관리자 로그인");
		System.out.println("4. 회원가입");
		System.out.println("5. home");
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		
		switch (sel) {
		case 1:
		case 2:
		case 3:
			sessionStorage.put("login", sel);
			return View.LOGIN;
		case 4:
			return View.HOME;
		case 5:
			return View.HOME;
		default:
			return View.HOME;
		}
	}
}
