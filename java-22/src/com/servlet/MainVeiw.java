package com.servlet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainVeiw  extends JFrame {
    JMenuBar menuBar = new JMenuBar() ;
    JMenu depMenu = new JMenu("部门管理");
    JMenuItem addDepItem =new JMenuItem("添加部门");
    JMenuItem delDepItem =new JMenuItem("删除部门");
    JMenuItem updateDepItem =new JMenuItem("修改部门");
    JMenuItem queryDepItem =new JMenuItem("查询部门");
    JMenu empMenu = new JMenu("员工管理");
    JMenuItem addEmpItem =new JMenuItem("添加员工");
    JMenuItem delEmpItem =new JMenuItem("删除员工");
    JMenuItem updateEmpItem =new JMenuItem("修改员工");
    JMenuItem queryEmpItem =new JMenuItem("查询员工");
    public MainVeiw(){
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        menuBar.setBounds(0,0,400,30);
        menuBar.add(depMenu);
        depMenu.add(addDepItem);
        depMenu.add(delDepItem);
        depMenu.add(updateDepItem);
        depMenu.add(queryDepItem);
        menuBar.add(empMenu);
        empMenu.add(addEmpItem);
        empMenu.add(delEmpItem);
        empMenu.add(updateEmpItem);
        empMenu.add(queryEmpItem);
        this.add(menuBar);
        addDepItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("0.0");
            }
        });
         queryDepItem.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent actionEvent) {
                 try {
                     new ShowDepView();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
         });
         queryEmpItem.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent actionEvent) {
                 try {
                     new ShowEmpView();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
         });
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainVeiw();
    }
}
