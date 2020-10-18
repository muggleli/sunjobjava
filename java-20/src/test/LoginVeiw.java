package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginVeiw extends JFrame {
    private  JLabel usernameLabel = new JLabel("登录用户");
    private  JTextField username = new JTextField();

    private  JLabel passwordLable = new JLabel("登录密码");
    private  JTextField passwordfield = new JPasswordField();

    private  JButton resertButton = new JButton("重置");
    private  JButton loginButtom = new JButton("登录");

    public void inter(){
        usernameLabel.setBounds(40 ,40 ,80 ,80 );
        username.setBounds(160 ,60 ,200 ,40);
        passwordLable.setBounds(40 ,160 ,80 ,80);
        passwordfield.setBounds(160 ,180 ,200 ,40) ;
        resertButton.setBounds(40 ,300 ,60 ,30);
        loginButtom.setBounds(280 ,300 ,60 ,30);

        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLable);
        this.add(passwordfield);
        this.add(resertButton);
        this.add(loginButtom);

        resertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    };


    public static void main(String[] args) {
        //创建一个界面类的对象
        LoginVeiw loginVeiw =new LoginVeiw();
        loginVeiw.setSize(400,400);
        loginVeiw.setLocationRelativeTo(null);
        loginVeiw.setResizable(false);
        loginVeiw.setVisible(true);
        loginVeiw.setLayout(null);
        loginVeiw.inter();
    }
}
