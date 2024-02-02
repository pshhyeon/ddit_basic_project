package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.LectureService;
import service.UserService;
import util.ScanUtil;
import util.View;
import vo.UserVo;

// 출력부분 클래스 상속
public class MainController {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	UserService userService = UserService.getInstance();
	LectureService lectureService = LectureService.getInstance();

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
			case MEM_HOME:
				view = memHome();
				break;
			case LECTURER_HOME:
				view = lecturerHome();
				break;
			case ADMIN_HOME:
				view = adminHome();
				break;
			case ALL_LECTURE_LIST:
				view = allLecture();
				break;
//			case ADMIN_HOME:
//				view = adminHome();
//				break;
			default:
				break;
			}
		}
	}



//	commy and push 할때 로컬에서 보내야한다 브랜치는 shun으로

//github.com/pshhyeon/ddit_basic_project.git
	
	private View allLecture() {//allLecture리스트
//		LECTURE_NO  출력리스트
//		LECTURE_NAME
//		LECTURE_CONTENT
//		USER_NAME
//		LEVEL_NAME
//		BOOK_NAME
//		BOOKCATEGORY_NAME
		List<Map<String,Object>> alllectureList = lectureService.lectureList();
		System.out.println("전체 강의리스트입니다");
		System.out.println("-----------------------");
		for(Map<String,Object> map : alllectureList) {
			BigDecimal lectureNo = (BigDecimal)map.get("LECTURE_NO");
			String lectureName = (String)map.get("LECTURE_NAME");
			String lectureContent = (String)map.get("LECTURE_CONTENT");
			String userName = (String)map.get("USER_NAME");
			String levlName = (String)map.get("LEVEL_NAME");
			String bookName = (String)map.get("BOOK_NAME");
			String bookCategory = (String)map.get("BOOKCATEGORY_NAME");
			
			System.out.println(lectureNo.intValue()+"\t"+lectureName+"\t"+lectureContent+"\t"+
					userName);
			System.out.println(levlName+"\t"+bookName+"\t"+bookCategory);
		}
		return View.MEM_HOME;
	}


	private View adminHome() {
		System.out.println("환영합니다~!~! 관리자님");
		System.out.println();
		System.out.println("1. 일반회원 조회하기");
		System.out.println("2. 강사 조회하기");
		System.out.println("3. 전체회원조회하기");
		System.out.println("4. 전체리뷰 조회하기");
		System.out.println("5. 로그아웃");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.ALL_MEM_LIST;
		case 2:
			return View.ALL_LECTURER_LIST;
		case 3:
			return View.ALL_MEM_LIST;
		case 4:
			return View.ALL_LEVIEW_LIST;
		case 5:
			return View.HOME;
		default:
			return View.HOME;
		}
	}

	private View lecturerHome() {
		System.out.println("환영합니다~!~! 강사님");
		System.out.println();
		System.out.println("1. 강의 등록하기");
		System.out.println("2. 내 강의");
		System.out.println("3. 책 등록");
		System.out.println("4. 로그아웃");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.LECTURE_INSERT;
		case 2:
			return View.LECTURER_MYLECTURE;
		case 3:
			return View.BOOK_INSERT;
		case 4:
			return View.HOME;
		default:
			return View.HOME;
		}
	}

	private View memHome() {
		System.out.println("환영합니다~!~! 회원님");
		System.out.println();
		System.out.println("1. 전체 강의조회하기");
		System.out.println("2. 강의 신청하기");
		System.out.println("3. 내 강의");
		System.out.println("4. 로그아웃");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.ALL_LECTURE_LIST;
		case 2:
			return View.LECTURE_APPLY;
		case 3:
			return View.USER_MYLECTURE;
		case 4:
			return View.HOME;
		default:
			return View.HOME;
		}
	}


//github.com/pshhyeon/ddit_basic_project.git

//github.com/pshhyeon/ddit_basic_project.git
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


		System.out.println("1. 일반회원가입\n2. 강사회원가입\3. home");
		
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		if(sel==3) {
			return View.HOME;
		}
		List<Object> param = new ArrayList<Object>();
		System.out.println();
		String id = ScanUtil.nextLine("ID : ");
		param.add(id);
		
	


		System.out.println("\n회원가입을 진행합니다");
		param.add(id);
		if (!userService.joinChk(param)) {
			return View.USER_JOIN;
		}
		String pass = ScanUtil.nextLine("사용가능한 ID입니다\nPASS : ");
		String name = ScanUtil.nextLine("이름 : ");
		String bir = ScanUtil.nextLine("생년월일('20001231'형식으로 입력해주세요!!) : ");
		String hp = "";
		while (true) {
			hp = ScanUtil.nextLine("핸드폰번호('01012345678'형식으로 입력해주세요!!) : ");
			if (hp.matches("\\d{11}")) {
				hp = hp.substring(0, 3) + "-" + hp.substring(3, 7) + "-" + hp.substring(7);
				break;
			} else {
				System.out.println("올바르지 않은 형식입니다. 다시 입력해주세요");
			}
		}

		String address = ScanUtil.nextLine("주소(선택사항입니다. 작성하지 않을시 Enter를 눌러주세요) : ");



		param.add(pass);
		param.add(hp);
		param.add(bir);
		param.add(name);
		param.add(address);

		boolean join = userService.join(param, (int) sessionStorage.get("join"));
		if (join) {
			return (View) sessionStorage.get("page");
		} else {
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
		int login  = (int)sessionStorage.get("page");

		if (userService.login(param, (int) sessionStorage.get("login"))) {
			System.out.println("로그인");
			System.out.println((UserVo) sessionStorage.get("user"));
			return (View)sessionStorage.get("page");
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
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
		case 2:
		case 3:
			sessionStorage.put("login", sel);
			return View.LOGIN;
		case 4:
			// 재선택
			while (true) {
				System.out.println("1. 일반회원가입\n2. 강사회원가입\n3. home");
				sel = ScanUtil.menu();
				if (sel > 3) {
					System.out.println("다시 선택 해주세요.");
					continue;
				}
				break;
			}

			if (sel == 3) {
				return View.HOME;
			}
			sessionStorage.put("join", sel);
			return View.USER_JOIN;
		case 5:
			return View.HOME;
		default:
			return View.HOME;
		}
	}
}
