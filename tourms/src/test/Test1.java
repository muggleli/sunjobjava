package test;

import frame.MainVeiw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class Test1 {


    private   JButton userButton = new JButton("用户管理");
    private   JButton visitorButton = new JButton("游客管理");
    private   JButton lineButton = new JButton("线路管理");
    private   JButton groupButton = new JButton("组团管理");


    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        JButton userButton = new JButton("用户管理");
        JButton visitorButton = new JButton("游客管理");
        JButton lineButton = new JButton("线路管理");
        JButton groupButton = new JButton("组团管理");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        userButton.setBounds(100,150,100,50);
        visitorButton.setBounds(250,150,100,50);
        lineButton.setBounds(400,150,100,50);
        groupButton.setBounds(550,150,100,50);

        // 创建选项卡面板
        final JTabbedPane tabbedPane = new JTabbedPane();
        new MainVeiw();
        JPanel MainPanel = new JPanel(null);
        MainPanel.add(userButton);
        MainPanel.add(lineButton);
        MainPanel.add(visitorButton);
        MainPanel.add(groupButton);
        tabbedPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected int calculateTabWidth(
                    int tabPlacement, int tabIndex, FontMetrics metrics) {
                return 200; // the width of the tab
            }
        });

        // 创建第 1 个选项卡（选项卡只包含 标题）
        tabbedPane.addTab("Tab01", createTextPanel("TAB 01"));

        // 创建第 2 个选项卡（选项卡包含 标题 和 图标）
        tabbedPane.addTab("Tab02", new ImageIcon("bb.jpg"),MainPanel);

        // 创建第 3 个选项卡（选项卡包含 标题、图标 和 tip提示）
        tabbedPane.addTab("Tab03", new ImageIcon("bb.jpg"), createTextPanel("TAB 03"), "This is a tab.");


        // 添加选项卡选中状态改变的监听器
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("当前选中的选项卡: " + tabbedPane.getSelectedIndex());
            }
        });

        // 设置默认选中的选项卡
        tabbedPane.setSelectedIndex(1);

        jf.setContentPane(tabbedPane);
        jf.setVisible(true);
    }

    /**
     * 创建一个面板，面板中心显示一个标签，用于表示某个选项卡需要显示的内容
     */
    private static JComponent createTextPanel(String text) {
        // 创建面板, 使用一个 1 行 1 列的网格布局（为了让标签的宽高自动撑满面板）
        JPanel panel = new JPanel(new GridLayout(1, 1));

        // 创建标签
        JLabel label = new JLabel(text);
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // 添加标签到面板
        panel.add(label);

        return panel;
    }

}
