package dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
	
	public int bookInsert(List<Object> param) {
		String sql = " SELECT MAX(BOOK_NO)+1 AS BOOK_NO FROM BOOK ";
		Map<String, Object> map = jdbc.selectOne(sql);
		int book_no = ((BigDecimal)map.get("BOOK_NO")).intValue();
		
		sql = "  INSERT INTO BOOK(BOOK_NO,BOOK_NAME,BOOK_CONTENT,BOOKCATEGORY_NO)\r\n" + 
				"VALUES((SELECT MAX(BOOK_NO)+1 FROM BOOK),?,?,?) ";
		jdbc.update(sql, param);
		
		return book_no;
	}
	
	public List<Map<String, Object>> bookList() { 
		String sql = "  SELECT B.BOOK_NO AS BOOK_NO,\r\n" + 
				"       B.BOOK_NAME AS BOOK_NAME,\r\n" + 
				"       B.BOOK_CONTENT AS BOOK_CONTENT,\r\n" + 
				"       BC.BOOKCATEGORY_NAME  AS BOOKCATEGORY_NAME\r\n" + 
				"FROM BOOK B , BOOK_CATEGORY BC\r\n" + 
				"WHERE B.BOOKCATEGORY_NO = BC.BOOKCATEGORY_NO\r\n" + 
				"AND B.BOOK_DELYN IS NULL ORDER BY BOOK_NO ";
		return jdbc.selectList(sql);
	}
	
	public void bookDelete(int booksell) {
		String sql = " UPDATE BOOK\r\n" + 
				"SET BOOK_DELYN = 'Y'\r\n" + 
				"WHERE BOOK_NO = " + booksell;
		jdbc.update(sql);
	}

	public Map<String, Object> bookdetail(List<Object> param) { 
		String sql = "  SELECT B.BOOK_NO AS BOOK_NO,\r\n" + 
				"B.BOOK_NAME AS BOOK_NAME,\r\n" + 
				" B.BOOK_CONTENT AS BOOK_CONTENT, \r\n" + 
				"BC.BOOKCATEGORY_NAME  AS BOOKCATEGORY_NAME\r\n" + 
				"FROM BOOK B , BOOK_CATEGORY BC\r\n" + 
				"WHERE B.BOOKCATEGORY_NO = BC.BOOKCATEGORY_NO\r\n" + 
				"AND B.BOOK_DELYN IS NULL\r\n" + 
				"AND BOOK_NO = ?  ";
		return jdbc.selectOne(sql, param);
	}
	
	public void bookUpdate(List<Object> param) { 
		String sql = "UPDATE BOOK\r\n" + 
				"SET BOOK_NAME = ? , BOOK_CONTENT = ?, BOOKCATEGORY_NO = ?\r\n" + 
				"WHERE BOOK_NO = ? ";
		jdbc.update(sql,param);
	}
	
}