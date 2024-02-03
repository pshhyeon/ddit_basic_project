package service;

import java.util.List;
import java.util.Map;

import dao.LectureDao;
import vo.LectureVo;

public class LectureService {
	private static LectureService instance = null;

	private LectureService() {
	}

	public static LectureService getInstance() {
		if (instance == null) {
			instance = new LectureService();
		}
		return instance;
	}

	LectureDao dao = LectureDao.getInstance();

	public List<Map<String, Object>> lectureList() {
		return dao.lectureList();
	}

	public Map<String, Object> lectureDetail(List<Object> param) {
		return dao.lectureDetail(param);
	}

	public void lectureApply(List<Object> param) {
		dao.lectureApply(param);
	}

	public List<Map<String, Object>> lectureApplyList(List<Object> param) {
		return dao.lectureApplyList(param);
	}

	public List<Map<String, Object>> pastlectureApplyList(List<Object> param) {
		return dao.pastlectureApplyList(param);
	}

	public void lectureCancel(List<Object> param) {
		dao.lectureCancel(param);
	}

	public void lectureInsert(List<Object> param) {
		dao.lectureInsert(param);
	}


}