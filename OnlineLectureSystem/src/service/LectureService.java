package service;

import java.util.List;

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
	
	
	
	LectureDao ldao = LectureDao.getInstance();
	
	public List<LectureVo> lectureList() {
		return ldao.lectureList();
	}

}
