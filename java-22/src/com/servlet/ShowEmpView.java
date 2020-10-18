package com.servlet;

import pojo.Dep;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;
public class ShowEmpView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    private  JLabel nameLabel = new JLabel("姓名");
    private  JTextField nameField = new JTextField();
    private  JLabel idendityLabel = new JLabel("身份证号码");
    private  JTextField  idendityField = new JTextField();
    private  JLabel genderLabel = new JLabel("性别");
    private JRadioButton maleButton = new JRadioButton("男");
    private JRadioButton femaleButton = new JRadioButton("女");
    private  JLabel telLabel = new JLabel("联系电话");
    private  JTextField telField = new JTextField();
    private  JLabel depLabel = new JLabel("所在部门");
    private  JComboBox<Dep> depJComboBox = new JComboBox<Dep>();
    private  JLabel empidLable = new JLabel("员工编号");
    private  JTextField empidField = new JTextField();
    private  JButton updateButton = new JButton("修改");

    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private  JButton  delButton = new JButton("删除");

    public  ShowEmpView() throws SQLException {
        setTitle("查找员工");
        setSize(800, 500);//设置窗体的大小
        setLocationRelativeTo(null);//设置界面居中
        setResizable(false);//设置大小是否可以改变
        //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
        setLayout(null);
        setModal(true);//设置为模式对话框

        Vector<String> thVector = new Vector<String>();
        thVector.add("姓名");
        thVector.add("身份证号码");
        thVector.add("性别");
        thVector.add("联系电话");
        thVector.add("所在部门");
        thVector.add("部门编号");

        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        nameLabel.setBounds(620,20,50,30);
        nameField.setBounds(680,20,80,30);
        idendityLabel.setBounds(600,80,80,30);
        idendityField.setBounds(680,80,80,30);
        idendityField.setEditable(false);
        genderLabel.setBounds(620,140,50,30);
        maleButton.setBounds(680, 140, 40, 30);
        femaleButton.setBounds(720, 140, 40, 30);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);

        maleButton.setSelected(true);
        telLabel.setBounds(610,200,50,30);
        telField.setBounds(680,200,80,30);
        depLabel.setBounds(620,260,50,30);
        depJComboBox.setBounds(680,260,80,30);
        empidLable.setBounds(620,320,50,30);
        empidField.setBounds(680,320,80,30);
        empidField.setEditable(false);
        updateButton.setBounds(660,400,60,20);

        add(nameLabel);
        add(nameField);
        add(idendityLabel);
        add(idendityField);
        add(genderLabel);
        add(idendityField);
        add(genderLabel);
        add(maleButton);
        add(femaleButton);
        add(telLabel);
        add(telField);
        add(depLabel);
        add(depJComboBox);

        add(updateButton);
        add(empidLable);
        add(empidField);
        // JDBC 从数据库取信息
        // 2.连接驱动
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC","root","root");
            // 写sql语句
            String sql = "select empname , empidentity,empgender,emptel, depname,empid from emp inner join dep on emp.did=dep.depid   ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                String a =rs.getString(3);
                if (a.equals("1"))
                    vector.add("男");
                else
                    vector.add("女");
                vector.add(rs.getString(4));
                vector.add(rs.getString(5));
                vector.add(rs.getString(6));
                dataVector.add(vector);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(dataVector,thVector);
        table.setModel(defaultTableModel);

        scrollPane.getViewport().add(table);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,defaultTableCellRenderer);

        scrollPane.setBounds(20, 20, 540, 350);
        delButton.setBounds(500 , 400 , 60 , 20);
        add(delButton);
        add(scrollPane);
        // 给下拉列表赋初值
        Connection connection3 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC","root","root");
            String sql3 = "select depid , depname from dep";
            PreparedStatement preparedStatement =connection3.prepareStatement(sql3);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Dep dep = new Dep();
                dep.setDepid(resultSet.getInt(1));
                dep.setDepname(resultSet.getString(2));
                depJComboBox.addItem(dep);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection3.close();
        }

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null,"请先选中");
                    return;
                }
                String depid = (String) table.getValueAt(row,5);

                //jdbc
                Connection connection1 = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "delete  from emp where  empid = ?;";
                    PreparedStatement ps = connection1.prepareStatement(sql);


                    ps.setObject(1,depid);
                    int n = ps.executeUpdate();
                    if(n>0){
                        JOptionPane.showMessageDialog(null , "删除成功");
                        //数据删了    才删除界面
                        ((DefaultTableModel)table.getModel()).removeRow(row);

                    }else{
                        JOptionPane.showMessageDialog(null , "删除失败");
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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                nameField.setText((String) table.getValueAt(row , 0));
                if (table.getValueAt(row,2).equals("女"))
                {femaleButton.setSelected(true); }
                else maleButton.setSelected(true);

                idendityField.setText((String) table.getValueAt(row , 1));
                telField.setText((String) table.getValueAt(row,3));
                Connection connection1 = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "select depid from dep where depname = ?";
                    PreparedStatement ps = connection1.prepareStatement(sql);
                    ps.setObject(1,table.getValueAt(row,4));
                    ResultSet n =ps.executeQuery();
                    while (n.next())
                    {
                       depJComboBox.setSelectedIndex(n.getInt(1)-1);
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        connection1.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }


                empidField.setText((String) table.getValueAt(row,5));
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String empdepid = empidField.getText();
                String empname = nameField.getText();
                int empgender = 0;
                int did = 0;
                  String empgendername = "";
                if (maleButton.isSelected())
                {
                     empgender = 1;
                     empgendername = "男";
                }
                else
                {
                    empgender = 2;
                    empgendername = "女";
                }
                String empdepname = "";
                String emptel = telField.getText();
                String empdep = (String) depJComboBox.getSelectedItem().toString();
                if(empdep.equals("国防部"))
                {did = 1 ; empdepname = "国防部" ; }
                else if (empdep.equals("小卖部"))
                { did = 2 ; empdepname = "小卖部" ; }
                else
                { did = 3 ;empdepname = "销售部" ;}
                Connection connection1 = null;
                try {
                    connection1 = null;
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "update emp set empname = ? , empgender = ? , emptel = ? ,did=? where empid = ? ";
                    PreparedStatement pss = connection1.prepareStatement(sql);
                    pss.setObject(1,empname);
                    pss.setObject(2,empgender);
                    pss.setObject(3,emptel);
                    pss.setObject(4,did);
                    pss.setObject(5,empdepid);
                    int a = pss.executeUpdate() ;
                    if(a==1){
                    table.setValueAt(empname , table.getSelectedRow(),0);
                    table.setValueAt(empgendername , table.getSelectedRow(),2);
                    table.setValueAt(emptel , table.getSelectedRow(),3);
                    table.setValueAt(empdepname , table.getSelectedRow(),4);

                    JOptionPane.showMessageDialog(null , "修改成功");
                }else{
                    JOptionPane.showMessageDialog(null , "修改失败");
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

    public static void main(String[] args) throws SQLException {
        new ShowEmpView();

    }
}
