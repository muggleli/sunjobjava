package frame;

import Util.DateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class TourOderView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private  JLabel oderIdLabel = new JLabel("班次编号");
    private  JTextField oderIdField = new JTextField();
    private  JLabel oderStartLabel = new JLabel("出发时间");
    private  JTextField  oderStartField = new JTextField();
    private  JLabel oderEndLabel = new JLabel("返回时间");
    private  JTextField oderEndField = new JTextField();
    private  JLabel tourStandardLabel = new JLabel("旅行标准");

    private  JLabel lineIdLabel = new JLabel("线路编号");
    private  JTextField lineIdField = new JTextField();

    private  JButton addButton = new JButton("添加");
    private  JButton alterButton = new JButton("修改");

    String [] listData = new String[]{"","经济","豪华"};
    final  JComboBox<String> tourStandardComboBox = new JComboBox(listData);

    JComboBox<String> lineIdComboBox = new JComboBox<String>();

    public TourOderView() throws SQLException {
        this.setTitle("班次管理");
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

        //添加控件
        oderIdLabel.setBounds(20,330,50,30);
        oderIdField.setBounds(100,338,100,20);
        oderStartLabel.setBounds(230,330,50,30);
        oderStartField.setBounds(310,338,100,20);
        oderEndLabel.setBounds(430,330,50,30);
        oderEndField.setBounds(510,338,100,20);

        tourStandardLabel.setBounds(20,390,50,30);
        tourStandardComboBox.setBounds(100,398,100,20);
        lineIdLabel.setBounds(230,390,50,30);
        lineIdComboBox.setBounds(310,398,100,20);



        addButton.setBounds(660,332,70,30);
        alterButton.setBounds(660,393,70,30);

        //对的图标
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

        lineIdComboBox.addItem("");

        //为线路编号下拉框赋值
        Connection connection3 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
            String sql3 = "select Line_no from tb_line";
            PreparedStatement preparedStatement =connection3.prepareStatement(sql3);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                lineIdComboBox.addItem(resultSet.getString(1));

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection3.close();
        }


        //添加时间控件
        DateChooser startDateChooser =  DateChooser.getInstance("yyyy-MM-dd");
        startDateChooser.register(oderStartField);
        DateChooser endDateChooser =  DateChooser.getInstance("yyyy-MM-dd");
        endDateChooser.register(oderEndField);


        this.add(oderIdLabel);
        this.add(oderIdField);
        this.add(oderStartLabel);
        this.add(oderStartField);
        this.add(oderEndLabel);
        this.add(oderEndField);
        this.add(tourStandardLabel);
        this.add(tourStandardComboBox);
        this.add(lineIdLabel);
        this.add(lineIdComboBox);

        this.add(addButton);
        this.add(alterButton);




        //建立集合
        Vector<String> thVector = new Vector<String>();
        thVector.add("班次编号");
        thVector.add("出发时间");
        thVector.add("返回时间");
        thVector.add("旅行标准");
        thVector.add("线路编号");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        //JDBC为表格添加数据
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select Order_no,Order_go,Order_back,Order_standard,Line_no from tb_Order  ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                vector.add(rs.getString(3));
                vector.add(rs.getString(4));
                vector.add(rs.getString(5));
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

//为表格添加事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                oderIdField.setText((String) table.getValueAt(row,0));
                oderStartField.setText((String) table.getValueAt(row,1));
                oderEndField.setText((String) table.getValueAt(row,2));
                if(table.getValueAt(row,3).equals("经济"))
                    tourStandardComboBox.setSelectedIndex(1);
                else
                    tourStandardComboBox.setSelectedIndex(2);

                for (int i = 0 ; i<lineIdComboBox.getItemCount();i++)
                {
                    if(lineIdComboBox.getItemAt(i).equals(table.getValueAt(row,4)))
                    lineIdComboBox.setSelectedIndex(i);
                }

            }
        });

        //为修改添加事件
        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String oderId = oderIdField.getText();
                String oderStart = oderStartField.getText();
                String oderEnd = oderEndField.getText();
                String tourStandard = (String) tourStandardComboBox.getSelectedItem();
                String lineId = (String) lineIdComboBox.getSelectedItem();


                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "update tb_order set Order_go = ? , Order_back = ? ,Order_standard = ?,Line_no = ? where Order_no = ? ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,oderStart);
                    pss.setObject(2,oderEnd);
                    pss.setObject(3,tourStandard);
                    pss.setObject(4,lineId);
                    pss.setObject(5,oderId);


                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        table.setValueAt(oderId , table.getSelectedRow(),0);
                        table.setValueAt(oderStart , table.getSelectedRow(),1);
                        table.setValueAt(oderEnd , table.getSelectedRow(),2);
                        table.setValueAt(tourStandard , table.getSelectedRow(),3);
                        table.setValueAt(lineId , table.getSelectedRow(),4);

//                        JOptionPane.showMessageDialog(null , "修改成功");
                        JOptionPane.showMessageDialog(null, "修改成功","消息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );

                        lineIdField.setText("");
                        oderIdField.setText("");
                        oderEndField.setText("");
                        oderStartField.setText("");
                        tourStandardComboBox.setSelectedIndex(0);
                        lineIdComboBox.setSelectedIndex(0);

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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String oderId = oderIdField.getText();
                String oderStart = oderStartField.getText();
                String oderEnd = oderEndField.getText();
                String tourStandard = (String) tourStandardComboBox.getSelectedItem();
                String lineId = (String) lineIdComboBox.getSelectedItem();

                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
//                    String sql = "update tb_line set Line_start = ? , Line_end = ? ,Line_days = ?,Line_sight = ?,Line_designer = ? where Line_no = ? ";
                    String sql = "insert into tb_order(Order_no,Order_go,Order_back,Order_standard,Line_no) Values(?,?,?,?,?) ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,oderId);
                    pss.setObject(2,oderStart);
                    pss.setObject(3,oderEnd);
                    pss.setObject(4,tourStandard);
                    pss.setObject(5,lineId);


                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        JOptionPane.showMessageDialog(null, "添加成功", "信息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
//                        JOptionPane.showMessageDialog(null , "添加成功");
                        dispose();
                        new TourOderView();
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


        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        new TourOderView();
    }
}
