package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class LeasingMainVeiw extends JFrame {
    static
    {
        ImageIcon smallImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        smallImg.setImage(smallImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
    }
    JMenuBar menuBar = new JMenuBar() ;
    JMenu  houseInfoMenu = new JMenu("房源信息管理");
    JMenuItem houseInfoItem =new JMenuItem("房源信息");
    JMenu  customerMenu = new JMenu("客户服务");
    JMenuItem customerItem =new JMenuItem("看房安排");
    JMenu userMenu = new JMenu("退出");
    JMenuItem exitItem =new JMenuItem("退出系统");
    JMenuItem changeItem =new JMenuItem("切换用户");
    //图片信息
    private ImageIcon backgroundImg;
    private JLabel showBackgroundImg;
    private ImageIcon smallImg;

    public LeasingMainVeiw(){
        this.setSize(900,650);
        this.setTitle("租赁专员系统界面");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 给menuBar添加图片
        ImageIcon smallImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\smalluser2.jpg");
        smallImg.setImage(smallImg.getImage().getScaledInstance(18,20, Image.SCALE_DEFAULT));
        ImageIcon lineImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\line.jpg");
        lineImg.setImage(lineImg.getImage().getScaledInstance(18,18, Image.SCALE_DEFAULT));
        ImageIcon visitorImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\visitor.jpg");
        visitorImg.setImage(visitorImg.getImage().getScaledInstance(18,18, Image.SCALE_DEFAULT));
        ImageIcon groupImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\team.jpg");
        groupImg.setImage(groupImg.getImage().getScaledInstance(18,18, Image.SCALE_DEFAULT));
        ImageIcon qureyImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\sousuo.jpg");
        qureyImg.setImage(qureyImg.getImage().getScaledInstance(18,18, Image.SCALE_DEFAULT));

        houseInfoMenu.setIcon(smallImg);
        customerMenu.setIcon(visitorImg);



        menuBar.setBounds(0,0,800,30);
        menuBar.add(houseInfoMenu);
        houseInfoMenu.add(houseInfoItem);


//        lineMenu.add(hostelItem);
        menuBar.add(customerMenu);
        customerMenu.add(customerItem);

        menuBar.add(userMenu);
        userMenu.add(exitItem);
        userMenu.add(changeItem);

        //添加小图标
        smallImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        smallImg.setImage(smallImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));


        this.add(menuBar);



        //添加图片
        backgroundImg=new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\background.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(900,620, Image.SCALE_DEFAULT));

        showBackgroundImg=new JLabel();
        showBackgroundImg.setIcon(backgroundImg);
        showBackgroundImg.setBounds(0,30,900,620);

        this.add(showBackgroundImg);

        //添加监听器
        houseInfoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new HouseInfoView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });



        customerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new WorkArrangeView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        changeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                try {
                    new LoginView();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        this.setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        new LeasingMainVeiw();
    }
}
