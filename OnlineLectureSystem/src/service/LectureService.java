package service;


import java.util.List;
import java.util.Map;

import dao.LectureDao;
import vo.LectureCategoryVo;

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

	public List<Map<String, Object>> lectureList(List<Object> param, int sel) {
		return dao.lectureList(param, sel);
	}

	public List<LectureCategoryVo> lectureCtegoryList() {
		return dao.lectureCtegoryList();
	}

	public Map<String, Object> lectureDetail(List<Object> param) {
		return dao.lectureDetail(param);
	}

}
