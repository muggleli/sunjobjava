package com.servlet;

import util.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class LoginVeiw extends JFrame {
    public  LoginVeiw(){
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
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //1.获取用户名和密码
                String username = usernameFeild.getText();
                String password = passwordField.getText();
                //2.拷贝驱动
                // 加载驱动
                Connection connection = DB.getConnection();
                try {
                    /*Class.forName("com.mysql.cj.jdbc.Driver");
                    //3. 得到链接对象
                    connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC","root","root");*/
                    // 4. 写sql语句

                    String sql = "Select username , password from userinfo where username = ? and password = ? ";
                    // 5. 编译并且发送sql
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1,username);
                    ps.setObject(2,password);
                    // 6. 执行操作
                    ResultSet rs = ps.executeQuery();
                    // 根据数据库查询结果做出不同的响应
                    if (rs.next()){
                        System.out.println("1121");
                        //1.关闭登录界面
                        LoginVeiw.this.dispose();
                        // 2. 回到主界面
                         new MainVeiw();
                    }else
                        {
                            JOptionPane.showMessageDialog(null,"用户名或者密码错误","用户名或者密码提示错误",JOptionPane.YES_NO_OPTION);
                            passwordField.setText("");
                            usernameFeild.setText("");
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    DB.releaseConnection(connection);
                }
                // 7.关闭连接
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginVeiw();
    }
}
