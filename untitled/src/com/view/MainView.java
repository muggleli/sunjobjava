package com.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    JMenuBar menuBar = new JMenuBar();

    JMenu depMenu = new JMenu("部门管理");
    JMenuItem addDepItem = new JMenuItem("新增部门");
    JMenuItem delDepItem = new JMenuItem("删除部门");
    JMenuItem updateDepItem = new JMenuItem("修改部门");
    JMenuItem queryDepItem = new JMenuItem("查询部门");


    JMenu empMenu = new JMenu("员工管理");
    JMenuItem addEmpItem = new JMenuItem("新增员工");
    JMenuItem delEmpItem = new JMenuItem("删除员工");
    JMenuItem updateEmpItem = new JMenuItem("修改员工");
    JMenuItem queryEmpItem = new JMenuItem("查询员工");
   public MainView(){
       setTitle("欢迎使用公司管理系统");
       setSize(600, 500);//设置窗体的大小
       setLocationRelativeTo(null);//设置界面居中
       setResizable(false);//设置大小是否可以改变
       //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
       setLayout(null);

       menuBar.setBounds(0, 0, 900, 20);
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

       //添加部门
       addDepItem.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new AddDepView();//点击了添加部门的菜单就弹出 添加部门的界面
           }
       });

       //添加员工
       addEmpItem.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new AddEmpView();
           }
       });

       // 查询部门
       queryDepItem.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent actionEvent) {
              new ShowDepView();
           }
       });

       add(menuBar);

       setVisible(true);
   }
    public static void main(String[] args) {
        new MainView();
    }
}
