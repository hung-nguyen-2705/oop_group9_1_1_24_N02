package gr9.qlsv.view;

import gr9.qlsv.controller.AccountController;
import gr9.qlsv.controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window.Type;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class LoginView extends JFrame {
    private LoginController loginController = new LoginController();
    private AccountController accountcontroller = new AccountController();

    public LoginView() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("Icon-CTy-Tap-doan-Phuong-Hoan.png"));
        setTitle("Đăng nhập");
        setSize(387, 318);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.inactiveCaptionBorder);
        JLabel userLabel = new JLabel("Tài khoản:");
        userLabel.setBounds(10, 54, 75, 20);
        JTextField userText = new JTextField(20);
        userText.setBounds(77, 54, 275, 20);
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setBounds(10, 114, 55, 17);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(77, 112, 275, 20);
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(125, 158, 119, 23);
        panel.setLayout(null);

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(loginButton);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        JLabel lblQunLSinh = new JLabel("QUẢN LÝ SINH VIÊN");
        lblQunLSinh.setHorizontalAlignment(SwingConstants.CENTER);
        lblQunLSinh.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblQunLSinh.setBounds(112, 23, 132, 20);
        panel.add(lblQunLSinh);
        
        JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("");
        lblNewJgoodiesLabel.setIcon(new ImageIcon("download (2).jpg"));
        lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewJgoodiesLabel.setBounds(0, 179, 371, 125);
        panel.add(lblNewJgoodiesLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                if (loginController.authenticate(username, password)) {
                    new StudentView();
                    dispose(); // Đóng cửa sổ đăng nhập
                } else {
                	if (accountcontroller.authenticate(username, password))
                	{
                		new Dangkymonhoc(username);
                		dispose();
                	}
                	else {
                	JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu");
                	}
                }
            }
        });

        setVisible(true);
    }
}
