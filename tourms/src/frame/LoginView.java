package frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class LoginView extends JFrame {

    private JLabel usernameLabel = new JLabel("用户名");
    private JTextField usernameField = new JTextField();

    private JLabel passwordLabel = new JLabel("密码");
    // 把密码框内容隐藏
    private JTextField passwordField = new JPasswordField();

    private JButton resetButton = new JButton("重置");
    private JButton loginButton = new JButton("登录");


    //图片信息
    private ImageIcon userImg;
    private JLabel showUserImg;





    public LoginView() throws IOException {
        setTitle("欢迎使用旅游管理系统");
        setSize(560, 320);//设置窗体的大小
        setLocationRelativeTo(null);//设置界面居中
        setResizable(false);//设置大小是否可以改变
        //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
        setLayout(null);


        usernameLabel.setBounds(280, 60, 60, 20);
        usernameField.setBounds(380, 60, 100, 20);

        passwordLabel.setBounds(280, 120, 60, 20);
        passwordField.setBounds(380, 120, 100, 20);

        resetButton.setBounds(280, 200, 60, 20);
        loginButton.setBounds(420, 200, 60, 20);


        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(resetButton);
        this.add(loginButton);


        //添加图片
        userImg=new ImageIcon("C:\\Users\\hutlhj\\Desktop\\sunjob\\javaswing项目\\img\\user.jpg");
        userImg.setImage(userImg.getImage().getScaledInstance(160,160,Image.SCALE_DEFAULT));

        showUserImg=new JLabel();
        showUserImg.setIcon(userImg);

        showUserImg.setBounds(60,60,160,160);
        this.add(showUserImg);
        //重置按/
        resetButton.addActionListener((e) -> {
            usernameField.setText("");
            passwordField.setText("");
        });

        //界面的监听事件  关闭
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //添加新的事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "真的要退出吗？", "退出", JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    System.exit(0);
                }
            }
        });

        //完成登录的功能
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //1.取出用户填写的用户名和密码
                String username = usernameField.getText();
                String password = passwordField.getText();


                //2.把用户填写的数据去数据库中查找  ？ 找到和没找到
                //(1) 拷贝驱动
                //(2)加载驱动
                Connection connection = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");

                    //(3) 得到链接对象
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC", "root", "root");
                    //(4) 写sql
                    String sql = "select User_name , User_pwd from tb_user where User_name = ? and User_pwd = ? ";

                    //(5)编译并且发送sql
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1, username);
                    ps.setObject(2, password);

                    //(6)执行操作
                    ResultSet rs = ps.executeQuery();

                    //3.根据数据库查询结果做出不同的响应
                    if (rs.next()) {
                        //1.关闭登录界面
                        LoginView.this.dispose();
                        //2.打开主界面
                        new MainVeiw();// new 产生对象

                    } else {
                        JOptionPane.showMessageDialog(null, "用户名或密码错误", "提示", JOptionPane.YES_NO_OPTION);
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    //(7) 关闭链接
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        setVisible(true);


    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        new LoginView();
    }

}
