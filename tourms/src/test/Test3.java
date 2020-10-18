package test;

import javax.swing.*;

public class Test3 extends JFrame {
    private   JButton userButton = new JButton("用户管理");
    private   JButton visitorButton = new JButton("游客管理");
    private   JButton lineButton = new JButton("线路管理");
    private   JButton groupButton = new JButton("组团管理");
    public  Test3(){
        this.setTitle("欢迎使用旅游管理系统！");
        this.setSize(800,400);
        this.setLocationRelativeTo(null);
        this.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(userButton);
        this.add(visitorButton);
        this.add(lineButton);
        this.add(groupButton);
        userButton.setBounds(100,150,100,50);
        visitorButton.setBounds(250,150,100,50);
        lineButton.setBounds(400,150,100,50);
        groupButton.setBounds(550,150,100,50);
        JPanel jPanel = new JPanel(null);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Test3();
    }
}
