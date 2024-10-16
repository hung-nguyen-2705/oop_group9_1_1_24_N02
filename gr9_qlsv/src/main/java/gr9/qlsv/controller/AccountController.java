package gr9.qlsv.controller;
import gr9.qlsv.dao.Account;
import gr9.qlsv.dao.UserDao;

public class AccountController {
	 private Account account1 = new Account();

	    public boolean authenticate(String username, String password) {
	        String accountInfo = account1.getAccount(username, password);
	        // Giả sử thông tin người dùng quản trị viên được lưu theo định dạng: "username,password"
	        if (accountInfo != null) {
	            String[] parts = accountInfo.split(",");
	            return parts[0].equals(username) && parts[1].equals(password);
	        }
	        return false; // Trả về false nếu không tìm thấy thông tin người dùng
	    }
}
