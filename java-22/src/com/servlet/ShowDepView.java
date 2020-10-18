package com.servlet;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class ShowDepView extends JDialog {
    private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JButton delButton = new JButton("删除");

    public ShowDepView() throws SQLException {
        setTitle("查找部门");
        setSize(600, 500);//设置窗体的大小
        setLocationRelativeTo(null);//设置界面居中
        setResizable(false);//设置大小是否可以改变
        //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
        setLayout(null);
        setModal(true);//设置为模式对话框

        Vector<String> thVector = new Vector<String>();
        thVector.add("部门编号");
        thVector.add("部门名称");

        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        // JDBC 从数据库取信息
        // 2.连接驱动
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC", "root", "root");
            // 写sql语句
            String sql = "select depid , depname from dep";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                dataVector.add(vector);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(dataVector, thVector);
        table.setModel(defaultTableModel);

        scrollPane.getViewport().add(table);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, defaultTableCellRenderer);

        scrollPane.setBounds(20, 20, 540, 350);
        delButton.setBounds(500, 400, 60, 20);
        add(delButton);
        add(scrollPane);

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "请先选中");
                    return;
                }

                String depid = (String) table.getValueAt(row, 0);
                Connection connection1 = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection1 = DriverManager.getConnection(" jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC", "root", "root");
                    String sql = "delect  from dep where depid = ? ";
                    PreparedStatement pss = connection1.prepareStatement(sql);
                    pss.setObject(1, depid);
                    int n = pss.executeUpdate();
                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                        //数据删了    才删除界面
                        ((DefaultTableModel) table.getModel()).removeRow(row);
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        connection1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

    }
}
