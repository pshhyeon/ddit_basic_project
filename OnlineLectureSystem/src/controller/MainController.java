package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.BookService;
import service.LectureService;
import service.ReplyService;
import service.ReviewService;
import service.UserService;
import util.ScanUtil;
import util.View;
import vo.LectureCategoryVo;
import vo.LectureVo;
import vo.MyHomeVo;
import vo.ReplyVo;
import vo.ReviewVo;
import vo.UserVo;

public class MainController {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	UserService userService = UserService.getInstance();
	LectureService lectureService = LectureService.getInstance();
	BookService bookservice = BookService.getInstance();
	ReviewService reviewService = ReviewService.getInstance();
	ReplyService replyService = ReplyService.getInstance();

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
			case LECTURER_MYLECTURE:
				view = lecturerMylecture();
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
			case REVIEW_DELETE:
				view = reviewDelete();
				break;
			case LECTURER_LECTURE_LIST:// 강사 강의목록
				view = lecturer_lecture_list();
				break;
			case BOOK_LIST: // BOOK-LIST 추가
				view = bookList();
				break;
			case BOOK_DELETE:
				view = bookdelete();
				break;
			case LECTURER_LECTURE_DETAIL:
				view = lecturer_lecture_detail();
				break;
			case LECTURE_UPDATE:
				view = lectureUpdate();
				break;
			case LECTURER_REVIEW:
				view = lectureReview();
				break;
			case REPLY_REVIEW:
				view = replyReview();
				break;
			case REPLY_LIST:
				view = replyList();
				break;
			case MEM_LIST:
				view = memList();
				break;
			case MEM_DETAIL:
				view = memDetail();
				break;
			case ADMIN_LECTURE_LIST:
				view = adminLectureList();
				break;
			case ADMIN_LECTURE_DETAIL:
				view = adminLectureDetail();
				break;
			case ADMIN_REVIEW_LIST:
				view = adminReviewList();
				break;
			case ADMIN_REVIEW_DETAIL:
				view = adminReviewDetail();
				break;
			case REPLY_UPDATE:
				view = replyUpdate();
				break;
			case BOOK_DETAIL://0206추가
				view = bookdetail();
				break;
			case BOOK_UPDATE://0206추가
				view = bookupdate();
				break;
			default:
				break;
			}
		}
	}

	private View bookupdate() {//0206추가
//		BOOK_CONTENT, BOOK_NAME, BOOKCATEGORY_NO
		System.out.println("책 수정을 진행합니다.");
		sessionStorage.get("book");
		String bookContent = ScanUtil.nextLine("수정할 내용을 입력해주세요");
		String bookName = ScanUtil.nextLine("수정할 책 제목을 입력해주세요");
		int bookCategory = ScanUtil.nextInt("책 종류를 적어주세여\\n1. 웹개발 \\n2.게임개발 \\n3.DataBase");
		List<Object> param = new ArrayList<Object>();
		param.add(bookContent);
		param.add(bookName);
		param.add(bookCategory);
		param.add(sessionStorage.get("book"));
		bookservice.bookUpdate(param);
		System.out.println("책 수정이 완료되었습니다 \n1.책 리스트보기 \n2. 내 강의");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.BOOK_LIST;
		case 2:
			return View.LECTURER_HOME;
		default:
			return View.LECTURER_HOME;
		}
	}

	private View bookdetail() {//0206
		int booksell = ScanUtil.nextInt("검색할 책 번호를 입력해주세요");
		MainController.sessionStorage.put("book", booksell);
		List<Object> param = new ArrayList<Object>();
		param.add(booksell);
		System.out.println("책 상세조회입니다.");
		
		
		System.out.println("------------------------");
		Map<String, Object> map  = bookservice.bookdetail(param);
			BigDecimal bookno = (BigDecimal) map.get("BOOK_NO");
			String bookname = (String) map.get("BOOK_NAME");
			String bookcontent = (String) map.get("BOOK_CONTENT");
			String bookcategoryname = (String) map.get("BOOKCATEGORY_NAME");

			System.out.println("책번호: " + bookno.intValue() + "\t" + "책제목: " + bookname + "\t" + "책내용: " + bookcontent
					+ "\t" + "책 카테고리 이름: " + bookcategoryname);
		System.out.println("1. 책 수정하기\n2. 책 삭제하기");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.BOOK_UPDATE
					;
		case 2:
			return View.BOOK_DELETE;
		default:
			return View.HOME;
		}
	}

	// 0205 추가
	private View replyUpdate() {

		System.out.println("답변 수정하기를 진행합니다");
		sessionStorage.get("reviewNum");
		((UserVo) sessionStorage.get("user")).getUser_no();
		String replycontent = ScanUtil.nextLine("수정할 강의 답변내용을 적어주세요");

		List<Object> param = new ArrayList<Object>();
		param.add(replycontent);
		param.add(sessionStorage.get("reviewNum"));
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());
		replyService.replyUpdate(param);
		System.out.println("수정이 완료되었습니다.");
		int sel = ScanUtil.nextInt("1. 내 강의전체 리스트보기\n2. 내 강의 \n3 홈");
		switch (sel) {
		case 1:
			return View.LECTURER_LECTURE_LIST;
		case 2:
			return View.LECTURER_MYLECTURE;
		case 3:
			return View.HOME;
		default:
			return View.HOME;
		}
	}

	private View replyList() {
		((UserVo) sessionStorage.get("user")).getUser_no();
		List<Object> param = new ArrayList<Object>();
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());

		List<Map<String, Object>> replyList = replyService.replyList(param);
		System.out.println("-----------------------");
		for (Map<String, Object> map : replyList) {
			String replyContent = (String) map.get("REPLY_CONTENT");
			String replyDate = (String) map.get("REPLY_DATE");
			BigDecimal reviewnum = (BigDecimal) map.get("REVIEW_NO");
			System.out.println("리뷰번호: " + reviewnum + "\t" + "답변내용: " + replyContent + "\t" + "답변날짜: " + replyDate);
		}
		return View.HOME;
	}

	private View replyReview() {
		int reviewNum = ScanUtil.nextInt("답변할 리뷰에 리뷰번호를 입력하세요");
		sessionStorage.put("reviewNum", reviewNum);
		String replycontent = ScanUtil.nextLine("리뷰에 답변할 내용을 입력해주세요");
		List<Object> param = new ArrayList<Object>();
		param.add(replycontent);
		param.add(reviewNum);
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());
		replyService.replyInsert(param);
		System.out.println("성공적으로 답변하셨습니다.");
		System.out.println("1. 답변리스트보기 \n2. 답변 수정하기");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.REPLY_LIST;
		default:
			break;
		}
		return null;
	}

	private View lectureReview() {// 강사님 강의 review리스트

		sessionStorage.get("lectureNum");
		List<Object> param = new ArrayList<Object>();
		param.add(sessionStorage.get("lectureNum"));
		List<Map<String, Object>> lectureupdate = reviewService.lectureReview(param);
		System.out.println("-----------------------");
		for (Map<String, Object> map : lectureupdate) {
			BigDecimal reviewNo = (BigDecimal) map.get("REVIEW_NO");
			String contente = (String) map.get("CONTENT");
			String rated = (String) map.get("RATED");
			String reviewDate = (String) map.get("REVIEW_DATE");
			System.out.println("리뷰번호: " + reviewNo.intValue() + "\t" + "리뷰내용: " + contente + "\t" + "별점: " + rated
					+ "\t" + "리뷰날짜: " + reviewDate);
		}
		int sel = ScanUtil.nextInt("1. 리뷰에대한 답글달기\n2. 내 강의 ");
		switch (sel) {
		case 1:
			return View.REPLY_REVIEW;
		case 2:
			return View.LECTURER_MYLECTURE;
		default:
			return View.LECTURER_MYLECTURE;
		}
	}

	private View lectureUpdate() {// 강의 수정하기 추가
		sessionStorage.get("lectureNum");
		String lecturename = ScanUtil.nextLine("강의 제목을 변경해주세요");
		String lecturecontent = ScanUtil.nextLine("강의 내용을 입력해주세요");
		int categoryno = ScanUtil.nextInt("카테고리번호를 입력해주세요");
		int levelno = ScanUtil.nextInt("난이도를 입력해주세요");
		int bookno = ScanUtil.nextInt("책 번호를 입력해주세요");
		List<Object> param = new ArrayList<Object>();
		param.add(lecturename);
		param.add(lecturecontent);
		param.add(categoryno);
		param.add(levelno);
		param.add(bookno);
		param.add(sessionStorage.get("lectureNum"));
		lectureService.lectureUpdate(param);
		System.out.println("수정이 완료되었습니다.");
		System.out.println("1. 내 강의 돌아가기\n2. 내 강의 목록보기\n3. 홈");

		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.LECTURER_MYLECTURE;
		case 2:
			return View.LECTURER_LECTURE_LIST;
		default:
			return View.LECTURER_HOME;
		}
	}

	private View lecturer_lecture_detail() {// 강의 자세히 검색하기 추가
		int lectureNum = ScanUtil.nextInt("자세히 검새할 강의를 입력해주세요.");
		sessionStorage.put("lectureNum", lectureNum);

		List<Object> param = new ArrayList<Object>();
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());
		param.add(lectureNum);

		System.out.println("-----------------------");
		Map<String, Object> map = lectureService.lecturer_lecture_detail(param);
		BigDecimal lecture_no = (BigDecimal) map.get("LECTURE_NO");
		String lecture_Name = (String) map.get("LECTURE_NAME");
		String lecture_content = (String) map.get("LECTURE_CONTENT");
		String category_name = (String) map.get("CATEGORY_NAME");
		String level_name = (String) map.get("LEVEL_NAME");
		String book_name = (String) map.get("BOOK_NAME");

		System.out.println(
				"강의번호: " + lecture_no.intValue() + "\t" + "강의명: " + lecture_Name + "\t" + "강의내용: " + lecture_content
						+ "\t" + "카테고리 이름: " + category_name + "\t" + "난이도: " + level_name + "\t" + "책이름" + book_name);

		System.out.println("1.강의 수정하기\n2. 리뷰보기");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.LECTURE_UPDATE;
		case 2:
			return View.LECTURER_REVIEW;
		default:
			return View.LECTURER_LECTURE_DETAIL;
		}

	}

	private View bookdelete() {// 책 삭제 추가
		int booksell = ScanUtil.nextInt("삭제할 책 번호를 입력해주세요");
		MainController.sessionStorage.put("book", booksell);
//		sessionStorage.get("bookNo");
		List<Object> param = new ArrayList<Object>();
		param.add((int) sessionStorage.get("book"));
		bookservice.bookDelete(booksell);
		System.out.println("정상적으로 삭제가 완료되었습니다.");
		return View.LECTURER_HOME;
	}

	private View bookList() {// 책 목록BOOK_LIST추가 0206 수정
		System.out.println("전체 책목록을 출력합니다.");

		List<Map<String, Object>> bookList = bookservice.bookList();
		System.out.println("------------------------");
		for (Map<String, Object> map : bookList) {
			BigDecimal bookno = (BigDecimal) map.get("BOOK_NO");
			String bookname = (String) map.get("BOOK_NAME");
			String bookcontent = (String) map.get("BOOK_CONTENT");
			String bookcategoryname = (String) map.get("BOOKCATEGORY_NAME");

			System.out.println("책번호: " + bookno.intValue() + "\t" + "책제목: " + bookname + "\t" + "책내용: " + bookcontent
					+ "\t" + "책 카테고리 이름: " + bookcategoryname);
		}
		System.out.println("1. 책 상세조회하기\n2. 책 등록하기");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.BOOK_DETAIL;
		case 2:
			return View.BOOK_INSERT;
		default :
			return View.HOME;
		}

	}

	private View lecturer_lecture_list() { // 강사님 강의조회하기 추가

		System.out.println("내 전체 강의리스트를 출력합니다. ");
		((UserVo) sessionStorage.get("user")).getUser_no();
		List<Object> param = new ArrayList<Object>();
		param.add(((UserVo) sessionStorage.get("user")).getUser_no());
		lectureService.lecturer_lecture_List(param);

		List<Map<String, Object>> lecturer_lectureList = lectureService.lecturer_lecture_List(param);
		System.out.println("-----------------------");
		for (Map<String, Object> map : lecturer_lectureList) {
			BigDecimal lecture_no = (BigDecimal) map.get("LECTURE_NO");
			String lecture_Name = (String) map.get("LECTURE_NAME");
			String lecture_content = (String) map.get("LECTURE_CONTENT");
			String category_name = (String) map.get("CATEGORY_NAME");
			String level_name = (String) map.get("LEVEL_NAME");
			String book_name = (String) map.get("BOOK_NAME");

			System.out.println("강의번호: " + lecture_no.intValue() + "\t" + "강의명: " + lecture_Name + "\t" + "강의내용: "
					+ lecture_content + "\t" + "카테고리 이름: " + category_name + "\t" + "난이도: " + level_name + "\t" + "책이름"
					+ book_name);
		}

		int sel = ScanUtil.nextInt("1. 강의 상세히 검색하기 \n2. 홈");
		switch (sel) {
		case 1:
			return View.LECTURER_LECTURE_DETAIL;
		case 2:
			return View.HOME;
		default:
			return View.HOME;
		}

	}

	private View lecturerMylecture() {// 강사님 내 강의실
		System.out.println("1. 내 강의검색 \n2. 강의 등록");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.LECTURER_LECTURE_LIST;// 수정
		case 2:
			return View.LECTURE_INSERT;
		default:
			return View.HOME;
		}
	}

	private View reviewDelete() {// 리뷰삭제하기
		System.out.println("리뷰삭제를 진행합니다.");
		sessionStorage.get("reviewNo");
		List<Object> param = new ArrayList();
		param.add(sessionStorage.get("reviewNo"));
		reviewService.reviewDelete(param);
		int sel = ScanUtil.nextInt("이동할 페이지를 선택해 주세요. \n1. 내 강의실 \n2. 홈");
		switch (sel) {
		case 1:
			return View.USER_MYLECTURE;
		case 2:
			return View.HOME;
		default:
			return View.HOME;
		}
	}

	// 0205수정
	private View reviewDetail() {// 라뷰자세히보기
		if (!sessionStorage.containsKey("reviewNo")) {
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
			return View.REVIEW_DELETE;
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

	private View bookInsert() {//0205 수정본 + 0206수정
		System.out.println("책 등록을 시작합니다");
		String bookName = ScanUtil.nextLine("책 이름을 입력해주세여");
		String bookContent = ScanUtil.nextLine("책 내용을 입력해주세요");
		int bookcategoryNo = ScanUtil.nextInt("책 종류를 적어주세여\n1. 웹개발 \n2.게임개발 \n3.DataBase");

		List<Object> param = new ArrayList<Object>();
		param.add(bookName);
		param.add(bookContent);
		param.add(bookcategoryNo);

		bookservice.bookInsert(param);
		System.out.println("성공적으로 책을 등록하셨습니다.");
		System.out.println("1. 책 리스트보기\n2. 내 강의");
		// 나중에 필요하면 이동위치 바꾸기
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.BOOK_LIST;
		case 2:
			return View.LECTURER_HOME;
		default:
			return View.LECTURER_HOME;
		}
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
			String userName = (String) map.get("USER_NAME");
			String lectureName = (String) map.get("LECTURE_NAME");
			String categoryName = (String) map.get("CATEGORY_NAME");
			String lectureStart = (String) map.get("LECTURE_START");
			String lectureFinish = (String) map.get("LECTURE_FINISH");
			System.out.println(userName + "\t" + lectureName + "\t" + categoryName);
			System.out.println(categoryName + "\t" + lectureStart + "\t" + lectureFinish);
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
		if (sel == 1) {
			String name = ScanUtil.nextLine("이름: ");
			param.add(name);
		}
		if (sel == 2) {
			List<LectureCategoryVo> cateList = lectureService.lectureCtegoryList();
			for (LectureCategoryVo l : cateList) {
				System.out.println(l);
			}
			int type = ScanUtil.nextInt("카테고리 번호 선택: ");// 인트로 형변환
			param.add(type);
		}
		if (sel == 3) {
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
		if (((UserVo) (sessionStorage.get("user"))).getDivi_no() == 1) {// 일반화원 구분
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
		int startNum = (page - 1) * 5 + 1;
		int endNum = startNum + 5 - 1;

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

	// 0205추가
	private View memDetail() {
		UserVo user = userService.memDetail((int) sessionStorage.get("memNo"));
		int user_no = user.getUser_no();
		String user_name = user.getUser_name();
		String user_id = user.getUser_id();
		String user_pass = user.getUser_pass();
		// null check
		String user_address = user.getUser_address();
		if (user_address == null || user_address.isEmpty()) {
			user_address = "N";
		}

		String user_hp = user.getUser_hp();
		String user_bir = user.getUser_bir();
		String join_date = user.getJoin_date();

		// 회원 유형
		String memType = "";
		if (user.getDivi_no() == 1) {
			memType = "일반회원";
		}
		if (user.getDivi_no() == 2) {
			memType = "강사회원";
		}

		// null 체크
		String delyn = user.getDelyn();
		if (delyn == null || delyn.isEmpty()) {
			delyn = "N";
		}

		System.out.println("--------------------회원 정보--------------------");
		System.out.printf(
				"회원 번호: %3d\t회원명: %s\n아이디: %s\t비밀번호: %s\n주소: %s\n핸드폰 번호: %s\n생년월일:%s\n가입일: %s\t회원 유형: %s\t삭제 여부: %s\n",
				user_no, user_name, user_id, user_pass, user_address, user_hp, user_bir, join_date, memType, delyn);
		System.out.println("----------------------------------------------");

		List<Object> param = new ArrayList<Object>();
		if (delyn.equals("N")) {
			System.out.println("1. 회원 삭제하기");
		}
		if (delyn.equals("Y")) {
			System.out.println("1. 회원 복구하기");
		}
		System.out.println("2 or 다른키. 회원 리스트로 이동");
		int sel = ScanUtil.menu();
		if (sel != 1) {
			return View.MEM_LIST;
		}
		if (sel == 1 && delyn.equals("Y")) {
			System.out.println(user_name + "회원을 복구하시겠습니까?\n1. 확인, 2 or 다른키. 취소(돌아가기)");
			int del = ScanUtil.menu();
			if (del != 1) {
				return View.MEM_DETAIL;
			}
			param.add("");
		}
		if (sel == 1 && delyn.equals("N")) {
			System.out.println(user_name + "회원을 삭제하시겠습니까?\n1. 확인, 2 or 다른키. 취소(돌아가기)");
			int del = ScanUtil.menu();
			if (del != 1) {
				return View.MEM_DETAIL;
			}
			param.add("Y");
		}
		param.add(user_id);
		userService.memDelyn(param);
		return View.MEM_LIST;
	}

	// 0205추가
	private View memList() {
		int page = 1;
		if (sessionStorage.containsKey("currentPage")) {
			page = (int) sessionStorage.get("currentPage");
		}
		int sPage = (page - 1) * 5 + 1;
		int ePage = sPage + 5 - 1;

		List<Object> param = new ArrayList<Object>();
		param.add(sPage);
		param.add(ePage);

		List<UserVo> userList = userService.memList(param, (int) sessionStorage.get("adminSel"));
		System.out.println("--------------------회원 목록--------------------");
		System.out.println("회원 번호\t회원명\t아이디\t비밀번호\t회원유형");
		for (UserVo user : userList) {
			// USER_NO, USER_ID, USER_PASS, USER_ADDRESS, USER_HP, USER_BIR, USER_NAME,
			// JOIN_DATE, DIVI_NO, DELYN
			int userNo = user.getUser_no();
			String userId = user.getUser_id();
			String userName = user.getUser_name();

			// 비밀번호 *로 변경 (6개까지만)
			String userPass = "";
			for (int i = 0; i < user.getUser_pass().length(); i++) {
				userPass += "*";
				if (i == 6)
					break;
			}
			String userType = "";
			if (user.getDivi_no() == 1)
				userType = "일반 회원";
			if (user.getDivi_no() == 2)
				userType = "강사 회원";
			System.out.println(userNo + "\t" + userName + "\t" + userId + "\t" + userPass + "\t" + userType);
		}
		System.out.println("----------------------------------------------");

		System.out.println("1. 이전 페이지\n2. 다음 페이지\n3. 회원 상세 정보\n4. home");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			if (page != 1) {
				page -= 1;
			} else {
				System.out.println("!!첫 페이지 입니다!!");
			}
			sessionStorage.put("currentPage", page);
			return View.MEM_LIST;
		case 2:
			int totalPages = (int) Math.ceil((double) userService.getMaxMem((int) sessionStorage.get("adminSel")) / 5);

			if (page != totalPages) {
				page += 1;
			} else {
				System.out.println("!마지막 페이지 입니다!!");
			}
			sessionStorage.put("currentPage", page);

			return View.MEM_LIST;
		case 3:
			int selMemNo = ScanUtil.nextInt("회원 번호를 선택하세요");
			sessionStorage.put("memNo", selMemNo);
			return View.MEM_DETAIL;
		case 4:
			sessionStorage.remove("adminSel");
			sessionStorage.remove("currentPage");
			return View.ADMIN_HOME;
		default:
			return View.MEM_LIST;
		}
	}

	// 0205추가
	private View adminLectureDetail() {
		Map<String, Object> lecture = lectureService.adminLectureDetail((int) sessionStorage.get("lecNo"));
		// LECTURE_NO, USER_NAME, LECTURE_NAME, LECTURE_CONTENT, CATEGORY_NAME,
		// LEVEL_NAME, BOOK_NAME, DELYN
		int lecture_no = ((BigDecimal) lecture.get("LECTURE_NO")).intValue();
		String user_name = (String) lecture.get("USER_NAME");
		String lecture_name = (String) lecture.get("LECTURE_NAME");
		String lecture_content = (String) lecture.get("LECTURE_CONTENT");
		String category_name = (String) lecture.get("CATEGORY_NAME");
		String level_name = (String) lecture.get("LEVEL_NAME");
		String book_name = (String) lecture.get("BOOK_NAME");
		String delyn = (String) lecture.get("DELYN");
		// 널체크
		if (delyn == null || delyn.isEmpty()) {
			delyn = "N";
		}
		// 강의내용 20자 띄워 쓰기
		StringBuilder formattedContent = new StringBuilder();
		for (int i = 0; i < lecture_content.length(); i++) {
			formattedContent.append(lecture_content.charAt(i));
			if ((i + 1) % 25 == 0) {
				formattedContent.append("\n\t");
			}
		}
		lecture_content = formattedContent.toString();

		System.out.println("--------------------------강의 정보--------------------------");
		System.out.println("강의번호: " + lecture_no + "\t|강사명: " + user_name);
		System.out.println("강의명: " + lecture_name);
		System.out.println("강의내용:\t" + lecture_content);
		System.out.println("난이도: " + level_name + "\t|강의 유형: " + category_name);
		System.out.println("책: " + book_name + "\t|삭제여부: " + delyn);
		System.out.println("----------------------------------------------------------");

		List<Object> param = new ArrayList<Object>();
		if (delyn.equals("N")) {
			System.out.println("1. 강의 삭제하기");
		}
		if (delyn.equals("Y")) {
			System.out.println("1. 강의 복구하기");
		}
		System.out.println("2 or 다른키. 강의 리스트로 이동");
		int sel = ScanUtil.menu();
		if (sel != 1) {
			return View.ADMIN_LECTURE_LIST;
		}
		if (sel == 1 && delyn.equals("Y")) {
			System.out.println("강의를 복구하시겠습니까?\n1. 확인, 2 or 다른키. 취소(돌아가기)");
			int del = ScanUtil.menu();
			if (del != 1) {
				return View.ADMIN_LECTURE_DETAIL;
			}
			param.add("");
		}
		if (sel == 1 && delyn.equals("N")) {
			System.out.println("강의를 삭제하시겠습니까?\n1. 확인, 2 or 다른키. 취소(돌아가기)");
			int del = ScanUtil.menu();
			if (del != 1) {
				return View.ADMIN_LECTURE_DETAIL;
			}
			param.add("Y");
		}
		param.add(lecture_no);
		lectureService.lectureDelyn(param);
		return View.ADMIN_LECTURE_LIST;
	}

	// 0205추가
	private View adminLectureList() {
		int page = 1;
		if (sessionStorage.containsKey("currentPage")) {
			page = (int) sessionStorage.get("currentPage");
		}
		int sPage = (page - 1) * 5 + 1;
		int ePage = sPage + 5 - 1;

		List<Object> param = new ArrayList<Object>();
		param.add(sPage);
		param.add(ePage);

		List<LectureVo> lectureList = lectureService.adminLectureList(param);

		System.out.println("-------------------------------강의 목록-------------------------------");
		System.out.println("강의 번호\t강의명\t\t강의내용\t\t\t강의유형\t강의레벨");
		for (LectureVo lecture : lectureList) {
			int lecture_no = lecture.getLecture_no();
			String lecture_name = lecture.getLecture_name();
			lecture_name = lecture_name.substring(0, 6) + "...";
			String lecture_content = lecture.getLecture_content();
			lecture_content = lecture_content.substring(0, 14) + "...";
			// 제목 내용 서브 스트링 하기
			int category_no = lecture.getCategory_no();
			int level_no = lecture.getLevel_no();
			String levelName = "";
			switch (level_no) {
			case 1:
				levelName = "Beginner";
				break;
			case 2:
				levelName = "Basic";
				break;
			case 3:
				levelName = "Middle";
				break;
			case 4:
				levelName = "Expert";
				break;
			}
			System.out.println(
					lecture_no + "\t" + lecture_name + "\t" + lecture_content + "\t" + category_no + "\t" + levelName);
		}
		System.out.println("--------------------------------------------------------------------");

		System.out.println("1. 이전 페이지\n2. 다음 페이지\n3. 강의 상세 정보\n4. home");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			if (page != 1) {
				page -= 1;
			} else {
				System.out.println("!!첫 페이지 입니다!!");
			}
			sessionStorage.put("currentPage", page);
			return View.ADMIN_LECTURE_LIST;
		case 2:
			int totalPages = (int) Math.ceil((double) lectureService.getMaxPage() / 5);
			if (page != totalPages) {
				page += 1;
			} else {
				System.out.println("!!마지막 페이지 입니다!!");
			}
			sessionStorage.put("currentPage", page);
			return View.ADMIN_LECTURE_LIST;
		case 3:
			int lecNo = ScanUtil.nextInt("강의 번호를 선택하세요: ");
			sessionStorage.put("lecNo", lecNo);
			return View.ADMIN_LECTURE_DETAIL;
		case 4:
			sessionStorage.remove("currentPage");
			return View.ADMIN_HOME;
		default:
			return View.ADMIN_LECTURE_LIST;
		}
	}

	// 0205추가
	private View adminReviewDetail() {
		Map<String, Object> review = reviewService.adminReviewDetail((int) sessionStorage.get("revNo"));
		// REVIEW_NO, CONTENT, RATED, REVIEW_DATE, AS USER_NAME, LECTURE_NO, DELYN
		int review_no = ((BigDecimal) review.get("REVIEW_NO")).intValue();
		String content = (String) review.get("CONTENT");
		String rated = (String) review.get("RATED");
		String review_date = (String) review.get("REVIEW_DATE");
		String user_name = (String) review.get("USER_NAME");
		int lecture_no = ((BigDecimal) review.get("LECTURE_NO")).intValue();
		String delyn = (String) review.get("DELYN");
		if (delyn == null || delyn.isEmpty()) {
			delyn = "N";
		}

		System.out.println("---------------------리뷰 정보---------------------");
		System.out.println("|리뷰번호: " + review_no + "\t|작성자: " + user_name);
		System.out.println("|작성일: " + review_date);
		System.out.println("|리뷰내용:\t" + content);
		System.out.println("|강의번호: " + lecture_no + "\t|평점: " + rated + "\t|삭제여부: " + delyn);
		System.out.println("------------------------------------------------");

		ReplyVo reply = (ReplyVo) replyService.adminGetReply(review_no);
		if (reply != null) {
			System.out.println("---------------------답글 정보---------------------");
			System.out.println("작성일: " + reply.getReply_date());
			System.out.println("작성 내용: " + reply.getReply_content());
			System.out.println("------------------------------------------------");
		}

		List<Object> param = new ArrayList<Object>();
		if (delyn.equals("N")) {
			System.out.println("1. 리뷰 삭제하기");
		}
		if (delyn.equals("Y")) {
			System.out.println("1. 리뷰 복구하기");
		}
		System.out.println("2 or 다른키. 리뷰 리스트로 이동");
		int sel = ScanUtil.menu();
		if (sel != 1) {
			return View.ADMIN_REVIEW_LIST;
		}
		if (sel == 1 && delyn.equals("Y")) {
			System.out.println("리뷰를 복구하시겠습니까?\n1. 확인, 2 or 다른키. 취소(돌아가기)");
			int del = ScanUtil.menu();
			if (del != 1) {
				return View.ADMIN_REVIEW_DETAIL;
			}
			param.add("");
		}
		if (sel == 1 && delyn.equals("N")) {
			System.out.println("리뷰를 삭제하시겠습니까?\n1. 확인, 2 or 다른키. 취소(돌아가기)");
			int del = ScanUtil.menu();
			if (del != 1) {
				return View.ADMIN_REVIEW_DETAIL;
			}
			param.add("Y");
		}
		param.add(review_no);
		reviewService.reviewDelyn(param);
		return View.ADMIN_REVIEW_DETAIL;
	}

	// 0205추가
	private View adminReviewList() {

		int page = 1;
		if (sessionStorage.containsKey("currentPage")) {
			page = (int) sessionStorage.get("currentPage");
		}
		int sPage = (page - 1) * 5 + 1;
		int ePage = sPage + 5 - 1;

		List<Object> param = new ArrayList<Object>();
		param.add(sPage);
		param.add(ePage);

		List<ReviewVo> reviewList = reviewService.adminReviewList(param);
		System.out.println("----------------------------리뷰 목록----------------------------");
		System.out.println("|리뷰  번호\t|작성자\t|강의번호\t|리뷰내용\t\t|작성일\t\t|삭제여부");
		for (ReviewVo review : reviewList) {
			int review_no = review.getReview_no();
			String content = review.getContent();
			content = content.substring(0, 5) + "...";

			String review_date = review.getReview_date();
			int user_no = review.getUser_no();
			int lecture_no = review.getLecture_no();
			String delyn = review.getDelyn();
			if (delyn == null || delyn.isEmpty()) {
				// 변수가 null이거나 빈 문자열인 경우 처리할 코드
				delyn = "N";
			}

			// 출력
			System.out.println("|" + review_no + "\t|" + user_no + "\t|" + lecture_no + "\t|" + content + "\t|"
					+ review_date + "\t|" + delyn);
		}
		System.out.println("--------------------------------------------------------------");

		// 페이지 변경
		System.out.println("1. 이전 페이지\n2. 다음 페이지\n3. 리뷰 상세 정보\n4. home");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			if (page != 1) {
				page -= 1;
			} else {
				System.out.println("!!첫 페이지 입니다!!");
			}
			sessionStorage.put("currentPage", page);
			return View.ADMIN_REVIEW_LIST;
		case 2:
			int totalPages = (int) Math.ceil((double) reviewService.getMaxReview() / 5);
			if (page != totalPages) {
				page += 1;
			} else {
				System.out.println("!!마지막 페이지 입니다!!");
			}
			sessionStorage.put("currentPage", page);
			return View.ADMIN_REVIEW_LIST;
		case 3:
			int revNo = ScanUtil.nextInt("리뷰 번호를 선택하세요: ");
			sessionStorage.put("revNo", revNo);
			sessionStorage.remove("currentPage");
			return View.ADMIN_REVIEW_DETAIL;
		case 4:
			sessionStorage.remove("currentPage");
			sessionStorage.remove("revNo");
			return View.ADMIN_HOME;
		default:
			return View.ADMIN_REVIEW_LIST;
		}
	}

	// 0205추가
	private View adminHome() {
		System.out.println("1. 일반회원 조회하기");
		System.out.println("2. 강사 조회하기");
		System.out.println("3. 강의 조회하기");
		System.out.println("4. 리뷰 조회하기");
		System.out.println("5. 로그아웃");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
		case 2:
			sessionStorage.put("adminSel", sel);
			return View.MEM_LIST;
		case 3:
			return View.ADMIN_LECTURE_LIST;
		case 4:
			return View.ADMIN_REVIEW_LIST;
		case 5:
			sessionStorage.clear();
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