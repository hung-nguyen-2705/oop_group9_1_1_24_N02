package gr9.qlsv.view;

import gr9.qlsv.controller.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.Color;

public class LoginView extends JFrame {
    private LoginController loginController = new LoginController();

    public LoginView() {
        setTitle("Đăng nhập");
        setSize(444, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane panel = new JLayeredPane();
        JLabel userLabel = new JLabel("Tài khoản:");
        userLabel.setBounds(37, 76, 82, 20);
        JTextField userText = new JTextField(20);
        userText.setBounds(120, 76, 275, 20);
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setBounds(37, 136, 79, 17);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(120, 134, 275, 20);
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(163, 183, 119, 23);
        panel.setLayout(null);

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(loginButton);
        getContentPane().add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                if (loginController.authenticate(username, password)) {
                    new StudentView();
                    dispose(); // đóng cửa sổ đăng nhập
                } else {
                    JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu");
                }
            }
        });

        setVisible(true);
    }
}
