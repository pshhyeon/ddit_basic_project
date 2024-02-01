package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.CartService;
import service.ProdService;
import service.UserService;
import util.ScanUtil;
import util.View;
import vo.ProdVo; // Vo 임포트
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
			default:
				break;
			}
		}
	}
	
	private void test() {
		System.out.println("커밋 테스트");
		System.out.println("브랜치 테스트");
		System.out.println("브랜치 테스트2");
		System.out.println("브랜치 테스트3");
		System.out.println("브랜치 테스트4");
		System.out.println("상현 받아줘~~");
		System.out.println("성후나 나 잘받았어 고마웡~");
		
	}

	

	private View home() {
		System.out.println("1. 로그인");
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1:
			return View.HOME;
		case 2:
			return View.HOME;
		default:
			return View.HOME;
		}
	}
}
