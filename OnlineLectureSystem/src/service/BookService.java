package service;

import java.util.List;

import dao.BookDao;

public class BookService {
	private static BookService instance = null;

	private BookService() {
	}

	public static BookService getInstance() {
		if (instance == null) {
			instance = new BookService();
		}
		return instance;
	}

	BookDao dao = BookDao.getInstance();
	
	public void bookInsert(List<Object> param) {
		dao.bookInsert(param);
	}
}
