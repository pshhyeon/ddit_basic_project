package dao;

import java.util.List;

import util.JDBCUtil;

public class BookDao {
	private static BookDao instance = null;

	private BookDao() {
	}

	public static BookDao getInstance() {
		if (instance == null) {
			instance = new BookDao();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public void bookInsert(List<Object> param) {
		String sql = "  INSERT INTO BOOK(BOOK_NO,BOOK_NAME,BOOK_CONTENT,BOOKCATEGORY_NO)\r\n" + 
				"VALUES((SELECT MAX(BOOK_NO)+1 FROM BOOK),?,?,?) ";
		jdbc.update(sql, param);

	}

}