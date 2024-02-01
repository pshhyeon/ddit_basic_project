package service;

import java.util.List;

import controller.MainController;
import dao.UserDao;
import util.View;
import vo.UserVo;

public class UserService {
	private static UserService instance = null;

	private UserService() {
	}

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	UserDao userDao = UserDao.getInstance();

