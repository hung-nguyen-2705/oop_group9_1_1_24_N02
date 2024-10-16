package gr9.qlsv.view;

import gr9.qlsv.controller.StudentController;
import gr9.qlsv.entity.Monhoc;
import gr9.qlsv.entity.Student;
import gr9.qlsv.controller.MonhocController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dangkymonhoc extends JFrame {
    private MonhocController monhocController = new MonhocController();
    private List<Monhoc> monhoc = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    // Các trường nhập
    private JLabel IDLabel = new JLabel();
    private JLabel NameLabel = new JLabel();
    private JLabel EmailLabel = new JLabel();
    private JLabel GPALabel = new JLabel();
    private JLabel AddLabel = new JLabel();


    // Danh sách sinh viên hiển thị
    private JList<String> monhocList = new JList<>(listModel);

    public Dangkymonhoc(String user) {
        setTitle("Phenikaa Managment");
        setSize(598, 501);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       monhoc = monhocController.getAllMonhoc(user);
       updateMonhocList(monhoc);

        // Tạo các nút

        // Tạo layout
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 584, 230);
        panel.setLayout(null);
        JLabel label = new JLabel("Mã SV:");
        label.setBounds(0, 24, 292, 23);
        panel.add(label);
        IDLabel.setBounds(292, 24, 292, 23);
        panel.add(IDLabel);
        JLabel label_1 = new JLabel("Tên SV:");
        label_1.setBounds(0, 47, 292, 23);
        panel.add(label_1);
        NameLabel.setBounds(292, 47, 292, 23);
        panel.add(NameLabel);
        JLabel label_2 = new JLabel("Email:");
        label_2.setBounds(0, 70, 292, 23);
        panel.add(label_2);
        EmailLabel.setBounds(292, 70, 292, 23);
        panel.add(EmailLabel);
        JLabel label_3 = new JLabel("GPA:");
        label_3.setBounds(0, 93, 292, 23);
        panel.add(label_3);
        GPALabel.setBounds(292, 93, 292, 23);
        panel.add(GPALabel);
        JLabel label_4 = new JLabel("Địa chỉ:");
        label_4.setBounds(0, 116, 292, 23);
        panel.add(label_4);
        AddLabel.setBounds(292, 116, 292, 23);
        panel.add(AddLabel);
      
        JButton addButton = new JButton("Xac nhan");
        addButton.setBounds(292, 184, 292, 23);
        panel.add(addButton);
        addButton.addActionListener(new ActionListener()
        {
        	  @Override
              public void actionPerformed(ActionEvent e) {
        		  String fileName = user + ".txt"; // Tên file
        		    String folderPath = "L\\"+ fileName ;
        		    {
        		    

        		try {
        		    // Tạo đối tượng File
        		    File newfile = new File(folderPath);

        		    // Tạo file nếu nó chưa tồn tại
        		    if (newfile.createNewFile()) {
        		        System.out.println("File đã được tạo: " + newfile.getName());
        		    } else {
        		        System.out.println("File đã tồn tại.");
        		    }
        		    
        		    // Ghi nội dung từ danh sách monhoc vào file
        		    try (FileWriter writer = new FileWriter(newfile)) {
        		        for (Monhoc subject : monhoc) {
        		            String line = subject.getId() + " , " + subject.getName() + " , Teacher: " + subject.getTeacher() + " , Địa chỉ: " + subject.getAddress() + "\n";
        		            writer.write(line);
        		        }
        		        System.out.println("Đã ghi nội dung vào file.");
        		    }
        		} catch (IOException e1) {
        		    System.out.println("Đã xảy ra lỗi.");
        		    e1.printStackTrace();
        		}
        		    }
        	    }
        });
        
        JButton dangkyButton = new JButton("Đăng Ký");
        dangkyButton.setBounds(0, 184, 292, 23);
        panel.add(dangkyButton);
        
        dangkyButton.addActionListener(new ActionListener()
        {
        	  @Override
              public void actionPerformed(ActionEvent e) {
        		  String selectedValue = monhocList.getSelectedValue();
        	        if (selectedValue != null) {
        	            String selectedId = selectedValue.split(" - ")[0]; // Lấy ID môn học
        	            Monhoc subjectselected = monhoc.stream()
        	                    .filter(s -> s.getId().equals(selectedId))
        	                    .findFirst()
        	                    .orElse(null);

        	            if (subjectselected != null) {
        	                // Cập nhật tên môn học với "(đã đăng ký)"
        	                String updatedName = subjectselected.getAddress() + " (Đã đăng ký)";
        	                subjectselected.setAddress(updatedName); // Giả sử bạn có phương thức setName trong Monhoc

        	                // Cập nhật danh sách hiển thị
        	                updateMonhocList(monhoc);
        	            }
        	        }
        	    }
        });
        
        
        
        JButton huyButton = new JButton("Hủy");
        huyButton.setBounds(0, 160, 292, 23);
        panel.add(huyButton);
        
        huyButton.addActionListener(new ActionListener()
        {
        	  @Override
              public void actionPerformed(ActionEvent e) {
        		  String selectedValue = monhocList.getSelectedValue();
        	        if (selectedValue != null) {
        	            String selectedId = selectedValue.split(" - ")[0]; // Lấy ID môn học
        	            Monhoc subjectselected = monhoc.stream()
        	                    .filter(s -> s.getId().equals(selectedId))
        	                    .findFirst()
        	                    .orElse(null);

        	            if (subjectselected != null) {
        	                // Xóa "(Đã đăng ký)" nếu nó tồn tại
        	                String address = subjectselected.getAddress();
        	                if (address.endsWith(" (Đã đăng ký)")) {
        	                    String updatedAddress = address.substring(0, address.length() - 15); // Xóa phần "(Đã đăng ký)"
        	                    subjectselected.setAddress(updatedAddress); // Cập nhật lại địa chỉ

        	                    // Cập nhật danh sách hiển thị
        	                    updateMonhocList(monhoc);
        	                }
        	        }
        	    }
        }
        });
        
        
        
        
        // Tạo danh sách sinh viên
        JScrollPane scrollPane = new JScrollPane(monhocList);
        scrollPane.setBounds(0, 230, 584, 231);
        monhocList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getContentPane().setLayout(null);

        // Thêm panel và danh sách vào frame
        getContentPane().add(panel);
        getContentPane().add(scrollPane);

        displayStudent(user);
		 
        setVisible(true);
    } 
    
    
    public void displayStudent(String user)
    {
    	 String filepath = "students.txt";
 		File file = new File(filepath);	
 		 try {
 	            BufferedReader br = new BufferedReader(new FileReader(file));
 	            String firstLine = br.readLine().trim();
 	            String[] columnsName = firstLine.split(",");
 	            
 	            Object[] tableLines = br.lines().toArray();
 	            
 	        
 	            for(int i = 0; i < tableLines.length; i++)
 	            {
 	                String line = tableLines[i].toString().trim();
 	                String[] dataRow = line.split(",");
 	                if (dataRow[0].equals(user))
 	                {
 	                	IDLabel.setText(dataRow[0]);
 	                	NameLabel.setText(dataRow[1]);
 	                	EmailLabel.setText(dataRow[2]);
 	                	GPALabel.setText(dataRow[3]);
 	                	AddLabel.setText(dataRow[4]);
 	                	
 	                }
 	            }
 	        } catch (Exception ex) {
 	 
 	        }
    
    

 		monhocList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = monhocList.getSelectedValue();
                if (selectedValue != null) {
                    String selectedId = selectedValue.split(" - ")[0];
                    Monhoc subjectselected = monhoc.stream()
                            .filter(s -> s.getId().equals(selectedId))
                            .findFirst()
                            .orElse(null);
                    if (subjectselected != null) {
                        
                    }
                }
            }
        });
}
    
    
    
    private void updateMonhocList(List<Monhoc> monhoc) {
        listModel.clear();
        for (Monhoc subject : monhoc ) {
            listModel.addElement(subject.getId() + " - " + subject.getName() + " - Teacher: " + subject.getTeacher() + " - Địa chỉ: " + subject.getAddress());
        }
    }
    
    
}
