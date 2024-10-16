package gr9.qlsv.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Account {
	 private final String ADMIN_FILE_NAME = "account.txt";
	  
	 public String getAccount(String username,String password) {
	        String accountInfo = null;
	        try (BufferedReader br = new BufferedReader(new FileReader(ADMIN_FILE_NAME))) {
	        	 String line;
	             while ((line = br.readLine()) != null) {
	                 // Giả sử mỗi dòng trong tệp có định dạng: username,password
	                 String[] parts = line.split(",");
	                 if (parts.length == 2) {
	                     String fileUsername = parts[0].trim();
	                     String filePassword = parts[1].trim();

	                     // Kiểm tra tên người dùng và mật khẩu
	                     if (fileUsername.equals(username) && filePassword.equals(password)) {
	                         accountInfo = line; // Trả về thông tin tài khoản
	                         break; // Thoát vòng lặp nếu tìm thấy
	                     }
	                 }
	             }
	         } catch (IOException e) {
	             e.printStackTrace();
	         }

	        return accountInfo; // Trả về thông tin người dùng quản trị viên
	    }
}
