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
			case USER_JOIN:
				view = userJoin();
				break;
			default:
				break;
			}
		}
	}
//	commy and push 할때 로컬에서 보내야한다 브랜치는 shun으로

	
	private View userJoin() {
//		USER_NO, 
//		USER_ID,
//		USER_PASS,
//		USER_ADDRESS,
//		USER_HP,
//		USER_BIR,
//		USER_NAME,
//		JOIN_DATE,
//		DIVI_NO
		System.out.println("1. 일반회원가입");
		System.out.println("2. 강사회원가입");
		System.out.println("3. home");
		
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		if(sel==3) {
			return View.HOME;
		}
		System.out.println();
		System.out.println("회원가입을 진행합니다");
		String id = ScanUtil.nextLine("ID : ");
		String pass = ScanUtil.nextLine("PASS : ");
		String name = ScanUtil.nextLine("이름 : ");
		System.out.println("생년월일은 YYYYmmdd형식으로 입력해주세요!!");
		String bir = ScanUtil.nextLine("생년월일 : ");
		System.out.println("선택사항입니다. 작성하지 않을시 Enter를 눌러주세요");
		String address = ScanUtil.nextLine("주소 : ");
		System.out.println("선택사항입니다. 작성하지 않을시 Enter를 눌러주세요");
		String hp = ScanUtil.nextLine("핸드폰번호 : ");
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		param.add(address);
		param.add(hp);
		param.add(bir);
		param.add(name);
		boolean join = userService.join(param, sel);
		if(join) {
			return  (View)sessionStorage.get("page"); 
		}
		else {
			return View.USER_JOIN;
		}
	}

	private View login() {
		System.out.println("로그인을 시작합니다");
		String id = ScanUtil.nextLine("id : ");
		String pass = ScanUtil.nextLine("pass : ");
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		if (userService.login(param, (int) sessionStorage.get("login"))) {
			System.out.println("로그인");
			System.out.println((UserVo) sessionStorage.get("user"));
		} else {
			System.out.println("로그인 실패");
		}
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
			return View.USER_JOIN;
		case 5:
			return View.HOME;
		default:
			return View.HOME;
		}
	}
}
