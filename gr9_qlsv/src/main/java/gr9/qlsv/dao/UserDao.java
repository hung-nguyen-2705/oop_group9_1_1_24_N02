package gr9.qlsv.dao;

import gr9.qlsv.entity.User;

public class UserDao {
    public User getAdminUser() {
        return new User("admin", "1");
    }
}
