package frame;

import util.DateChooser;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class FinView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private  JLabel monthLabel = new JLabel("月份");
    private  JTextField monthField = new JTextField();
    private  JLabel assetLabel = new JLabel("公司资产");
    private  JTextField  assetField = new JTextField();
    private  JLabel incomeLabel = new JLabel("本月收入");
    private  JTextField incomeField = new JTextField();
    private  JLabel expendLabel = new JLabel("本月支出");
    private  JTextField expendField = new JTextField();

    private  JLabel endDateLabel = new JLabel("本月利润");
    private  JTextField endDateField = new JTextField();

    private  JButton addButton = new JButton("添加");
    private  JButton alterButton = new JButton("修改");

    private   static   void   adjustTableColumnWidths(JTable   table)
    {
        JTableHeader header   =   table.getTableHeader();     //表头
        int   rowCount   =   table.getRowCount();     //表格的行数
        TableColumnModel cm   =   table.getColumnModel();     //表格的列模型

        for   (int   i   =   0;   i   <   cm.getColumnCount();   i++)   {     //循环处理每一列
            TableColumn column   =   cm.getColumn(i);                     //第i个列对象
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
    /*String [] listData = new String[]{"","经济","豪华"};
    final  JComboBox<String> tourStandardComboBox = new JComboBox(listData);

    JComboBox<String> lineIdComboBox = new JComboBox<String>();*/

    public FinView() throws SQLException {
        this.setTitle("财务报告管理");
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
        monthLabel.setBounds(20,330,50,30);
        monthField.setBounds(100,338,100,20);
        assetLabel.setBounds(230,330,50,30);
        assetField.setBounds(310,338,100,20);
        incomeLabel.setBounds(430,330,50,30);
        incomeField.setBounds(510,338,100,20);

        expendLabel.setBounds(20,390,50,30);
        expendField.setBounds(100,398,100,20);
        endDateLabel.setBounds(230,390,50,30);
        endDateField.setBounds(310,398,100,20);

        addButton.setBounds(660,332,70,30);
        alterButton.setBounds(660,393,70,30);

        //对的图标
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

      /*  lineIdComboBox.addItem("");
*/
        //为线路编号下拉框赋值
       /* Connection connection3 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
            String sql3 = "select Line_no from tb_line";
            PreparedStatement preparedStatement =connection3.prepareStatement(sql3);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                endDateField.addItem(resultSet.getString(1));

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection3.close();
        }*/


       /* //添加时间控件
        DateChooser startDateChooser =  DateChooser.getInstance("yyyy-MM-dd");
        startDateChooser.register();
        DateChooser endDateChooser =  DateChooser.getInstance("yyyy-MM-dd");
        endDateChooser.register();*/


        this.add(monthLabel);
        this.add(monthField);
        this.add(assetLabel);
        this.add(assetField);
        this.add(incomeLabel);
        this.add(incomeField);
        this.add(expendLabel);
        this.add(expendField);
        this.add(endDateLabel);
        this.add(endDateField);


        this.add(addButton);
        this.add(alterButton);




        //建立集合
        Vector<String> thVector = new Vector<String>();
        thVector.add("月份");
        thVector.add("公司资产");
        thVector.add("本月收入");
        thVector.add("本月支出");
        thVector.add("本月利润");

        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        //JDBC为表格添加数据
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select Fin_month,Fin_assets,Fin_income,Fin_expend,Fin_profit from tb_fin  ";
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
        this.adjustTableColumnWidths(table);
//为表格添加事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                monthField.setText((String) table.getValueAt(row,0));
                assetField.setText((String) table.getValueAt(row,1));
                incomeField.setText((String) table.getValueAt(row,2));
                expendField.setText((String) table.getValueAt(row,3));
                endDateField.setText((String) table.getValueAt(row,4));

            }
        });

        //为修改添加事件
        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String month = monthField.getText();
                String asset = assetField.getText();
                String income = incomeField.getText();
                String arrangeDep =  expendField.getText();
                String endDate =  endDateField.getText();


                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "update tb_fin set Fin_assets = ? ,Fin_income = ? ,Fin_expend = ? ,Fin_profit = ?  where Fin_month = ? ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,asset);
                    pss.setObject(2,income);
                    pss.setObject(3,arrangeDep);
                    pss.setObject(4,endDate);

                    pss.setObject(5,month);


                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        table.setValueAt(month , table.getSelectedRow(),0);
                        table.setValueAt(asset , table.getSelectedRow(),1);
                        table.setValueAt(income , table.getSelectedRow(),2);
                        table.setValueAt(arrangeDep , table.getSelectedRow(),3);
                        table.setValueAt(endDate , table.getSelectedRow(),4);


//                        JOptionPane.showMessageDialog(null , "修改成功");
                        JOptionPane.showMessageDialog(null, "修改成功","消息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
                        monthField.setText("");
                        assetField.setText("");
                        incomeField.setText("");
                        expendField.setText("");
                        endDateField.setText("");



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

                String month = monthField.getText();
                String asset = assetField.getText();
                String income = incomeField.getText();
                String expend =  expendField.getText();
                String endDate =  endDateField.getText();

                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC","root","root");
//                    String sql = "update tb_line set Line_start = ? , Line_end = ? ,Line_days = ?,Line_sight = ?,Line_designer = ? where Line_no = ? ";
                    String sql = "insert into tb_fin(Fin_month,Fin_assets,Fin_income,Fin_expend,Fin_profit) Values(?,?,?,?,?) ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,month);
                    pss.setObject(2,asset);
                    pss.setObject(3,income);
                    pss.setObject(4,expend);
                    pss.setObject(5,endDate);



                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        JOptionPane.showMessageDialog(null, "添加成功", "信息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
//                        JOptionPane.showMessageDialog(null , "添加成功");
                        dispose();
                        new FinView();
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
        new FinView();
    }
}
