package service;

import java.util.List;
import java.util.Map;

import dao.LectureDao;
import vo.LectureCategoryVo;
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

	public List<Map<String, Object>> lectureList(List<Object> param, int sel) {
		return dao.lectureList(param, sel);
	}
	
	//OVERIDING
	public List<Map<String, Object>> lectureList(List<Object> param) {
		return dao.lectureList(param);
	}

	public List<LectureCategoryVo> lectureCtegoryList() {
		return dao.lectureCtegoryList();
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
	
	public int getMaxPage() {
		return dao.getMaxPage();
	}
	
	public List<LectureVo> adminLectureList(List<Object> param) {		
		return dao.adminLectureList(param);
	}
	
	public Map<String, Object> adminLectureDetail(int sel) {
		return dao.adminLectureDetail(sel);
	}
	
	public void lectureDelyn(List<Object> param){
		dao.lectureDelyn(param);
	}
	
	public List<Map<String,Object>> lecturer_lecture_List(List<Object> param) {
		return dao.lecturer_lecture_List(param);
	}
	public Map<String,Object> lecturer_lecture_detail(List<Object> param) {
		return dao.lecturer_lecture_detail(param);
	}
	public void lectureUpdate(List<Object> param) {
		 dao.lectureUpdate(param);
	}

}