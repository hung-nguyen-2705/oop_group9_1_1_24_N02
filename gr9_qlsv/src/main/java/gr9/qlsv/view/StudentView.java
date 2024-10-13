
package gr9.qlsv.view;

import gr9.qlsv.controller.StudentController;
import gr9.qlsv.entity.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentView extends JFrame {
    private StudentController studentController = new StudentController();
    private List<Student> students = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public StudentView() {
        setTitle("Quản lý sinh viên");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        students = studentController.getAllStudents();
        JList<String> studentList = new JList<>(listModel);
        updateStudentList(students);

        JTextField idField = new JTextField(5);
        JTextField nameField = new JTextField(10);
        JTextField emailField = new JTextField(10);
        JTextField gpaField = new JTextField(5);
        JTextField addressField = new JTextField(10);
        JTextField searchField = new JTextField(10);
        JButton addButton = new JButton("Thêm");
        JButton saveButton = new JButton("Lưu");
        JButton searchButton = new JButton("Tìm kiếm");

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Mã SV:"));
        panel.add(idField);
        panel.add(new JLabel("Tên SV:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("GPA:"));
        panel.add(gpaField);
        panel.add(new JLabel("Địa chỉ:"));
        panel.add(addressField);
        panel.add(addButton);
        panel.add(saveButton);

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        add(new JScrollPane(studentList), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        add(searchPanel, BorderLayout.NORTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String email = emailField.getText();
                double gpa = Double.parseDouble(gpaField.getText());
                String address = addressField.getText();

                Student student = new Student(id, name, email, gpa, address);
                students.add(student);
                updateStudentList(students);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentController.saveStudents(students);
                JOptionPane.showMessageDialog(null, "Dữ liệu sinh viên đã được lưu!");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText().toLowerCase();
                List<Student> result = new ArrayList<>();
                for (Student student : students) {
                    if (student.getId().toLowerCase().contains(keyword) || 
                        student.getName().toLowerCase().contains(keyword)) {
                        result.add(student);
                    }
                }
                updateStudentList(result);
            }
        });

        setVisible(true);
    }

    private void updateStudentList(List<Student> students) {
        listModel.clear();
        for (Student student : students) {
            listModel.addElement(student.getId() + " - " + student.getName() + " - GPA: " + student.getGpa() + " - Địa chỉ: " + student.getAddress());
        }
    }
}
