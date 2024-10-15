package gr9.qlsv.view;

import gr9.qlsv.controller.StudentController;
import gr9.qlsv.entity.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentView extends JFrame {
    private StudentController studentController = new StudentController();
    private List<Student> students = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    // Các trường nhập
    private JTextField idField = new JTextField(5);
    private JTextField nameField = new JTextField(10);
    private JTextField emailField = new JTextField(10);
    private JTextField gpaField = new JTextField(5);
    private JTextField addressField = new JTextField(10);
    private JTextField searchField = new JTextField(10); // Trường tìm kiếm

    // Danh sách sinh viên hiển thị
    private JList<String> studentList = new JList<>(listModel);

    public StudentView() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\gr9_qlsv\\acess\\Icon-CTy-Tap-doan-Phuong-Hoan.png"));
        setTitle("Quản lý sinh viên");
        setSize(598, 501);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        students = studentController.getAllStudents();
        updateStudentList(students);

        // Tạo các nút
        JButton addButton = new JButton("Thêm");
        addButton.setBounds(0, 138, 292, 23);
        JButton saveButton = new JButton("Lưu");
        saveButton.setBounds(240, 207, 107, 23);
        JButton deleteButton = new JButton("Xóa");
        deleteButton.setBounds(0, 184, 292, 23);
        JButton editButton = new JButton("Sửa");
        editButton.setBounds(0, 160, 292, 23);
        JButton sortByNameButton = new JButton("Sắp xếp theo tên");
        sortByNameButton.setBounds(292, 138, 292, 23);
        JButton sortByGpaAscButton = new JButton("Sắp xếp GPA tăng dần");
        sortByGpaAscButton.setBounds(292, 160, 292, 23);
        JButton sortByGpaDescButton = new JButton("Sắp xếp GPA giảm dần");
        sortByGpaDescButton.setBounds(292, 184, 292, 23);
        JButton searchButton = new JButton("Tìm kiếm"); // Nút tìm kiếm
        searchButton.setBounds(449, 0, 135, 23);

        // Tạo layout
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 584, 230);
        panel.setLayout(null);
        JLabel label = new JLabel("Mã SV:");
        label.setBounds(0, 24, 292, 23);
        panel.add(label);
        idField.setBounds(292, 24, 292, 23);
        panel.add(idField);
        JLabel label_1 = new JLabel("Tên SV:");
        label_1.setBounds(0, 47, 292, 23);
        panel.add(label_1);
        nameField.setBounds(292, 47, 292, 23);
        panel.add(nameField);
        JLabel label_2 = new JLabel("Email:");
        label_2.setBounds(0, 70, 292, 23);
        panel.add(label_2);
        emailField.setBounds(292, 70, 292, 23);
        panel.add(emailField);
        JLabel label_3 = new JLabel("GPA:");
        label_3.setBounds(0, 93, 292, 23);
        panel.add(label_3);
        gpaField.setBounds(292, 93, 292, 23);
        panel.add(gpaField);
        JLabel label_4 = new JLabel("Địa chỉ:");
        label_4.setBounds(0, 116, 292, 23);
        panel.add(label_4);
        addressField.setBounds(292, 116, 292, 23);
        panel.add(addressField);
        JLabel label_5 = new JLabel("Tìm kiếm:");
        label_5.setBounds(0, 0, 68, 23);
        panel.add(label_5); // Nhãn tìm kiếm
        searchField.setText("nhập id hoặc tên, địa chỉ để tìm kiếm");
        searchField.setBounds(52, 0, 395, 23);
        panel.add(searchField); // Trường tìm kiếm
        panel.add(addButton);
        panel.add(saveButton);
        panel.add(deleteButton);
        panel.add(editButton);
        panel.add(sortByNameButton);
        panel.add(sortByGpaAscButton);
        panel.add(sortByGpaDescButton);
        panel.add(searchButton); // Thêm nút tìm kiếm

        // Tạo danh sách sinh viên
        JScrollPane scrollPane = new JScrollPane(studentList);
        scrollPane.setBounds(0, 230, 584, 231);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getContentPane().setLayout(null);

        // Thêm panel và danh sách vào frame
        getContentPane().add(panel);
        getContentPane().add(scrollPane);

        // Xử lý nút Thêm
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
                clearFields(); // Xóa thông tin trường
            }
        });

        // Xử lý nút Lưu
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentController.saveStudents(students);
                JOptionPane.showMessageDialog(null, "Dữ liệu sinh viên đã được lưu!");
            }
        });

        // Xử lý nút Xóa
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = studentList.getSelectedValue();
                if (selectedValue != null) {
                    String selectedId = selectedValue.split(" - ")[0]; // Lấy Mã SV
                    students.removeIf(student -> student.getId().equals(selectedId)); // Xóa sinh viên
                    updateStudentList(students); // Cập nhật lại danh sách sinh viên
                    studentController.saveStudents(students); // Lưu lại vào file
                    clearFields(); // Xóa thông tin các trường
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sinh viên để xóa.");
                }
            }
        });

        // Sửa sinh viên
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = studentList.getSelectedValue();
                if (selectedValue != null) {
                    String selectedId = selectedValue.split(" - ")[0]; // Lấy mã sinh viên
                    students.removeIf(student -> student.getId().equals(selectedId)); // Xóa sinh viên cũ
                    String id = idField.getText();
                    String name = nameField.getText();
                    String email = emailField.getText();
                    double gpa = Double.parseDouble(gpaField.getText());
                    String address = addressField.getText();

                    Student student = new Student(id, name, email, gpa, address);
                    students.add(student); // Thêm sinh viên mới
                    updateStudentList(students);
                    clearFields(); // Xóa thông tin trường
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sinh viên để sửa.");
                }
            }
        });

        // Sắp xếp theo tên (theo chữ cuối cùng)
        sortByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                students.sort(Comparator.comparing(s -> {
                    String[] nameParts = s.getName().split(" ");
                    return nameParts[nameParts.length - 1]; // Sắp xếp theo tên cuối cùng
                }));
                updateStudentList(students);
            }
        });

        // Sắp xếp theo GPA (tăng dần)
        sortByGpaAscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                students.sort(Comparator.comparingDouble(Student::getGpa)); // Sắp xếp theo GPA từ thấp đến cao
                updateStudentList(students);
            }
        });

        // Sắp xếp theo GPA (giảm dần)
        sortByGpaDescButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                students.sort(Comparator.comparingDouble(Student::getGpa).reversed()); // Sắp xếp theo GPA từ cao đến thấp
                updateStudentList(students);
            }
        });

        // Tìm kiếm sinh viên
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText().toLowerCase();
                List<Student> filteredStudents = new ArrayList<>();
                for (Student student : students) {
                    // Kiểm tra xem ID, tên hoặc địa chỉ có chứa chuỗi tìm kiếm hay không
                    if (student.getId().toLowerCase().contains(searchQuery) ||
                        student.getName().toLowerCase().contains(searchQuery) ||
                        student.getAddress().toLowerCase().contains(searchQuery)) {
                        filteredStudents.add(student);
                    }
                }
                updateStudentList(filteredStudents); // Cập nhật danh sách hiển thị
            }
        });

        // Sự kiện chọn sinh viên để sửa
        studentList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = studentList.getSelectedValue();
                if (selectedValue != null) {
                    String selectedId = selectedValue.split(" - ")[0];
                    Student student = students.stream()
                            .filter(s -> s.getId().equals(selectedId))
                            .findFirst()
                            .orElse(null);
                    if (student != null) {
                        idField.setText(student.getId());
                        nameField.setText(student.getName());
                        emailField.setText(student.getEmail());
                        gpaField.setText(String.valueOf(student.getGpa()));
                        addressField.setText(student.getAddress());
                    }
                }
            }
        });

        setVisible(true);
    }

    // Cập nhật danh sách sinh viên hiển thị
    private void updateStudentList(List<Student> students) {
        listModel.clear();
        for (Student student : students) {
            listModel.addElement(student.getId() + " - " + student.getName() + " - GPA: " + student.getGpa() + " - Địa chỉ: " + student.getAddress());
        }
    }

    // Xóa dữ liệu trong các trường
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        gpaField.setText("");
        addressField.setText("");
        searchField.setText(""); // Xóa trường tìm kiếm
    }
}
