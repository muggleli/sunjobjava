package frame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class GroupInfoView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    private  JLabel groupIdLabel = new JLabel("旅行团号");
    private  JTextField groupIdField = new JTextField();
    private  JLabel groupStartLabel = new JLabel("旅行团名");
    private  JTextField  groupStartField = new JTextField();
    private  JLabel guideLabel = new JLabel("导游");
    private  JTextField guideField = new JTextField();
    private  JLabel peoplesLabel = new JLabel("旅行人数");
    private  JTextField peoplesField = new JTextField();
    private  JLabel telLabel = new JLabel("联系电话");
    private  JTextField telField = new JTextField();
    private  JLabel oderNoLabel = new JLabel("班次编号");
    private  JTextField oderNoField = new JTextField();
    private  JComboBox<String> oderIdComboBox = new JComboBox<String>();

    private  JButton addButton = new JButton("添加");
    private  JButton alterButton = new JButton("修改");

    private  JButton checkGuideButton = new JButton("查询");

    public GroupInfoView() throws SQLException {
        this.setTitle("组团管理");
        this.setSize(830, 500);//设置窗体的大小
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

        groupIdLabel.setBounds(20,330,50,30);
        groupIdField.setBounds(100,338,100,20);
        groupStartLabel.setBounds(230,330,50,30);
        groupStartField.setBounds(310,338,100,20);
        guideLabel.setBounds(430,330,50,30);
        guideField.setBounds(510,338,100,20);

        peoplesLabel.setBounds(20,390,50,30);//lineNameLabel lineNameField
        peoplesField.setBounds(100,398,100,20);
        telLabel.setBounds(230,390,50,30);//tourDaysLabel tourDaysField
        telField.setBounds(310,398,100,20);
        oderNoLabel.setBounds(430,390,50,30);//tourSightsLabel tourSightsField
        oderIdComboBox.setBounds(510,398,100,20);



        addButton.setBounds(660,332,70,30);
        alterButton.setBounds(660,393,70,30);

        checkGuideButton.setBounds(510,310,70,20);

        oderIdComboBox.addItem("");

        this.add(groupIdLabel);
        this.add(groupIdField);
        this.add(groupStartLabel);
        this.add(groupStartField);
        this.add(guideLabel);
        this.add(guideField);
        this.add(peoplesLabel);
        this.add(peoplesField);
        this.add(telLabel);
        this.add(telField);
        this.add(oderNoLabel);
        this.add(oderIdComboBox);
        this.add(addButton);
        this.add(alterButton);
        this.add(checkGuideButton);
        //提示对的图标
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

        //建立集合
        Vector<String> thVector = new Vector<String>();
        thVector.add("旅行团号");
        thVector.add("旅行团名");
        thVector.add("导游");
        thVector.add("旅行人数");
        thVector.add("联系电话");
        thVector.add("班次编号");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        //JDBC为表格添加数据
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select Group_no,Group_name,Group_charge,Group_peoples,Group_phone,Order_no from tb_group  ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                vector.add(rs.getString(3));
                vector.add(rs.getString(4));
                vector.add(rs.getString(5));
                vector.add(rs.getString(6));
                dataVector.add(vector);
            }
        } catch (ClassNotFoundException | SQLException e) {
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

        scrollPane.setBounds(20, 20, 790, 280);
        add(scrollPane);

        //为表格添加监听事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                groupIdField.setText((String) table.getValueAt(row,0));
                groupStartField.setText((String) table.getValueAt(row,1));
                guideField.setText((String) table.getValueAt(row,2));
                peoplesField.setText((String) table.getValueAt(row,3));
                telField.setText((String) table.getValueAt(row,4));
                for (int i = 0 ; i<oderIdComboBox.getItemCount();i++)
                {
                    if(oderIdComboBox.getItemAt(i).equals(table.getValueAt(row,5)))
                        oderIdComboBox.setSelectedIndex(i);
                }
            }
        });

        //为下拉框赋值
        Connection connection3 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
            String sql3 = "select Order_no from tb_order";
            PreparedStatement preparedStatement =connection3.prepareStatement(sql3);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                oderIdComboBox.addItem(resultSet.getString(1));

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection3.close();
        }

        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String Id = groupIdField.getText();
                String groupName = groupStartField.getText();
                String guide = guideField.getText();
                String peoples = peoplesField.getText();
                String tel = telField.getText();
                String oderId = (String) oderIdComboBox.getSelectedItem();


                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "update tb_group set Group_name = ? ,Group_charge = ?,Group_peoples = ?,Group_phone = ?,Order_no=? where Group_no = ? ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,groupName);
                    pss.setObject(2,guide);
                    pss.setObject(3,peoples);
                    pss.setObject(4,tel);
                    pss.setObject(5,oderId);
                    pss.setObject(6,Id);


                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        table.setValueAt(Id , table.getSelectedRow(),0);
                        table.setValueAt(groupName , table.getSelectedRow(),1);
                        table.setValueAt(guide , table.getSelectedRow(),2);
                        table.setValueAt(peoples , table.getSelectedRow(),3);
                        table.setValueAt(tel , table.getSelectedRow(),4);
                        table.setValueAt(oderId , table.getSelectedRow(),5);


//                        JOptionPane.showMessageDialog(null , "修改成功");
                        JOptionPane.showMessageDialog(null, "修改成功","消息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );

                        groupIdField.setText("");
                        groupStartField.setText("");

                        guideField.setText("");
                        telField.setText("");
                        oderIdComboBox.setSelectedIndex(0);

                    }
                    else{
                        JOptionPane.showMessageDialog(null , "修改失败");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        //为增加添加监听事件
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String Id = groupIdField.getText();
                String groupName = groupStartField.getText();
                String guide = guideField.getText();
                String peoples = peoplesField.getText();
                String tel = telField.getText();
                String oderId = (String) oderIdComboBox.getSelectedItem();
                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
//                    String sql = "update tb_line set Line_start = ? , Line_end = ? ,Line_days = ?,Line_sight = ?,Line_designer = ? where Line_no = ? ";
                    String sql2 = "update tb_guide set Oder_no=? where Guide_name = ?";
                    PreparedStatement ps = connection.prepareStatement(sql2);

                    ps.setObject(1,oderId);
                    ps.setObject(2,guide);
                    int b=ps.executeUpdate();
                    String sql = "insert into tb_group(Group_no,Group_name,Group_charge,Group_peoples,Group_phone,Order_no) Values(?,?,?,?,?,?) ";
                    PreparedStatement pss = connection.prepareStatement(sql);


                    pss.setObject(1,Id);
                    pss.setObject(2,groupName);
                    pss.setObject(3,guide);
                    pss.setObject(4,peoples);
                    pss.setObject(5,tel);
                    pss.setObject(6,oderId);

                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        JOptionPane.showMessageDialog(null, "添加成功", "信息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
//                        JOptionPane.showMessageDialog(null , "添加成功");
                        dispose();
                        new GroupInfoView();
                    }
                    else{
                        JOptionPane.showMessageDialog(null , "添加失败");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        checkGuideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new CheckGuideView();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setVisible(true);

    }

    public static void main(String[] args) throws SQLException {
        new GroupInfoView();
    }
}
