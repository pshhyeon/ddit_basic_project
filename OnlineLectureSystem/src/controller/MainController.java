package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.BookService;
import service.LectureService;
import service.ReviewService;
import service.UserService;
import util.ScanUtil;
import util.View;
import vo.MyHomeVo;
import vo.ReviewVo;
import vo.UserVo;

public class MainController {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	UserService userService = UserService.getInstance();
	LectureService lectureService = LectureService.getInstance();
	BookService bookservice = BookService.getInstance();
	ReviewService  reviewService = ReviewService.getInstance();
	
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
				break;
			case LECTURE_APPLY:
				view = lectureApply();
				break;
			case USER_MYLECTURE:
				view = userMylecture();
				break;
			case LECTURE_APPLY_LIST:
				view = lectureApplyList();
				break;
			case PAST_LECTURE_APPLY_LIST:
				view = pastlectureApplyList();
				break;
			case MAKING_REVIEW:
				view = makingReview();
				break;
			case LECTURE_CANCEL:
				view = lectureCancel();
				break;
			case LECTURE_INSERT:
				view = lectureInsert();
				break;
			case BOOK_INSERT:
				view = bookInsert();
				break;
			case REVIEW_LIST:
				view = reviewList();
				break;
			case REVIEW_UPDATE:
				view = reviewUpdate();
				break;
			default:
				break;
			}
		}
	}

	private View reviewUpdate() {
		System.out.println("리뷰수정을 진행합니다");
//		SELECT MAX(LECTURE_NO)+1 FROM REVIEW), CONTENT = ? , RATED = ?
		((UserVo) sessionStorage.get("user")).getUser_no();
		sessionStorage.get("lectureNo");
		String content = ScanUtil.nextLine("내용을 입력해주세요");
		int ratedNum = ScanUtil.nextInt("1~5별점을 입력해주세요 : ");
		
		String rated = "";
		for (int i =0; i<ratedNum; i++) {
			rated +="☆";
		}
		List<Object> param = new ArrayList();
		param.add(content);
		param.add(rated);
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());
		param.add((int) sessionStorage.get("lectureNo"));
		
		System.out.println("리뷰수정이 완료되었습니다.");
		return null;
		
		

	}

	private View reviewList() {
		System.out.println("전체리뷰 리스트입니다.");
		List<ReviewVo> reviewlist = reviewService.reviewList();
		for(ReviewVo reviewList : reviewlist ) {
			System.out.println(reviewList);
		}
		
		return null;
	}

	private View bookInsert() {
//		BOOK_NO,
//		BOOK_NAME,
//		BOOK_CONTENT,
//		BOOKCATEGORY_NO
		System.out.println("책 등록을 시작합니다");
		String bookName = ScanUtil.nextLine("책 이름을 입력해주세여");
		String bookContent = ScanUtil.nextLine("책 내용을 입열해주세요");
		int bookcategoryNo = ScanUtil.nextInt("책 종류를 적어주세여");
		List<Object> param = new ArrayList<Object>();
		param.add(bookName);
		param.add(bookContent);
		param.add(bookcategoryNo);
		
		bookservice.bookInsert(param);
		System.out.println("성공적으로 책을 등록하셨습니다.");
		return null;
	}

	private View lectureInsert() {
		
//순서
//LECTURE_NO ,
//USER_NO ,
//LECTURE_NAME ,
//LECTURE_CONTENT , 
//CATEGORY_NO ,
//LEVEL_NO ,
//BOOK_NO
		
		((UserVo) sessionStorage.get("user")).getUser_no();// (UserVo)타입을 넣어주는것을 캐스팅이라고 한다
		
		System.out.println("");
		String lecturename = ScanUtil.nextLine("강의이름을 입력해주세요");
		String lectureContent = ScanUtil.nextLine("강의내용을 입력해주세요");
		int categoryNo = ScanUtil.nextInt("카테고리번호를 입력해주세요");
		int levelNo = ScanUtil.nextInt("난이도 번호를 입력해주세요");
		int bookNo = ScanUtil.nextInt("책번호를 입력해 주세요");
		List<Object> param = new ArrayList();
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());
		param.add(lecturename);
		param.add(lectureContent);
		param.add(categoryNo);
		param.add(levelNo);
		param.add(bookNo);
		lectureService.lectureInsert(param);
		
		System.out.println("성공적으로 강의신청을 완료했습니다.");
		
		List<Map<String, Object>> alllectureList = lectureService.lectureList();
		System.out.println("전체 강의리스트입니다");
		System.out.println("-----------------------");
		for (Map<String, Object> map : alllectureList) {
			BigDecimal lectureNo = (BigDecimal) map.get("LECTURE_NO");
			String lectureName = (String) map.get("LECTURE_NAME");
			String lecturecontent = (String) map.get("LECTURE_CONTENT");
			String userName = (String) map.get("USER_NAME");
			String levlName = (String) map.get("LEVEL_NAME");
			String bookName = (String) map.get("BOOK_NAME");
			String bookCategory = (String) map.get("BOOKCATEGORY_NAME");

			System.out.println(lectureNo.intValue() + "\t" + lectureName + "\t" + lectureContent + "\t" + userName);
			System.out.println(levlName + "\t" + bookName + "\t" + bookCategory);
		}
		
		return null;
	}

	private View lectureCancel() {
		System.out.println("수강신청 취소");
		
		((UserVo) sessionStorage.get("user")).getUser_no();// (UserVo)타입을 넣어주는것을 캐스팅이라고 한다
		sessionStorage.get("lectureNo");
		System.out.println("");
		List<Object> param = new ArrayList();
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());
		param.add((int) sessionStorage.get("lectureNo"));
		lectureService.lectureCancel(param);
		System.out.println("수강신청 취소가 완료되었습니다.");
		return View.LECTURE_APPLY_LIST;
	}

	private View makingReview() {

//		CONTENT,
//		RATED,
//		USER_NO,
//		LECTURE_NO
		
		((UserVo) sessionStorage.get("user")).getUser_no();// (UserVo)타입을 넣어주는것을 캐스팅이라고 한다
		sessionStorage.get("lectureNo");
		System.out.println("");

		
			String content = ScanUtil.nextLine("내용을 입력해주세요 : ");
			int ratedNum = ScanUtil.nextInt("1~5별점을 입력해주세요 : ");
			
			String rated = "";
			for (int i =0; i<ratedNum; i++) {
				rated +="☆";
			}
			
			List<Object> param = new ArrayList();
			param.add(content);
			param.add(rated);
			param.add(((UserVo) sessionStorage.get("user")).getUser_no());
			param.add((int) sessionStorage.get("lectureNo"));
			
			reviewService.makingReview(param);
			System.out.println("성공적으로 리뷰를 작성하셨습니다.");
			
			int sel = ScanUtil.nextInt("1. 리뷰목록보기 \n2. 리뷰수정하기");
			switch (sel) {
			case 1:
				return View.REVIEW_LIST;
			case 2:
				return View.REVIEW_UPDATE;
			default:
				System.out.println("잘못입력하셨습니다. 홈으로 돌아갑니다");
				return View.MEM_HOME;
			}
	

	}

	private View pastlectureApplyList() {// 과거수강신청내역

		((UserVo) sessionStorage.get("user")).getUser_no();
		System.out.println("내 과거수강신청 내역을 확인합니다");
		List<Object> param = new ArrayList();
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());
//		   U.USER_NAME , 
//	       L.LECTURE_NAME,
//	       C.CATEGORY_NAME,
//	       LE.LEVEL_NAME,
//	       M.LECTURE_START
//		     LECTURE_FINISH

		List<Map<String, Object>> pastlectureApplyList = lectureService.pastlectureApplyList(param);
		System.out.println("-----------------------");
		for (Map<String, Object> map : pastlectureApplyList) {
			String userName = (String) map.get("USER_NAME");
			String lectureName = (String) map.get("LECTURE_NAME");
			String categoryName = (String) map.get("CATEGORY_NAME");
			String lectureStart = (String) map.get("LECTURE_START");
			String lectureFinish = (String) map.get("LECTURE_FINISH");
			System.out.println(userName + "\t" + lectureName + "\t" + categoryName);
			System.out.println(categoryName + "\t" + lectureStart + "\t" + lectureFinish);
		}

		return null;
	}

	private View lectureApplyList() { // 수강신청내역
		((UserVo) sessionStorage.get("user")).getUser_no();
		System.out.println("내 수강신청 내역을 확인합니다");
		List<Object> param = new ArrayList();
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());
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
			System.out.println(categoryName + "\t" + lectureStart);
		}

		System.out.println("1. 리뷰남기기\n2. 수강신청취소\n3. 내 강의 자세히보기");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.MAKING_REVIEW;
		case 2:
			return View.LECTURE_CANCEL;
		case 3:
			return View.MYLECTURE_DETAIL;
		default:
			System.out.println("잘못입력하셨습니다. 다시 입력해주세요");
			return View.LECTURE_APPLY_LIST;
		}

	}

	private View userMylecture() {// 일반회원(내 강의)
		System.out.println("1. 수강신청내역 확인하기\n2. 과거 수강신청내역 확인하기");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.LECTURE_APPLY_LIST;
		case 2:
			return View.PAST_LECTURE_APPLY_LIST;
		default:
			System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
			return View.USER_MYLECTURE;
		}

	}

	private View lectureApply() {// 수강신청하기
//		
//		USER_NO,
//		LECTURE_NO, 
//		LECTURE_START,
//		LECTURE_FINISH

		((UserVo) sessionStorage.get("user")).getUser_no();// (UserVo)타입을 넣어주는것을 캐스팅이라고 한다
		sessionStorage.get("lectureNo");
		System.out.println("");
		List<Object> param = new ArrayList();
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());
		param.add((int) sessionStorage.get("lectureNo"));
		lectureService.lectureApply(param);
		System.out.println("수강신청이 완료되었습니다");

		return View.USER_MYLECTURE;
	}

	private View lectureDetail() {// 강의 자세히보기
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
		
		
		// 
		boolean alreadyLec = false;
		List<MyHomeVo> myHome = userService.myHomeList();
		int lecNo = ScanUtil.nextInt("과목 번호 선택");
		for(MyHomeVo mh : myHome) {
			if(mh.getLecture_no() == lecNo) {
				alreadyLec= true; // 수강중인 과목으로 판단
				break;
			}
		}
			
		if (alreadyLec) {
			System.out.println("-------------------");
			System.out.println("1. 수강신청취소\n2. 리뷰작성 \n3. 홈");
			int sel = ScanUtil.menu();
//			if(sel==1) {
//				return View.LECTURE_CANCEL;
//			}
//			if(sel==2) {
//				return View.MAKING_REVIEW;
//			}
			switch (sel) {
			case 1:
				return View.LECTURE_CANCEL;
			case 2:
				return View.MAKING_REVIEW;
			case 3:
				return View.HOME;

			default:
				return View.HOME;
			}
		} else {
			System.out.println("-------------------");
			System.out.println("1. 수강신청\n2. 홈");
			int sel = ScanUtil.menu();
			switch (sel) {
			case 1:
				return View.LECTURE_APPLY;
			case 2:
				return  View.HOME;
			default:
				return View.HOME;
			}
		}

	}

	private View allLecture() {// 전체강의리스트
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

	private View adminHome() {// 관리자홈
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

	private View lecturerHome() {// 강사님홈
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

	private View memHome() {// 일반회원홈
		
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

	public View userDelete() {// 회원탈퇴작업
		String yn = ScanUtil.nextLine("회원을 탈퇴하시겠습니까?? (y/n)");
		if (yn.equals("y")) {
			String pass = ScanUtil.nextLine("비밀번호 >>");
			return userService.delAcount(pass);
		}
		System.out.println("회원탈퇴를 취소합니다.");

		return (View) sessionStorage.get("page");
	}

	private View userJoin() {// 회원가입작업

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

	private View login() {// 로그인메서드
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