package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainVeiw  extends JFrame {
    static
    {
        ImageIcon smallImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        smallImg.setImage(smallImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

    }
    JMenuBar menuBar = new JMenuBar() ;
    JMenu  userMenu = new JMenu("用户管理");
    JMenuItem userInfoItem =new JMenuItem("用户信息管理");
    JMenu  visitorMenu = new JMenu("游客管理");
    JMenuItem visitorInfoItem =new JMenuItem("游客信息管理");
    JMenu lineMenu = new JMenu("旅游线路管理");
    JMenuItem scheduleItem =new JMenuItem("班次管理");
    JMenuItem sightsItem =new JMenuItem("线路管理");
//    JMenuItem hostelItem =new JMenuItem("宾馆管理");
    JMenu groupMenu = new JMenu("旅行团组团管理");
    JMenuItem groupInfoItem =new JMenuItem("旅行团信息管理");
//    JMenuItem guideItem =new JMenuItem("导游信息管理");
//    JMenuItem insuranceItem =new JMenuItem("保险管理");
    JMenu queryMenu = new JMenu("旅行团信息查询");
    JMenuItem queryItem =new JMenuItem("信息查询");
    //图片信息
    private ImageIcon backgroundImg;
    private JLabel showBackgroundImg;
    private ImageIcon smallImg;

    public MainVeiw(){
        this.setSize(900,650);
        this.setTitle("旅游管理系统");
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

        userMenu.setIcon(smallImg);
        lineMenu.setIcon(lineImg);
        visitorMenu.setIcon(visitorImg);
        groupMenu.setIcon(groupImg);
        queryMenu.setIcon(qureyImg);

        menuBar.setBounds(0,0,800,30);
        menuBar.add(userMenu);
        userMenu.add(userInfoItem);
        menuBar.add(lineMenu);
        lineMenu.add(sightsItem);
        lineMenu.add(scheduleItem);
//        lineMenu.add(hostelItem);
        menuBar.add(visitorMenu);
        visitorMenu.add(visitorInfoItem);
        menuBar.add(groupMenu);
        groupMenu.add(groupInfoItem);
    /*    groupMenu.add(guideItem);
        groupMenu.add(insuranceItem);
    */    menuBar.add(queryMenu);
        queryMenu.add(queryItem);

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
        userInfoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new UserInfoView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        sightsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new TourLineView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        scheduleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new TourOderView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        visitorInfoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new VisitorInfoView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        groupInfoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new GroupInfoView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        queryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new QureyView();
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        new MainVeiw();
    }
}
