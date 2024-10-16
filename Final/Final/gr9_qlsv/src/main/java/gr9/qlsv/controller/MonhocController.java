package gr9.qlsv.controller;

import gr9.qlsv.dao.MonhocDao;
import gr9.qlsv.entity.Monhoc;
import gr9.qlsv.view.LoginView;

import java.util.ArrayList;
import java.util.List;

public class MonhocController {
    private MonhocDao monhocDao = new MonhocDao();

    public List<Monhoc> getAllMonhoc(String user) {
        return monhocDao.readStudents(user);
    }

    public void saveMonhoc(List<Monhoc> monhoc) {
        monhocDao.writeStudents(monhoc);
    }
}
