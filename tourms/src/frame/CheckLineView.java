package frame;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Vector;

public class CheckLineView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    };




    private  JLabel title = new JLabel("旅游线路查询");

    private  JLabel startLabel = new JLabel("线路起点");
    private  JTextField startField = new JTextField();
    private  JLabel endLabel = new JLabel("线路终点");
    private  JTextField endField = new JTextField();
    private  JButton checkButton = new JButton("查询");

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

    public CheckLineView () throws SQLException {
        this.setTitle("旅游信息查询");
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


        //建立集合
        Vector<String> thVector = new Vector<String>();
        thVector.add("线路编号");
        thVector.add("线路名称");
        thVector.add("线路起点");
        thVector.add("线路终点");
        thVector.add("旅行天数");
        thVector.add("主要景点");
        thVector.add("开设班次");

        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        startLabel.setBounds(100,65,50,50);
        startField.setBounds(200,82,100,20);
        endLabel.setBounds(350,65,50,50);
        endField.setBounds(450,82,100,20);
        checkButton.setBounds(600,81,70,20);
        title.setBounds(330,10,250,50);


        Font font = new Font("宋体", Font.PLAIN, 25);//创建1个字体实例
        title.setFont(font);//设置JLabel的字体
//        title.setForeground(Color.PINK);//设置文字的颜色

        this.add(startLabel);
        this.add(startField);
        this.add(endLabel);
        this.add(endField);
        this.add(checkButton);
        this.add(title);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

          String start=startField.getText();
          String end=endField.getText();

        //JDBC为表格添加数据
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select l.Line_no,Line_name,Line_start,Line_end,Line_days,Line_sight,Order_no from tb_line l,tb_order o  where  l.Line_no = o.Line_no and l.Line_start=? and l.Line_end=?  ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, start);
            ps.setObject(2, end);
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
                DefaultTableModel defaultTableModel = new DefaultTableModel(dataVector,thVector);
                table.setModel(defaultTableModel);

                scrollPane.getViewport().add(table);

            }
        } catch (ClassNotFoundException | SQLException e) {
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
        this.adjustTableColumnWidths(table);

        DefaultTableModel defaultTableModel = new DefaultTableModel(dataVector,thVector);
        table.setModel(defaultTableModel);

        scrollPane.getViewport().add(table);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,defaultTableCellRenderer);

        scrollPane.setBounds(20, 140, 790, 280);
        add(scrollPane);



        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        new CheckLineView();
    }
}
