package gr9.qlsv.dao;

import gr9.qlsv.entity.Student;
import gr9.qlsv.utils.FileUtils;

import java.util.List;

public class StudentDao {
    private static final String FILE_PATH = "student.txt";

    public List<Student> getAllStudents() {
        return FileUtils.readStudentsFromFile(FILE_PATH);
    }

    public void saveStudents(List<Student> students) {
        FileUtils.writeStudentsToFile(FILE_PATH, students);
    }
}
