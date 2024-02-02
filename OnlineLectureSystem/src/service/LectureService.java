package service;

import java.util.List;
import java.util.Map;

import dao.LectureDao;

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
	
	public List<Map<String,Object>> lectureList() {
		return dao.lectureList();
	}

}



