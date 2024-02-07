package service;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import dao.ReviewDao;
import vo.ReviewVo;
import vo.SwearWordsVo;

public class ReviewService {
	private static ReviewService instance = null;

	private ReviewService() {
	}

	public static ReviewService getInstance() {
		if (instance == null) {
			instance = new ReviewService();
		}
		return instance;
	}

	ReviewDao dao = ReviewDao.getInstance();

	public void makingReview(List<Object> param) {
		dao.makingReview(param);
	}

	public List<ReviewVo> reviewList(int userNo) {
		return dao.reviewlist(userNo);
	}

	public int reviewUpdate(List<Object> param) {
		return dao.reviewUpdate(param);
	}

	public ReviewVo reviewChk(List<Object> param) {
		return dao.reviewChk(param);
	}

	public ReviewVo reviewDetail(int reviewnum) {
		return dao.reviewDetail(reviewnum);
	}

	public void reviewDelete(List<Object> param) {
		dao.reviewDelete(param);
	}

	public List<Map<String, Object>> lectureReview(List<Object> param) {
		return dao.lectureReview(param);
	}

	public List<ReviewVo> adminReviewList(List<Object> param) {
		return dao.adminReviewList(param);
	}

	public int getMaxReview() {
		return dao.getMaxReview();
	}

	public Map<String, Object> adminReviewDetail(int sel) {
		return dao.adminReviewDetail(sel);
	}

	public void reviewDelyn(List<Object> param) {
		dao.reviewDelyn(param);
	}

	public void violentReviewDel() {
		dao.violentReviewDel();
	}

}