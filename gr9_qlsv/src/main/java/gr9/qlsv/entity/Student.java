// src/main/java/gr9/qlsv/entity/Student.java
package gr9.qlsv.entity;

public class Student {
    private String id;
    private String name;
    private String email;
    private double gpa;
    private String address;

    public Student(String id, String name, String email, double gpa, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gpa = gpa;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getGpa() {
        return gpa;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + email + "," + gpa + "," + address;
    }
}
