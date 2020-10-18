package frame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Vector;

public class CheckVisitorView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private  JLabel title = new JLabel("游客信息查询");

    private  JLabel startLabel = new JLabel("游客姓名");
    private  JTextField startField = new JTextField();
    private  JButton checkButton = new JButton("查询");
    public CheckVisitorView(){
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
        thVector.add("游客编号");
        thVector.add("游客姓名");
        thVector.add("游客性别");
        thVector.add("游客住址");
        thVector.add("联系电话");
        thVector.add("所在班次");
        thVector.add("所属旅行团");

        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        startLabel.setBounds(100,65,50,50);
        startField.setBounds(200,82,100,20);

        checkButton.setBounds(600,81,70,20);
        title.setBounds(330,10,250,50);


        Font font = new Font("宋体", Font.PLAIN, 25);//创建1个字体实例
        title.setFont(font);//设置JLabel的字体
//        title.setForeground(Color.PINK);//设置文字的颜色

        this.add(startLabel);
        this.add(startField);

        this.add(checkButton);
        this.add(title);



        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String start=startField.getText();

                //JDBC为表格添加数据
                Connection connection = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC", "root", "root");
                    String sql = "select Visitors_no,Visitors_name,Visitors_sex,Visitors_address,Visitors_phone,v.oder_no,Group_name from tb_visitor v,tb_group o  where  v.Oder_no = o.Order_no and v.Visitors_name=?   ";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1, start);

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

    public static void main(String[] args) {
        new CheckVisitorView();
    }

}
