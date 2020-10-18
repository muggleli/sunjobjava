package com.servlet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Loginuser extends JFrame {

    public Loginuser() {
        setTitle("欢迎");
        JLabel usernameLabel = new JLabel();
        JTextField usernameFeild = new JTextField();

        usernameLabel.setText("登录");

        JLabel passwordLabel = new JLabel();
        JPasswordField passwordField = new JPasswordField();

        passwordLabel.setText("密码");

        JButton resertButton = new JButton();
        resertButton.setText("重置");
        JButton loginButton = new JButton();
        loginButton.setText("登录");

        usernameLabel.setBounds(40, 40, 40, 40);
        usernameFeild.setBounds(110, 40, 130, 40);
        passwordLabel.setBounds(40, 100, 40, 40);
        passwordField.setBounds(110, 100, 130, 40);
        resertButton.setBounds(40, 160, 60, 30);
        loginButton.setBounds(180, 160, 60, 30);


        this.setSize(300, 270);

        this.add(usernameLabel);
        this.add(usernameFeild);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(resertButton);
        this.add(loginButton);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        resertButton.addActionListener
                (new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent actionEvent) {
                         usernameFeild.setText("");
                         passwordField.setText("");
                     }
                 }
                );
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "真的要退出吗？","退出",JOptionPane.YES_NO_OPTION);
                if ( n==0){
                    System.exit(0);
                }
            }
        });

        // 给close添加窗口事件
       /* this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int n = JOptionPane.showConfirmDialog(null, "真的要退出吗？","退出",JOptionPane.YES_NO_OPTION);
                if ( n==0){
                    System.exit(0);
                }

            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });*/
    }

    public static void main(String[] args) {
        new Loginuser();
    }
}
