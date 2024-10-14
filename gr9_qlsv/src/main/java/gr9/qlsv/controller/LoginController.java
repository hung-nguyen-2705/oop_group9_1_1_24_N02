package gr9.qlsv.controller;

import gr9.qlsv.dao.UserDao;

public class LoginController {
    private UserDao userDao = new UserDao();

    public boolean authenticate(String username, String password) {
        String adminInfo = userDao.getAdminUser();
        // Giả sử thông tin người dùng quản trị viên được lưu theo định dạng: "username,password"
        if (adminInfo != null) {
            String[] parts = adminInfo.split(",");
            return parts[0].equals(username) && parts[1].equals(password);
        }
        return false; // Trả về false nếu không tìm thấy thông tin người dùng
    }
}
