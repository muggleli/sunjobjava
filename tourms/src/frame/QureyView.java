package frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class QureyView extends JDialog {
    private  JButton checkVisitorButton = new JButton("游客信息查询");
    private  JButton checkLineButton = new JButton("旅游线路查询");
    private  JButton checkGroupButton = new JButton("旅行团信息查询");

    public  QureyView(){
        this.setTitle("旅游信息查询");
        this.setSize(550, 250);//设置窗体的大小
        this.setLocationRelativeTo(null);//设置界面居中
        this.setResizable(false);//设置大小是否可以改变
        this.setLayout(null);
        this.setModal(true);//设置为模式对话框
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        checkLineButton.setBounds(50,80,120,50);
        checkVisitorButton.setBounds(210,80,120,50);
        checkGroupButton.setBounds(370,80,120,50);

        //为按钮添加事件
        checkLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new CheckLineView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        checkGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CheckGroupView();
            }
        });
        checkVisitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CheckVisitorView();
            }
        });
        this.add(checkLineButton);
        this.add(checkGroupButton);
        this.add(checkVisitorButton);


        this.setVisible(true);

    }

    public static void main(String[] args) {
        new QureyView();
    }
}
