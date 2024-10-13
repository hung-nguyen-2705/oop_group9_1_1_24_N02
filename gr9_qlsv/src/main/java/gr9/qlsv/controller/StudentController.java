
package gr9.qlsv.controller;

import gr9.qlsv.dao.StudentDao;
import gr9.qlsv.entity.Student;

import java.util.List;

public class StudentController {
    private StudentDao studentDao = new StudentDao();

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public void saveStudents(List<Student> students) {
        studentDao.saveStudents(students);
    }
}
