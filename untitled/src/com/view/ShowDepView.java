package com.view;

import com.sun.jdi.connect.spi.Connection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

public class ShowDepView extends JDialog {
    //滚动面板
    private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JTable table = new JTable() {
        @Override//设置表格是否可以编辑
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };//表格中显示什么数据？

    private JButton delButton = new JButton("删除");


    public ShowDepView() {
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

        //表数据的集合
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        //表数据  从数据库中查出来
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC","root","root");
            String sql = "select depid , depname from dep";
            PreparedStatement ps = ((java.sql.Connection) connection).prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {//循环一次  就是有一条记录   一条记录就对应一个vector
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                dataVector.add(vector);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //表模型
        DefaultTableModel defaultTableModel = new DefaultTableModel(dataVector, thVector);
        table.setModel(defaultTableModel);//把表模型赋值给表格

        scrollPane.getViewport().add(table);

        //表头不能重新排序
        table.getTableHeader().setReorderingAllowed(false);
        //表头不能改变宽度
        table.getTableHeader().setResizingAllowed(false);

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);//设置居中
        table.setDefaultRenderer(Object.class, defaultTableCellRenderer);

        scrollPane.setBounds(20, 20, 540, 350);
        delButton.setBounds(500 , 400 , 60 , 20);
        add(delButton);
        add(scrollPane);

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();//删除选中行
                if(row==-1){
                    JOptionPane.showMessageDialog(null , "请选中先");
                    return ;//后面删除的代码不要执行了
                }

                //删除只能根据部门编号删除
                String depid = (String) table.getValueAt(row , 0);//得到部门编号

                Connection connection = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "delete from dep where depid = ? ";
                    PreparedStatement ps = ((java.sql.Connection) connection).prepareStatement(sql);
                    ps.setObject(1 , depid);
                    int n = ps.executeUpdate();

                    if(n>0){
                        JOptionPane.showMessageDialog(null , "删除成功");
                        //数据删了    才删除界面
                        ((DefaultTableModel)table.getModel()).removeRow(row);

                    }else{
                        JOptionPane.showMessageDialog(null , "删除失败");
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        connection.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });


        setVisible(true);

    }
}
