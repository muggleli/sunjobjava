package com.view;

import com.sun.jdi.connect.spi.Connection;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEmpView extends JDialog {
    private JLabel empnameLabel = new JLabel("姓名");
    private JTextField empnameField = new JTextField();

    private JLabel empgenderLabel = new JLabel("性别");
    private JRadioButton maleButton = new JRadioButton("男");
    private JRadioButton femaleButton = new JRadioButton("女");

    private JLabel emptelLabel = new JLabel("联系电话");
    private JTextField emptelField = new JTextField();
    private JLabel checktelLabel = new JLabel("");

    private JLabel empidendityLabel = new JLabel("身份证号码");
    private JTextField empidendityField = new JTextField();
    private JLabel getChecktelLabel = new JLabel("");

    private JLabel empdepLabel = new JLabel("所在部门");
    private JTextField empdepField = new JTextField();

    private JButton addButton = new JButton("添加");

    public  AddEmpView (){
        setTitle("添加员工");
        setSize(400, 300);//设置窗体的大小
        setLocationRelativeTo(null);//设置界面居中
        setResizable(false);//设置大小是否可以改变
        //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
        setLayout(null);
        setModal(true);//设置为模式对话框

        empnameLabel.setBounds(60, 20, 80, 20);
        empnameField.setBounds(160, 20, 100, 20);

        empgenderLabel.setBounds(60, 60, 80, 20);
        maleButton.setBounds(160, 60, 60, 20);
        femaleButton.setBounds(220, 60, 60, 20);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);

        maleButton.setSelected(true);


        emptelLabel.setBounds(60, 100, 80, 20);
        emptelField.setBounds(160, 100, 100, 20);
        checktelLabel.setBounds(270, 100, 60, 20);

        empidendityLabel.setBounds(60, 140, 80, 20);
        empidendityField.setBounds(160, 140, 100, 20);
        getChecktelLabel.setBounds(270,140,60,20);

        empdepLabel.setBounds(60, 180, 80, 20);
        empdepField.setBounds(160, 180, 100, 20);

        addButton.setBounds(200, 220, 60, 20);


        add(empnameLabel);
        add(empnameField);

        add(empgenderLabel);
        add(maleButton);
        add(femaleButton);

        add(emptelLabel);
        add(emptelField);
        add(checktelLabel);
        add(getChecktelLabel);
        emptelField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String regex = "^1[3456789]\\d{9}$";
                Pattern pattern = Pattern.compile(regex);
                String target = emptelField.getText();
                //用户填写的电话
                Matcher matcher = pattern.matcher(target);
                if (matcher.find()) {
                    checktelLabel.setText("√");
                } else {
                    checktelLabel.setText("×");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String regex = "^1[3456789]\\d{9}$";
                Pattern pattern = Pattern.compile(regex);
                String target = emptelField.getText();
                //用户填写的电话
                Matcher matcher = pattern.matcher(target);
                if (matcher.find()) {
                    checktelLabel.setText("√");
                } else {
                    checktelLabel.setText("×");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("3");
            }
        });

        empidendityField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                String regex = "^1[3456789]\\d{9}$";
                Pattern pattern = Pattern.compile(regex);
                String target = empidendityField.getText();
                //用户填写的电话
                Matcher matcher = pattern.matcher(target);
                if (matcher.find()) {
                    getChecktelLabel.setText("√");
                } else {
                    getChecktelLabel.setText("×");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                String regex = "^1[3456789]\\d{9}$";
                Pattern pattern = Pattern.compile(regex);
                String target = empidendityField.getText();
                //用户填写的电话
                Matcher matcher = pattern.matcher(target);
                if (matcher.find()) {
                    getChecktelLabel.setText("√");
                } else {
                    getChecktelLabel.setText("×");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                String regex = "^1[3456789]\\d{9}$";
                Pattern pattern = Pattern.compile(regex);
                String target = empidendityField.getText();
                //用户填写的电话
                Matcher matcher = pattern.matcher(target);
                if (matcher.find()) {
                    getChecktelLabel.setText("√");
                } else {
                    getChecktelLabel.setText("×");
                }
            }



        });

        add(empidendityLabel);
        add(empidendityField);

        add(empdepLabel);
        add(empdepField);

        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String empname = empnameField.getText();
                //取性别?
                int empgender = 1;

                if (femaleButton.isSelected()) {
                    empgender = 2;
                }

                String empidendity = empidendityField.getText();
                String emptel = emptelField.getText();
                String did = empdepField.getText();

                Connection connection = null;

                try {
                    //写JDBC
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC", "root", "root");
                    String sql = "insert into emp(empname , empidendity , empgender , emptel , did ) values(?,?,?,?,?)";
                    PreparedStatement ps = ((java.sql.Connection) connection).prepareStatement(sql);
                    ps.setObject(1, empname);
                    ps.setObject(2, empidendity);
                    ps.setObject(3, empgender);
                    ps.setObject(4, emptel);
                    ps.setObject(5, did);

                    //执行操作
                    int n = ps.executeUpdate();
                    // n标是添加的条数    n=0  表示添加了0条
                    if (n == 0) {
                        JOptionPane.showMessageDialog(null, "添加失败", "提示", JOptionPane.YES_NO_OPTION);
                    } else {
                        JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.YES_NO_OPTION);
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    //关闭连接
                    try {
                        connection.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }


            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
         new AddEmpView();
    }
}
