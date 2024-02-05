package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.LecturePrint;
import service.BookService;
import service.LectureService;
import service.ReviewService;
import service.UserService;
import util.ScanUtil;
import util.View;
import vo.LectureCategoryVo;
import vo.MyHomeVo;
import vo.ReviewVo;
import vo.UserVo;

public class MainController extends LecturePrint {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	UserService userService = UserService.getInstance();
	LectureService lectureService = LectureService.getInstance();
	BookService bookservice = BookService.getInstance();
	ReviewService reviewService = ReviewService.getInstance();

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
			case REVIEW_DETAIL:
				view = reviewDetail();
				break;
			case LECTURE_SEARCH:
				view = lectureSearch();
				break;
			default:
				break;
			}
		}
	}

	private View reviewDetail() {
		if(!sessionStorage.containsKey("reviewNo")) {
			int reviewNo = ScanUtil.nextInt("리뷰번호를 입력해주세요");
			sessionStorage.put("reviewNo", reviewNo);
		}
		int reviewnum = (int) sessionStorage.get("reviewNo");			
		ReviewVo review = reviewService.reviewDetail(reviewnum);
		System.out.println(review);
		int sel = ScanUtil.nextInt("1. 리뷰 수정 \n2. 리뷰 삭제\n2. 작성한 리뷰 리트스");
		switch (sel) {
		case 1:
			// 리뷰 디테일로 이동하게 하기
			sessionStorage.put("lectureNo", review.getLecture_no());
			return View.REVIEW_UPDATE;
		case 2:
			return View.REVIEW_DETAIL;
		default:
			return View.REVIEW_LIST;
		}
	}

	private View reviewUpdate() {
		System.out.println("리뷰수정을 진행합니다");
		((UserVo) sessionStorage.get("user")).getUser_no();
		sessionStorage.get("lectureNo");
		String content = ScanUtil.nextLine("내용을 입력해주세요");
		int ratedNum = ScanUtil.nextInt("1~5별점을 입력해주세요 : ");

		String rated = "";
		for (int i = 0; i < ratedNum; i++) {
			rated += "★";
		}
		List<Object> param = new ArrayList();
		param.add(content);
		param.add(rated);
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());
		param.add((int) sessionStorage.get("lectureNo"));
		int reviewNo = reviewService.reviewUpdate(param);
		System.out.println("리뷰수정이 완료되었습니다.");
		sessionStorage.put("reviewNo", reviewNo);
		// 리뷰 디테일로 이동하게 하기
		return View.REVIEW_DETAIL;
	}

	private View reviewList() {
		System.out.println("작성한 리뷰 리스트입니다.");
		List<ReviewVo> reviewlist = reviewService.reviewList(((UserVo) sessionStorage.get("user")).getUser_no());
		for (ReviewVo reviewList : reviewlist) {
			System.out.println(reviewList);
		}

		int sel = ScanUtil.nextInt("1. 리뷰 상세보기 \n2. 홈으로 이동");
		switch (sel) {
		case 1:
			return View.REVIEW_DETAIL;
		case 2:
			return View.MEM_HOME;
		default:
			return View.REVIEW_LIST;
		}

	}

	private View bookInsert() {
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

		// 나중에 필요하면 이동위치 바꾸기
		return View.LECTURER_HOME;
	}

	private View lectureInsert() {
		((UserVo) sessionStorage.get("user")).getUser_no();

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

		System.out.println("성공적으로 강의등록을 완료했습니다.");

		// 나중에 필요하면 이동위치 바꾸기
		return View.LECTURER_HOME;
	}

	private View lectureCancel() {
		System.out.println("수강신청 취소");

		((UserVo) sessionStorage.get("user")).getUser_no();
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
		((UserVo) sessionStorage.get("user")).getUser_no();// (UserVo)타입을 넣어주는것을 캐스팅이라고 한다
		sessionStorage.get("lectureNo");
		System.out.println("");

		String content = ScanUtil.nextLine("내용을 입력해주세요 : ");
		int ratedNum = ScanUtil.nextInt("1~5별점을 입력해주세요 : ");

		String rated = "";
		for (int i = 0; i < ratedNum; i++) {
			rated += "☆";
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
		List<Map<String, Object>> pastlectureApplyList = lectureService.pastlectureApplyList(param);
		System.out.println("-----------------------");
		for (Map<String, Object> map : pastlectureApplyList) {
			BigDecimal lectureNum = (BigDecimal) map.get("LECTURE_NO"); 
			//강의번호 추가 및 순서변경
			String userName = (String) map.get("USER_NAME");
			String lectureName = (String) map.get("LECTURE_NAME");
			String categoryName = (String) map.get("CATEGORY_NAME");
			String lectureStart = (String) map.get("LECTURE_START");
			String lectureFinish = (String) map.get("LECTURE_FINISH");
			System.out.println(lectureNum.intValue() + "\t" +userName + "\t" + lectureName + "\t" + categoryName+ "\t" +categoryName + "\t" + lectureStart + "\t" + lectureFinish );
		}

		System.out.println("1. 강의 상세보기");
		System.out.println("2. 돌아가기");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.LECTURE_DETAIL;
		case 2:
			return View.MEM_HOME;
		default:
			return View.PAST_LECTURE_APPLY_LIST;
		}
	}

	private View lectureApplyList() { // 수강신청내역
		((UserVo) sessionStorage.get("user")).getUser_no();
		System.out.println("내 수강신청 내역을 확인합니다");
		List<Object> param = new ArrayList();
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());

		List<Map<String, Object>> lectureApplyList = lectureService.lectureApplyList(param);
		System.out.println("-----------------------");
		for (Map<String, Object> map : lectureApplyList) {
			String lectureName = (String) map.get("LECTURE_NAME");
			String categoryName = (String) map.get("CATEGORY_NAME");
			String lectureStart = (String) map.get("LECTURE_START");
			String levelName = (String) map.get("LEVEL_NAME");
			String lectureNo = (String) map.get("LECTURE_NO");

			System.out.println("강의번호: " + lectureNo + "\t" + "강의명: " + lectureName + "\t" + "강의유형: " + categoryName
					+ "\t" + "강의레벨: " + levelName + "\t" + "수강신청날짜: " + lectureStart);
		}

		System.out.println("1. 강의 상세보기 \n2. 홈");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.LECTURE_DETAIL;
		case 2:
			return View.MEM_HOME;
		default:
			return View.LECTURE_APPLY_LIST;
		}

	}

	private View userMylecture() {// 일반회원(내 강의)
		System.out.println("1. 수강신청내역 확인하기\n2. 과거 수강신청내역 확인하기\n3. 작성한 리뷰 보기");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.LECTURE_APPLY_LIST;
		case 2:
			return View.PAST_LECTURE_APPLY_LIST;
		case 3:
			return View.REVIEW_LIST;
		default:
			return View.USER_MYLECTURE;
		}

	}

	private View lectureApply() {// 수강신청하기
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
		int lectureNo = ScanUtil.nextInt("강의번호를 선택해주세요.");
		sessionStorage.put("lectureNo", lectureNo);
		List<Object> param = new ArrayList<Object>();

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

		// 수강여부 체크
		boolean alreadyLec = false;
		List<MyHomeVo> myHome = userService.myHomeList();
		for (MyHomeVo mh : myHome) {
			if (mh.getLecture_no() == lectureNo) {
				alreadyLec = true; // 수강중인 과목으로 판단
				break;
			}
		}

		// 리뷰 작성 여부 판단
		List<Object> param2 = new ArrayList<Object>();
		param2.add(((UserVo) sessionStorage.get("user")).getUser_no());
		param2.add((int) sessionStorage.get("lectureNo"));
		ReviewVo reviewVo = (ReviewVo) reviewService.reviewChk(param2);
		
		// 일반회원
		if (((UserVo) sessionStorage.get("user")).getDivi_no() == 1) {
			if (alreadyLec) {
				if (reviewVo == null) {
					System.out.println("-------------------");
					System.out.println("1. 수강신청취소\n2. 리뷰작성 \n3. 홈");
					int sel = ScanUtil.menu();
					switch (sel) {
					case 1:
						return View.LECTURE_CANCEL;
					case 2:
						return View.MAKING_REVIEW;
					case 3:
						return View.HOME;

					default:
						return View.MEM_HOME;
					}
				} else {
					System.out.println("-------------------");
					System.out.println("1. 수강신청취소\n2. 리뷰수정 \n3. 홈");
					int sel = ScanUtil.menu();
					switch (sel) {
					case 1:
						return View.LECTURE_CANCEL;
					case 2:
						return View.REVIEW_UPDATE;
					case 3:
						return View.MEM_HOME;

					default:
						return View.MEM_HOME;
					}
				}
			} else {
				System.out.println("-------------------");
				System.out.println("1. 수강신청\n2. 홈");
				int sel = ScanUtil.menu();
				switch (sel) {
				case 1:
					return View.LECTURE_APPLY;
				case 2:
					return View.MEM_HOME;
				default:
					return View.LECTURE_DETAIL;
				}
			}
		}
		// 강사
		if (((UserVo) sessionStorage.get("user")).getDivi_no() == 2) {
			System.out.println("-------------------");
			System.out.println("1. 리뷰 확인\n2. 홈");
			int sel = ScanUtil.menu();
			switch (sel) {
			case 1:
				// 리뷰확인 강사 버전으로 변경하기
				return View.LECTURE_DETAIL;
			case 2:
				return View.LECTURER_HOME;
			default:
				return View.LECTURE_DETAIL;
			}
		}
		return View.LECTURE_DETAIL;
	}

	private View lectureSearch() {
		System.out.println("1. 이름 검색");
		System.out.println("2. 강의 카테고리 검색");
		System.out.println("3. 전체 검색");

		List<Object> param = new ArrayList<Object>();
		int sel = ScanUtil.menu();
		if (sel == 1 ) {
			String name = ScanUtil.nextLine("이름: ");
			param.add(name);
		}
		if (sel == 2 ) {
			List<LectureCategoryVo> cateList = lectureService.lectureCtegoryList();
			for (LectureCategoryVo l : cateList) {
				System.out.println(l);
			}
			int type = ScanUtil.nextInt("카테고리 번호 선택: ");//인트로 형변환
			param.add(type);
		}
		if(sel ==3 ) {
			String allname = ScanUtil.nextLine("전체");
			param.add(allname);
			param.add(allname);
		}

		List<Map<String, Object>> lectureList = lectureService.lectureList(param, sel);
		if (lectureList != null) {
			System.out.println("검색된 강의리스트입니다");
			System.out.println("-----------------------");
			for (Map<String, Object> map : lectureList) {
				BigDecimal lectureNo = (BigDecimal) map.get("LECTURE_NO");
				String lectureName = (String) map.get("LECTURE_NAME");
				String lectureContent = (String) map.get("LECTURE_CONTENT");
				String userName = (String) map.get("USER_NAME");
				String levlName = (String) map.get("LEVEL_NAME");
				String bookName = (String) map.get("BOOK_NAME");
				String bookCategory = (String) map.get("CATEGORY_NAME");

				System.out.println(lectureNo.intValue() + "\t" + lectureName + "\t" + lectureContent + "\t" + userName);
				System.out.println(levlName + "\t" + bookName + "\t" + bookCategory);
			}
		} else {
			System.out.println("검색결과가 존재하지 않습니다");
		}
		// 일반회원
		if (((UserVo) (sessionStorage.get("user"))).getDivi_no() == 1) {//일반화원 구분
			System.out.println("1. 강의 상세페이지");
			System.out.println("2. 강의 검색");
			System.out.println("3. 홈");

			int sel2 = ScanUtil.nextInt("메뉴 선택 : ");
			switch (sel2) {
			case 1:
				return View.LECTURE_DETAIL;
			case 2:
				return View.LECTURE_SEARCH;
			case 3:
				return View.MEM_HOME;
			default:
				return View.LECTURE_SEARCH;
			}
		}
		// 강사
		if (((UserVo) (sessionStorage.get("user"))).getDivi_no() == 2) {
			System.out.println("1. 강의 상세페이지");
			System.out.println("2. 강의 검색");
			System.out.println("3. 강의 등록");
			System.out.println("4. 홈");

			int sel2 = ScanUtil.nextInt("메뉴 선택 : ");
			switch (sel2) {
			case 1:
				return View.LECTURE_DETAIL;
			case 2:
				return View.LECTURE_SEARCH;
			case 3:
				return View.LECTURE_INSERT;
			case 4:
				return View.LECTURER_HOME;
			default:
				return View.ALL_LECTURE_LIST;
			}
		}

		// 일반 회원

		return View.ALL_LECTURE_LIST;
	}

	private View allLecture() { // allLecture리스트
		int page = 1;
		if (sessionStorage.containsKey("currentPage")) {
			page = (int) sessionStorage.get("currentPage");
		}
		int startNum = 1 + 5 * (page - 1);
		int endNum = 5 * page;
		List<Object> pageNum = new ArrayList<Object>();
		pageNum.add(startNum);
		pageNum.add(endNum);

		List<Map<String, Object>> alllectureList = lectureService.lectureList(pageNum);
		System.out.println("전체 강의리스트입니다");
		System.out.println("-----------------------");
		for (Map<String, Object> map : alllectureList) {
			BigDecimal lectureNo = (BigDecimal) map.get("LECTURE_NO");
			String lectureName = (String) map.get("LECTURE_NAME");
			String lectureContent = (String) map.get("LECTURE_CONTENT");
			String userName = (String) map.get("USER_NAME");
			String levlName = (String) map.get("LEVEL_NAME");
			String bookName = (String) map.get("BOOK_NAME");
			String bookCategory = (String) map.get("CATEGORY_NAME");

			System.out.println(lectureNo.intValue() + "\t" + lectureName + "\t" + lectureContent + "\t" + userName);
			System.out.println(levlName + "\t" + bookName + "\t" + bookCategory);
		}

		// 사용자
		if (((UserVo) (sessionStorage.get("user"))).getDivi_no() == 1) {
			System.out.println("1. 이전 페이지");
			System.out.println("2. 다음 페이지");
			System.out.println("3. 강의 상세페이지");
			System.out.println("4. 강의 검색");
			System.out.println("5. 홈");

			int sel = ScanUtil.nextInt("메뉴 선택 : ");
			switch (sel) {
			case 1:
				if (page != 1) {
					page -= 1;
				}
				MainController.sessionStorage.put("currentPage", page);
				return View.ALL_LECTURE_LIST;
			case 2:
				if (page != lectureService.getMaxPage() / 5 + 1) {
					page += 1;
				}
				MainController.sessionStorage.put("currentPage", page);
				return View.ALL_LECTURE_LIST;
			case 3:
				return View.LECTURE_DETAIL;
			case 4:
				return View.LECTURE_SEARCH;
			case 5:
				return View.MEM_HOME;
			default:
				return View.ALL_LECTURE_LIST;
			}
		}

		// 강사
		if (((UserVo) (sessionStorage.get("user"))).getDivi_no() == 2) {

			System.out.println("1. 강의 상세페이지");
			System.out.println("2. 강의 검색");
			System.out.println("3. 홈");

			int sel = ScanUtil.nextInt("메뉴 선택 : ");
			switch (sel) {
			case 1:
				return View.LECTURE_DETAIL;
			case 2:
				return View.LECTURE_SEARCH;
			case 3:
				return View.LECTURER_HOME;
			default:
				return View.ALL_LECTURE_LIST;
			}

		}
		return View.ALL_LECTURE_LIST;
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
//		case 1:
//			return View.ALL_MEM_LIST;
//		case 2:
//			return View.ALL_LECTURER_LIST;
//		case 3:
//			return View.ALL_MEM_LIST;
		case 4:
			return View.ALL_LEVIEW_LIST;
		case 5:
			return View.HOME;
		default:
			return View.HOME;
		}
	}

	private View lecturerHome() {
		System.out.println("1. 내강의 조회하기");
		System.out.println("2. 내 책 조회");
		System.out.println("3. 로그아웃");
		System.out.println("4. 회원 탈퇴");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.ALL_LECTURE_LIST;
		case 2:
			return View.BOOK_LIST;
		case 3:
			sessionStorage.clear();
			return View.HOME;
		case 4:
			sessionStorage.put("page", View.LECTURER_HOME);
			return userDelete();
		default:
			return View.LECTURER_HOME;
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
			sessionStorage.put("join", sel);
			if (sel == 3) {
				return View.HOME;
			}
			if (sel == 1) {
				sessionStorage.put("page", View.MEM_HOME);
			}
			if (sel == 2) {
				sessionStorage.put("page", View.LECTURER_HOME);
			}
			return View.USER_JOIN;
		case 5:
			return View.HOME;
		default:
			return View.HOME;
		}
	}
}