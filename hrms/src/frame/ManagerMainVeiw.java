package frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
public class ManagerMainVeiw extends JFrame {
    static
    {
        ImageIcon smallImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        smallImg.setImage(smallImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
    }
    JMenuBar menuBar = new JMenuBar() ;
    JMenu  adminMenu = new JMenu("人事管理");
    JMenuItem empInfoItem =new JMenuItem("员工信息管理");
    JMenuItem empPerforItem =new JMenuItem("员工绩效管理");
    JMenu  financeMenu = new JMenu("财务管理");
    JMenuItem financialReportsItem =new JMenuItem("财务报告审核");
    JMenu workMenu = new JMenu("办公管理");
    JMenuItem workItem =new JMenuItem("工作安排");
    JMenu userMenu = new JMenu("退出");
    JMenuItem exitItem =new JMenuItem("退出系统");
    JMenuItem changeItem =new JMenuItem("切换用户");

    public ManagerMainVeiw(){
        this.setSize(900,650);
        this.setTitle("管理人员系统界面");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuBar.setBounds(0,0,800,30);
        menuBar.add(adminMenu);
        adminMenu.add(empInfoItem);
        adminMenu.add(empPerforItem);
        menuBar.add(workMenu);
        workMenu.add(workItem);
        menuBar.add(financeMenu);
        financeMenu.add(financialReportsItem);
        menuBar.add(userMenu);
        userMenu.add(exitItem);
        userMenu.add(changeItem);
        this.add(menuBar);

        empInfoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new EmpInfoView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        empPerforItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new EmpAchView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        financialReportsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new FinView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        workItem.addActionListener(new ActionListener() {
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
        new ManagerMainVeiw();
    }
}
