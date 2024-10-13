package gr9.qlsv.controller;

import gr9.qlsv.dao.UserDao;
import gr9.qlsv.entity.User;

public class LoginController {
    private UserDao userDao = new UserDao();

    public boolean authenticate(String username, String password) {
        User adminUser = userDao.getAdminUser();
        return adminUser.getUsername().equals(username) && adminUser.getPassword().equals(password);
    }
}