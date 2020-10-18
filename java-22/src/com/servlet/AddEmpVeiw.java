package com.servlet;

import javax.swing.*;

public class AddEmpVeiw extends JDialog {
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


    public  AddEmpVeiw () {
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

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddEmpVeiw();
    }}

