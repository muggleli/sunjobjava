package frame;

import util.DateChooser;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class CheckHouseInfoView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private  JLabel houseLabel = new JLabel("房屋编号");
    private  JTextField houseField = new JTextField();
    private  JLabel houseNameLabel = new JLabel("房屋名称");
    private  JTextField houseNameField = new JTextField();
    private  JLabel renterIdLabel = new JLabel("租户编号");
    private  JTextField renterIdField = new JTextField();
    private  JLabel renterNameLabel = new JLabel("租户名称");
    private  JTextField renterNameField = new JTextField();
    private  JLabel timeLabel = new JLabel("看房时间");
    private  JTextField timeField = new JTextField();


    private  JButton alterButton = new JButton("申请看房");

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

    public CheckHouseInfoView() throws SQLException {
        this.setTitle("房源信息查询");
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
        houseLabel.setBounds(20,330,50,30);
        houseField.setBounds(100,338,100,20);
        houseNameLabel.setBounds(230,330,50,30);
        houseNameField.setBounds(310,338,100,20);
        renterIdLabel.setBounds(430,330,50,30);
        renterIdField.setBounds(510,338,100,20);

        renterNameLabel.setBounds(20,390,50,30);
        renterNameField.setBounds(100,398,100,20);
        timeLabel.setBounds(230,390,50,30);
        timeField.setBounds(310,398,100,20);



        alterButton.setBounds(670,365,70,30);
        //添加控件

        DateChooser startDateChooser =  DateChooser.getInstance("yyyy-MM-dd");
        startDateChooser.register(timeField);


        this.add(houseLabel);
        this.add(houseField);
        this.add(houseNameLabel);
        this.add(houseNameField);
        this.add(renterIdLabel);
        this.add(renterIdField);
        this.add(renterNameLabel);
        this.add(renterNameField);
        this.add(timeLabel);
        this.add(timeField);

        this.add(alterButton);

        //提示对的图标
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

        //建立集合
        Vector<String> thVector = new Vector<String>();
        thVector.add("房屋编号");
        thVector.add("房屋名称");
        thVector.add("房主姓名");
        thVector.add("房屋地址");
        thVector.add("房屋面积");
        thVector.add("房屋租金");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        //JDBC为表格添加数据
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select House_id,House_name,Owner_name,House_address,House_measure,House_rent from tb_house where House_state ='可出租'   ";
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
                houseField.setText((String) table.getValueAt(row,0));
                houseNameField.setText((String) table.getValueAt(row,1));

            }
        });

        //为修改添加事件
        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String house = houseField.getText();
                String houseName = houseNameField.getText();
                String renterId = renterIdField.getText();
                String renterName =  renterNameField.getText();
                String time =  timeField.getText();

                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC","root","root");
//                    String sql = "update tb_line set Line_start = ? , Line_end = ? ,Line_days = ?,Line_sight = ?,Line_designer = ? where Line_no = ? ";
                    String sql = "insert into tb_apply(House_id,House_name,Renter_id,Renter_name,Apply_time) Values(?,?,?,?,?) ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,house);
                    pss.setObject(2,houseName);
                    pss.setObject(3,renterId);
                    pss.setObject(4,renterName);
                    pss.setObject(5,time);



                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        JOptionPane.showMessageDialog(null, "申请成功", "信息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
//                        JOptionPane.showMessageDialog(null , "添加成功");
                        dispose();
                        new CheckHouseInfoView();
                    }
                    else{
                        JOptionPane.showMessageDialog(null , "申请失败");
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


        this.setVisible(true);
    }

    //设置表格列宽自适应

    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        new CheckHouseInfoView();
    }
}
