package service;

import java.util.List;
import java.util.Map;

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
	
	public int bookInsert(List<Object> param) {
		return dao.bookInsert(param);
	}
	
	public List<Map<String,Object>> bookList() {
		return dao.bookList();
	}
	public void bookDelete(int booksell) {
		 dao.bookDelete(booksell);
	}
	
	public Map<String,Object> bookdetail(List<Object> param) {
		return dao.bookdetail(param);
	}
	public void bookUpdate(List<Object> param) {
		 dao.bookUpdate(param);
	}
	
}