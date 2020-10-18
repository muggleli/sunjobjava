package frame;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class TourLineView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private  JLabel lineIdLabel = new JLabel("线路编号");
    private  JTextField lineIdField = new JTextField();
    private  JLabel lineStartLabel = new JLabel("线路起点");
    private  JTextField  lineStartField = new JTextField();
    private  JLabel lineEndLabel = new JLabel("线路终点");
    private  JTextField lineEndField = new JTextField();
    private  JLabel tourDaysLabel = new JLabel("旅行天数");
    private  JTextField tourDaysField = new JTextField();
    private  JLabel tourSightsLabel = new JLabel("旅行景点");
    private  JTextField tourSightsField = new JTextField();
    private  JLabel lineNameLabel = new JLabel("线路名称");
    private  JTextField lineNameField = new JTextField();

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

    public TourLineView() throws SQLException {
        this.setTitle("线路管理");
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
        lineIdLabel.setBounds(20,330,50,30);
        lineIdField.setBounds(100,338,100,20);
        lineStartLabel.setBounds(230,330,50,30);
        lineStartField.setBounds(310,338,100,20);
        lineEndLabel.setBounds(430,330,50,30);
        lineEndField.setBounds(510,338,100,20);

        lineNameLabel.setBounds(20,390,50,30);//lineNameLabel lineNameField
        lineNameField.setBounds(100,398,100,20);
        tourDaysLabel.setBounds(230,390,50,30);//tourDaysLabel tourDaysField
        tourDaysField.setBounds(310,398,100,20);
        tourSightsLabel.setBounds(430,390,50,30);//tourSightsLabel tourSightsField
        tourSightsField.setBounds(510,398,100,20);



       addButton.setBounds(660,332,70,30);
        alterButton.setBounds(660,393,70,30);
        //添加控件
        this.add(lineIdLabel);
        this.add(lineIdField);
        this.add(lineStartLabel);
        this.add(lineStartField);
        this.add(lineEndLabel);
        this.add(lineEndField);
        this.add(tourDaysLabel);
        this.add(tourDaysField);
        this.add(tourSightsLabel);
        this.add(tourSightsField);
        this.add(lineNameLabel);
        this.add(lineNameField);
        this.add(addButton);
        this.add(alterButton);

        //提示对的图标
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

        //建立集合
        Vector<String> thVector = new Vector<String>();
        thVector.add("线路编号");
        thVector.add("线路起点");
        thVector.add("线路终点");
        thVector.add("线路名称");
        thVector.add("旅行天数");
        thVector.add("旅行景点");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        //JDBC为表格添加数据
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select Line_no,Line_start,Line_end,Line_name,Line_days,Line_sight from tb_line  ";
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
        //设置表格列宽自适应
        this.adjustTableColumnWidths(table);

        //为表格添加监听事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                lineIdField.setText((String) table.getValueAt(row,0));
                lineStartField.setText((String) table.getValueAt(row,1));
                lineEndField.setText((String) table.getValueAt(row,2));
                lineNameField.setText((String) table.getValueAt(row,3));
                tourDaysField.setText((String) table.getValueAt(row,4));
                tourSightsField.setText((String) table.getValueAt(row,5));
            }
        });

        //为修改添加事件
        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String lineId = lineIdField.getText();
                String lineStart = lineStartField.getText();
                String lineEnd = lineEndField.getText();
                String tourDays = tourDaysField.getText();
                String tourSights = tourSightsField.getText();
                String lineName = lineNameField.getText();

                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "update tb_line set Line_start = ? , Line_end = ? ,Line_days = ?,Line_sight = ?,Line_name = ? where Line_no = ? ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,lineStart);
                    pss.setObject(2,lineEnd);
                    pss.setObject(3,tourDays);
                    pss.setObject(4,tourSights);
                    pss.setObject(5,lineName);
                    pss.setObject(6,lineId);

                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        table.setValueAt(lineId , table.getSelectedRow(),0);
                        table.setValueAt(lineStart , table.getSelectedRow(),1);
                        table.setValueAt(lineEnd , table.getSelectedRow(),2);
                        table.setValueAt(lineName , table.getSelectedRow(),3);
                        table.setValueAt(tourDays , table.getSelectedRow(),4);
                        table.setValueAt(tourSights , table.getSelectedRow(),5);
//                        JOptionPane.showMessageDialog(null , "修改成功");
                        JOptionPane.showMessageDialog(null, "修改成功","消息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );

                        lineIdField.setText("");
                        lineStartField.setText("");
                        lineEndField.setText("");
                        tourDaysField.setText("");
                        tourSightsField.setText("");
                        lineNameField.setText("");
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

                String lineId = lineIdField.getText();
                String lineStart = lineStartField.getText();
                String lineEnd = lineEndField.getText();
                String tourDays = tourDaysField.getText();
                String tourSights = tourSightsField.getText();
                String lineName = lineNameField.getText();
                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
//                    String sql = "update tb_line set Line_start = ? , Line_end = ? ,Line_days = ?,Line_sight = ?,Line_designer = ? where Line_no = ? ";
                    String sql = "insert into tb_line(Line_no,Line_start,Line_end,Line_name,Line_days,Line_sight) Values(?,?,?,?,?,?) ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,lineId);
                    pss.setObject(2,lineStart);
                    pss.setObject(3,lineEnd);
                    pss.setObject(4,lineName);
                    pss.setObject(5,tourDays);
                    pss.setObject(6,tourSights);

                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        JOptionPane.showMessageDialog(null, "添加成功", "信息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
//                        JOptionPane.showMessageDialog(null , "添加成功");
                        dispose();
                        new TourLineView();
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
        javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        new TourLineView();
    }
}
