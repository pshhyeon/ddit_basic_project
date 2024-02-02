package service;

import java.util.List;
<<<<<<< HEAD

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
=======
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
	
	
	
	LectureDao ldao = LectureDao.getInstance();
	
	public List<Map<String,Object>> lectureList() {
		return ldao.lectureList();
	}

}
>>>>>>> refs/remotes/origin/shun
