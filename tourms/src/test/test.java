package test;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class test {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(300, 300);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        // 创建选项卡面板
        final JTabbedPane tabbedPane = new JTabbedPane();


        // 创建第 1 个选项卡（选项卡只包含 标题）
        tabbedPane.addTab("Tab01", createAPanel());

        // 创建第 2 个选项卡（选项卡包含 标题 和 图标）
        tabbedPane.addTab("Tab02", new ImageIcon("bb.jpg"), createBPanel());

        // 创建第 3 个选项卡（选项卡包含 标题、图标 和 tip提示）
        tabbedPane.addTab("Tab03", new ImageIcon("bb.jpg"), createCPanel(), "This is a tab.");


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
    private static JComponent createAPanel() {
        // 创建面板, 使用一个 1 行 1 列的网格布局（为了让标签的宽高自动撑满面板）
        JPanel panel = new JPanel();

        // 创建标签
        JLabel label = new JLabel();
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // 添加标签到面板
        panel.add(label);

        return panel;
    }
    private static JComponent createBPanel() {
        // 创建面板, 使用一个 1 行 1 列的网格布局（为了让标签的宽高自动撑满面板）
        JPanel panel = new JPanel();

        // 创建标签
        JLabel label = new JLabel();
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // 添加标签到面板
        panel.add(label);

        return panel;
    }
    private static JComponent createCPanel() {
        // 创建面板, 使用一个 1 行 1 列的网格布局（为了让标签的宽高自动撑满面板）
        JPanel panel = new JPanel();

        // 创建标签
        JLabel label = new JLabel();
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // 添加标签到面板
        panel.add(label);

        return panel;
    }
}
