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
			case LECTURE_DETAIL:
				view = lectureDetail();
			case LECTURE_APPLY:
				view = lectureApply();
			case USER_MYLECTURE:
				view = userMylecture();
				break;
			case LECTURE_APPLY_LIST:
				view = lectureApplyList();
				break;
			default:
				break;
			}
		}
	}

	private View lectureApplyList() {
		((UserVo)sessionStorage.get("user")).getUser_no();
		System.out.println("내 수강신청 내역을 확인합니다");
		List<Object> param = new ArrayList();
		param.add(((UserVo)sessionStorage.get("user")).getUser_no());
//		   U.USER_NAME , 
//	       L.LECTURE_NAME,
//	       C.CATEGORY_NAME,
//	       LE.LEVEL_NAME,
//	       M.LECTURE_START
		
		
		List<Map<String, Object>> lectureApplyList = lectureService.lectureApplyList(param);
		System.out.println("-----------------------");
		for (Map<String, Object> map : lectureApplyList) {
			String userName = (String) map.get("USER_NAME");
			String lectureName = (String) map.get("LECTURE_NAME");
			String categoryName = (String) map.get("CATEGORY_NAME");
			String lectureStart = (String) map.get("LECTURE_START");

			System.out.println(userName + "\t" + lectureName + "\t" + categoryName);
			System.out.println(categoryName + "\t" + lectureStart );
		}
		
		System.out.println("");
		
		
		return null;
	}

	private View userMylecture() {
		System.out.println("1. 수강신청내역 확인하기\n2. 과거 수강신청내역 확인하기");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.LECTURE_APPLY_LIST;
		default:
			break;
		}
		
		return null;
	}

	private View lectureApply() {
//		
//		USER_NO,
//		LECTURE_NO, 
//		LECTURE_START,
//		LECTURE_FINISH
		
		((UserVo)sessionStorage.get("user")).getUser_no();//(UserVo)타입을 넣어주는것을 캐스팅이라고 한다
		sessionStorage.get("lectureNo");
		System.out.println("");
		List<Object> param = new ArrayList();
		param.add(((UserVo)sessionStorage.get("user")).getUser_no());
		param.add((int)sessionStorage.get("lectureNo"));
		lectureService.lectureInsert(param);
		System.out.println("수강신청이 완료되었습니다");
		
		
		return View.USER_MYLECTURE;
	}

	private View lectureDetail() {
		int detailsel = ScanUtil.nextInt("강의번호를 선택해주세요.");
		sessionStorage.put("lectureNo", detailsel);
		
		List<Object> param = new ArrayList<Object>();
		int lectureNo = (int) sessionStorage.get("lectureNo");
		param.add(lectureNo);
		List<Map<String, Object>> alllectureList = lectureService.lectureList();
		System.out.println("강의 상세 조회입니다");
		System.out.println("-----------------------");
		Map<String, Object> map = lectureService.lectureDetail(param);
		BigDecimal lectureno = (BigDecimal) map.get("LECTURE_NO");
		String lectureName = (String) map.get("LECTURE_NAME");
		String lectureContent = (String) map.get("LECTURE_CONTENT");
		String userName = (String) map.get("USER_NAME");
		String levlName = (String) map.get("LEVEL_NAME");
		String bookName = (String) map.get("BOOK_NAME");
		String bookCategory = (String) map.get("BOOKCATEGORY_NAME");

		System.out.println(lectureno.intValue() + "\t" + lectureName + "\t" + lectureContent + "\t" + userName);
		System.out.println(levlName + "\t" + bookName + "\t" + bookCategory);

		System.out.println("1. 수강신청\n2. 수강신청취소\n3. 홈 ");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.LECTURE_APPLY;
//		case 2:
//			return View.LECTURE_APPLY_CANCEL;
		default:
			return View.MEM_HOME;
		}
	}

	private View allLecture() {// allLecture리스트
		List<Map<String, Object>> alllectureList = lectureService.lectureList();
		System.out.println("전체 강의리스트입니다");
		System.out.println("-----------------------");
		for (Map<String, Object> map : alllectureList) {
			BigDecimal lectureNo = (BigDecimal) map.get("LECTURE_NO");
			String lectureName = (String) map.get("LECTURE_NAME");
			String lectureContent = (String) map.get("LECTURE_CONTENT");
			String userName = (String) map.get("USER_NAME");
			String levlName = (String) map.get("LEVEL_NAME");
			String bookName = (String) map.get("BOOK_NAME");
			String bookCategory = (String) map.get("BOOKCATEGORY_NAME");

			System.out.println(lectureNo.intValue() + "\t" + lectureName + "\t" + lectureContent + "\t" + userName);
			System.out.println(levlName + "\t" + bookName + "\t" + bookCategory);
		}
		System.out.println("1. 강의 상세히보기 \n2. 강의 검색하기 ");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.LECTURE_DETAIL;
//		case 2:
//			sessionStorage.put("lectureNo", 1);
//			return View.LECTURE_DETAIL;
		default:
			return View.MEM_HOME;
		}
	}

	private View adminHome() {
		System.out.println("환영합니다~!~! 관리자님\n");
		System.out.println("1. 일반회원 조회하기");
		System.out.println("2. 강사 조회하기");
		System.out.println("3. 전체회원조회하기");
		System.out.println("4. 전체리뷰 조회하기");
		System.out.println("5. 로그아웃");
		int sel = ScanUtil.menu();
		switch (sel) {

		case 4:
			return View.ALL_LEVIEW_LIST;
		case 5:
			return View.HOME;
		default:
			return View.HOME;
		}
	}

	private View lecturerHome() {
		System.out.println("환영합니다~!~! 강사님\n");
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
		System.out.println("1. 전체 강의 조회하기");
		System.out.println("2. 내 강의실");
		System.out.println("3. 로그아웃");
		System.out.println("4. 회원 탈퇴");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.ALL_LECTURE_LIST;
		case 2:
			return View.USER_MYLECTURE;
		case 3:
			sessionStorage.clear();
			return View.HOME;
		case 4:
			sessionStorage.put("page", View.MEM_HOME);
			return userDelete();
		default:
			return View.MEM_HOME;
		}

	}

	public View userDelete() {
		String yn = ScanUtil.nextLine("회원을 탈퇴하시겠습니까?? (y/n)");
		if (yn.equals("y")) {
			String pass = ScanUtil.nextLine("비밀번호 >>");
			return userService.delAcount(pass);
		}
		System.out.println("회원탈퇴를 취소합니다.");

		return (View) sessionStorage.get("page");
	}

	private View userJoin() {

		List<Object> param = new ArrayList<Object>();
		System.out.println("\n회원가입을 진행합니다");
		String id = ScanUtil.nextLine("ID : ");
		param.add(id);
		if (!userService.joinChk(param)) {
			return View.USER_JOIN;
		}
		String pass = ScanUtil.nextLine("사용가능한 ID입니다\nPASS : ");
		String name = ScanUtil.nextLine("이름 : ");
		String bir = ScanUtil.nextLine("생년월일('20001231'형식으로 입력해주세요!!) : ");
		String hp = "";
		// 나중에 service로 빼기
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

		if (userService.login(param, (int) sessionStorage.get("login"))) {
			System.out.println(((UserVo) sessionStorage.get("user")).getUser_id() + "님 환영합니다.");
			return (View) sessionStorage.get("page");
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