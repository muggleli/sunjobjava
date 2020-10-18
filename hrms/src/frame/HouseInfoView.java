package frame;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class HouseInfoView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private  JLabel houseIdLabel = new JLabel("房屋编号");
    private  JTextField houseIdField = new JTextField();
    private  JLabel houseNameLabel = new JLabel("房屋名称");
    private  JTextField  houseNameField = new JTextField();
    private  JLabel ownerLabel = new JLabel("房主名");
    private  JTextField ownerField = new JTextField();
    private  JLabel addressLabel = new JLabel("房主地址");
    private  JTextField addressField = new JTextField();
    private  JLabel measureLabel = new JLabel("房屋面积");
    private  JTextField measureField = new JTextField();
    private  JLabel rentLabel = new JLabel("租金");
    private  JTextField rentField = new JTextField();
    private  JLabel stateLabel = new JLabel("房屋状态");
    private  JTextField stateField = new JTextField();

    private  JButton addButton = new JButton("添加");
    private  JButton alterButton = new JButton("修改");

    //设置表哥列宽自适应
    private   static   void   adjustTableColumnWidths(JTable   table)
    {
        JTableHeader header   =   table.getTableHeader();     //表头
        int   rowCount   =   table.getRowCount();     //表格的行数
        TableColumnModel   cm   =   table.getColumnModel();     //表格的列模型

        for   (int   i   =   0;   i   <   cm.getColumnCount();   i++)   {     //循环处理每一列
            TableColumn   column   =   cm.getColumn(i);                     //第i个列对象
            int   width   =
                    (int)header.getDefaultRenderer().getTableCellRendererComponent(table,
                            column.getIdentifier(),   false,   false,   -1,
                            i).getPreferredSize().getWidth();     //用表头的绘制器计算第i列表头的宽度
            for(int   row   =   0;   row<rowCount;   row++){     //循环处理第i列的每一行，用单元格绘制器计算第i列第row行的单元格宽度
                int   preferedWidth   =   (int)table.getCellRenderer(row,
                        i).getTableCellRendererComponent(table,   table.getValueAt(row,   i),
                        false,   false,   row,   i).getPreferredSize().getWidth();
                width   =   Math.max(width,   preferedWidth);     //取最大的宽度
            }
            column.setPreferredWidth(width+table.getIntercellSpacing().width);     //设置第i列的首选宽度
        }

        table.doLayout();       //按照刚才设置的宽度重新布局各个列
    }

    public HouseInfoView() throws SQLException {
        this.setTitle("房源信息管理");
        this.setSize(830, 520);//设置窗体的大小
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
        houseIdLabel.setBounds(20,330,50,30);
        houseIdField.setBounds(100,338,100,20);
        houseNameLabel.setBounds(230,330,50,30);
        houseNameField.setBounds(310,338,100,20);
        ownerLabel.setBounds(430,330,50,30);
        ownerField.setBounds(510,338,100,20);

        addressLabel.setBounds(20,390,50,30);//lineNameLabel lineNameField
        addressField.setBounds(100,398,100,20);
        measureLabel.setBounds(230,390,50,30);//tourDaysLabel tourDaysField
        measureField.setBounds(310,398,100,20);
        rentLabel.setBounds(430,390,50,30);//tourSightsLabel tourSightsField
        rentField.setBounds(510,398,100,20);

        stateLabel.setBounds(230,450,50,30);
        stateField.setBounds(310,458,100,20);

       addButton.setBounds(660,332,70,30);
        alterButton.setBounds(660,393,70,30);
        //添加控件
        this.add(houseIdLabel);
        this.add(houseIdField);
        this.add(houseNameLabel);
        this.add(houseNameField);
        this.add(ownerLabel);
        this.add(ownerField);
        this.add(addressLabel);
        this.add(addressField);
        this.add(measureLabel);
        this.add(measureField);
        this.add(rentLabel);
        this.add(rentField);
        this.add(stateLabel);
        this.add(stateField);
        this.add(addButton);
        this.add(alterButton);

        //提示对的图标
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

        //建立集合
        Vector<String> thVector = new Vector<String>();
        thVector.add("房屋编号");
        thVector.add("房屋名称");
        thVector.add("房主名");
        thVector.add("房主地址");
        thVector.add("房屋面积");
        thVector.add("租金");
        thVector.add("房屋状态");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        //JDBC为表格添加数据
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select House_id,House_name,Owner_name,House_address,House_measure,House_rent,House_state from tb_house  ";
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
                vector.add(rs.getString(7));
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
        //设置表格列宽自适应
        this.adjustTableColumnWidths(table);

        //为表格添加监听事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                houseIdField.setText((String) table.getValueAt(row,0));
                houseNameField.setText((String) table.getValueAt(row,1));
                ownerField.setText((String) table.getValueAt(row,2));
                addressField.setText((String) table.getValueAt(row,3));
                measureField.setText((String) table.getValueAt(row,4));
                rentField.setText((String) table.getValueAt(row,5));
                stateField.setText((String) table.getValueAt(row,6));
            }
        });

        //为修改添加事件
        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String houseId = houseIdField.getText();
                String houseName = houseNameField.getText();
                String owner = ownerField.getText();
                String address = addressField.getText();
                String measure = measureField.getText();
                String rent = rentField.getText();
                String state = stateField.getText();

                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "update tb_house set House_name = ? , Owner_name = ? ,House_address = ?,House_measure = ?,House_rent = ? ,House_state=? where House_id = ? ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,houseName);
                    pss.setObject(2,owner);
                    pss.setObject(3,address);
                    pss.setObject(4,measure);
                    pss.setObject(5,rent);
                    pss.setObject(6,state);
                    pss.setObject(7,houseId);

                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        table.setValueAt(houseId , table.getSelectedRow(),0);
                        table.setValueAt(houseName , table.getSelectedRow(),1);
                        table.setValueAt(owner , table.getSelectedRow(),2);
                        table.setValueAt(address , table.getSelectedRow(),3);
                        table.setValueAt(measure , table.getSelectedRow(),4);
                        table.setValueAt(rent , table.getSelectedRow(),5);
                        table.setValueAt(state , table.getSelectedRow(),6);
//                        JOptionPane.showMessageDialog(null , "修改成功");
                        JOptionPane.showMessageDialog(null, "修改成功","消息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );

                        houseIdField.setText("");
                        houseNameField.setText("");
                        ownerField.setText("");
                        addressField.setText("");
                        measureField.setText("");
                        rentField.setText("");
                        stateField.setText("");
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

                String houseId = houseIdField.getText();
                String houseName = houseNameField.getText();
                String owner = ownerField.getText();
                String address = addressField.getText();
                String measure = measureField.getText();
                String rent = rentField.getText();
                String state = stateField.getText();
                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC","root","root");
//                    String sql = "update tb_line set Line_start = ? , Line_end = ? ,Line_days = ?,Line_sight = ?,Line_designer = ? where Line_no = ? ";
                    String sql = "insert into tb_house(House_id,House_name,Owner_name,House_address,House_measure,House_rent,House_state) Values(?,?,?,?,?,?,?) ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,houseId);
                    pss.setObject(2,houseName);
                    pss.setObject(3,owner);
                    pss.setObject(4,address);
                    pss.setObject(5,measure);
                    pss.setObject(6,rent);
                    pss.setObject(7,state);

                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        JOptionPane.showMessageDialog(null, "添加成功", "信息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
//                        JOptionPane.showMessageDialog(null , "添加成功");
                        dispose();
                        new HouseInfoView();
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

    //设置表格列宽自适应

    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        new HouseInfoView();
    }
}
