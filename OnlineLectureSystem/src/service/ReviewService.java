package service;

import java.util.List;

import dao.ReviewDao;
import vo.ReviewVo;

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


}