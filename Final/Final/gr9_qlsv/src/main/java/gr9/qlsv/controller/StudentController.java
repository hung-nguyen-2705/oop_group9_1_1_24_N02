package gr9.qlsv.controller;

import gr9.qlsv.dao.UserDao;
import gr9.qlsv.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private UserDao userDao = new UserDao();

    public List<Student> getAllStudents() {
        return userDao.readStudents();
    }

    public void saveStudents(List<Student> students) {
        userDao.writeStudents(students);
    }
}
