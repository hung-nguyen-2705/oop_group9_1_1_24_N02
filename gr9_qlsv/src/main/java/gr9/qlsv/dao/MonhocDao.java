package gr9.qlsv.dao;

import gr9.qlsv.entity.Monhoc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MonhocDao {
	
    private static final String FILE_NAME = "C:\\Users\\bangd\\Desktop\\L\\";
    private static final String MONHOC_FILE_NAME = FILE_NAME + "Monhoc.txt";
    // Thêm tệp chứa thông tin người dùng quản trị viên
    private final String ADMIN_FILE_NAME = "C:\\Users\\bangd\\Desktop\\oop_group9_1_1_24_N02\\gr9_qlsv\\src\\main\\java\\admin.txt";

    public List<Monhoc> readMonhocs(String user) {
        List<Monhoc> subjects = new ArrayList<>();
  
        String userFileName = FILE_NAME + user + ".txt";
        File file = new File(userFileName);

        
        String fileToRead = file.exists() ? userFileName : MONHOC_FILE_NAME;
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                   Monhoc monhoc = new Monhoc(parts[0], parts[1], parts[2], parts[3]);
                   subjects.add(monhoc);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public void writeStudents(List<Monhoc> subjects) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Monhoc monhoc : subjects) {
                bw.write(monhoc.getId() + "," + monhoc.getName() + "," + "," + monhoc.getTeacher() + "," + monhoc.getAddress());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
