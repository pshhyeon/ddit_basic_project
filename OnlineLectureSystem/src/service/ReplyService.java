package service;

import java.util.List;
import java.util.Map;

import dao.ReplyDao;

public class ReplyService {
	private static ReplyService instance = null;

	private ReplyService() {
	}

	public static ReplyService getInstance() {
		if (instance == null) {
			instance = new ReplyService();
		}
		return instance;
	}

	ReplyDao dao = ReplyDao.getInstance();
	
	public void replyInsert(List<Object> param) {
		dao.replyInsert(param);
	}
	public List<Map<String, Object>> replyList(List<Object> param) {
		return dao.replyList(param);
	}
	
}
