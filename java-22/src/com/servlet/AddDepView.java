package com.servlet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDepView  extends JDialog {
    JLabel depidLabel = new JLabel("部门编号");
    JTextField depidField = new JTextField();

    JLabel depnameLabel = new JLabel("部门名称");
    JTextField depnameField = new JTextField();

    JButton addDepButton = new JButton("添加部门");



    public AddDepView(){
        setTitle("添加部门");
        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setModal(true);

        depidLabel.setBounds(50,50,80,50);
        depidField.setBounds(150,50,120,50);
        depnameLabel.setBounds(50,150,80,50);
        depnameField.setBounds(150,150,120,50);
        addDepButton.setBounds(200,350,150,50);

        this.add(depidField);
        this.add(depidLabel);
        this.add(depnameLabel);
        this.add(depnameField);
        this.add(addDepButton);

        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\background.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));


        addDepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String depid = depidField.getText();
                String depname = depnameField.getText();



                Connection connection = null;


                try {
                    //2.加载驱动
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    //3.得到链接对象                jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC","root","root");
                    //4.写sql语句
                    String sql ="insert into dep(depid,depname) Values(?,?)  ";
                    //5.编译并且发送sql
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1,depid);
                    ps.setObject(2,depname);
                    //6.执行操作
                    //执行操作
                    int n = ps.executeUpdate();
                    // n标是添加的条数    n=0  表示添加了0条
                    if(n==0){
                        JOptionPane.showMessageDialog(null , "添加失败" ,"提示" ,JOptionPane.YES_NO_OPTION);
                    }else{
                        JOptionPane.showMessageDialog(null , "添加成功" ,"提示" ,JOptionPane.YES_NO_OPTION);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        });



        setVisible(true);
    }

    public static void main(String[] args) {
        new  AddDepView();
    }

}
