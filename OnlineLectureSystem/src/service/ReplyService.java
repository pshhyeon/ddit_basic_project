package service;

import java.util.List;
import java.util.Map;

import dao.ReplyDao;
import vo.ReplyVo;

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

	public ReplyVo adminGetReply(int re) {
		return dao.adminGetReply(re);
	}

	public void replyInsert(List<Object> param) {
		dao.replyInsert(param);
	}

	public List<Map<String, Object>> replyList(List<Object> param) {
		return dao.replyList(param);
	}
	
	public void replyUpdate(List<Object> param) {
		dao.replyUpdate(param);
	}

}
