package com.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDepView extends JDialog {

    private JLabel depidLabel = new JLabel("部门编号");
    private JTextField depidField = new JTextField();

    private JLabel depnameLabel = new JLabel("部门名称");
    private JTextField depnameField = new JTextField();

    private JButton addButton = new JButton("添加");

    public AddDepView(){

        setTitle("添加部门");
        setSize(600, 500);//设置窗体的大小
        setLocationRelativeTo(null);//设置界面居中
        setResizable(false);//设置大小是否可以改变
        //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
        setLayout(null);
        setModal(true);//设置为模式对话框

        depidLabel.setBounds(40, 40, 60, 20);
        depidField.setBounds(160, 40, 100, 20);

        depnameLabel.setBounds(40, 100, 60, 20);
        depnameField.setBounds(160, 100, 100, 20);

        addButton.setBounds(200, 160, 60, 20);

        add(depidLabel);
        add(depidField);

        add(depnameLabel);
        add(depnameField);

        add(addButton);



        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(111);
                //得到用户填写的部门编号  和部门名称
                String depid = depidField.getText();
                String depname = depnameField.getText();
                Connection connection = null;

                try {
                    //写JDBC
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "insert into dep(depid , depname ) values(?,?)";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1 , depid);
                    ps.setObject(2 , depname);

                    //执行操作
                    int n = ps.executeUpdate();
                    // n标是添加的条数    n=0  表示添加了0条
                    if(n==0){
                        JOptionPane.showMessageDialog(null , "添加失败" ,"提示" ,JOptionPane.YES_NO_OPTION);
                    }else{
                        JOptionPane.showMessageDialog(null , "添加成功" ,"提示" ,JOptionPane.YES_NO_OPTION);
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    //关闭连接
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

    public static void main(String[] args) {
        new AddDepView();
    }

}
