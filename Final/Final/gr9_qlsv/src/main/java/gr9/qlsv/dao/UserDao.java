package gr9.qlsv.dao;

import gr9.qlsv.entity.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final String FILE_NAME = "students.txt";

    // Thêm tệp chứa thông tin người dùng quản trị viên
    private final String ADMIN_FILE_NAME = "admin.txt";

    public List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Student student = new Student(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), parts[4]);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void writeStudents(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                bw.write(student.getId() + "," + student.getName() + "," + student.getEmail() + "," + student.getGpa() + "," + student.getAddress());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương thức lấy thông tin người dùng quản trị viên
    public String getAdminUser() {
        String adminInfo = null;
        try (BufferedReader br = new BufferedReader(new FileReader(ADMIN_FILE_NAME))) {
            adminInfo = br.readLine(); // Đọc thông tin người dùng quản trị viên từ tệp
        } catch (IOException e) {
            e.printStackTrace();
        }
        return adminInfo; // Trả về thông tin người dùng quản trị viên
    }
}
