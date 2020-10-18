package frame;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class EmpAchView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private  JLabel EmpIdLabel = new JLabel("员工编号");
    private  JTextField EmpIdField = new JTextField();
    private  JLabel EmpNameLabel = new JLabel("员工姓名");
    private  JTextField  EmpNameField = new JTextField();
    private  JLabel AchLabel = new JLabel("业绩评分");
    private  JTextField AchField = new JTextField();
    private  JLabel AbilityLabel = new JLabel("能力评分");
    private  JTextField AbilityField = new JTextField();
    private  JLabel AttitudeLabel = new JLabel("态度评分");
    private  JTextField AttitudeField = new JTextField();
    private  JLabel GradeLabel = new JLabel("绩效等级");
    private  JTextField GradeField = new JTextField();

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

    public EmpAchView() throws SQLException {
        this.setTitle("员工绩效管理");
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
        EmpIdLabel.setBounds(20,330,50,30);
        EmpIdField.setBounds(100,338,100,20);
        EmpNameLabel.setBounds(230,330,50,30);
        EmpNameField.setBounds(310,338,100,20);
        AchLabel.setBounds(430,330,50,30);
        AchField.setBounds(510,338,100,20);

        AbilityLabel.setBounds(20,390,50,30);//lineNameLabel lineNameField
        AbilityField.setBounds(100,398,100,20);
        AttitudeLabel.setBounds(230,390,50,30);//tourDaysLabel tourDaysField
        AttitudeField.setBounds(310,398,100,20);
        GradeLabel.setBounds(430,390,50,30);//tourSightsLabel tourSightsField
        GradeField.setBounds(510,398,100,20);



       addButton.setBounds(660,332,70,30);
        alterButton.setBounds(660,393,70,30);
        //添加控件
        this.add(EmpIdLabel);
        this.add(EmpIdField);
        this.add(EmpNameLabel);
        this.add(EmpNameField);
        this.add(AchLabel);
        this.add(AchField);
        this.add(AbilityLabel);
        this.add(AbilityField);
        this.add(AttitudeLabel);
        this.add(AttitudeField);
        this.add(GradeLabel);
        this.add(GradeField);
        this.add(addButton);
        this.add(alterButton);

        //提示对的图标
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

        //建立集合
        Vector<String> thVector = new Vector<String>();
        thVector.add("员工编号");
        thVector.add("员工姓名");
        thVector.add("业绩评分");
        thVector.add("能力评分");
        thVector.add("态度评分");
        thVector.add("绩效等级");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        //JDBC为表格添加数据
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select Emp_id,Emp_name,Ach_ach,Ach_ability,Ach_attitude,Ach_grade from tb_ach  ";
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
                EmpIdField.setText((String) table.getValueAt(row,0));
                EmpNameField.setText((String) table.getValueAt(row,1));
                AchField.setText((String) table.getValueAt(row,2));
                AbilityField.setText((String) table.getValueAt(row,3));
                AttitudeField.setText((String) table.getValueAt(row,4));
                GradeField.setText((String) table.getValueAt(row,5));
            }
        });

        //为修改添加事件
        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String EmpId = EmpIdField.getText();
                String EmpName = EmpNameField.getText();
                String Ach = AchField.getText();
                String Ability = AbilityField.getText();
                String Attitude = AttitudeField.getText();
                String Grade = GradeField.getText();

                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "update tb_ach set Emp_name = ? , Ach_ach = ? ,Ach_ability = ?,Ach_attitude = ?,Ach_grade = ? where Emp_id = ? ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,EmpName);
                    pss.setObject(2,Ach);
                    pss.setObject(3,Ability);
                    pss.setObject(4,Attitude);
                    pss.setObject(5,Grade);
                    pss.setObject(6,EmpId);

                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        table.setValueAt(EmpId , table.getSelectedRow(),0);
                        table.setValueAt(EmpName , table.getSelectedRow(),1);
                        table.setValueAt(Ach , table.getSelectedRow(),2);
                        table.setValueAt(Ability , table.getSelectedRow(),3);
                        table.setValueAt(Attitude , table.getSelectedRow(),4);
                        table.setValueAt(Grade , table.getSelectedRow(),5);
//                        JOptionPane.showMessageDialog(null , "修改成功");
                        JOptionPane.showMessageDialog(null, "修改成功","消息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );

                        EmpIdField.setText("");
                        EmpNameField.setText("");
                        AchField.setText("");
                        AbilityField.setText("");
                        AttitudeField.setText("");
                        GradeField.setText("");
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

                String EmpId = EmpIdField.getText();
                String EmpName = EmpNameField.getText();
                String Ach = AchField.getText();
                String Ability = AbilityField.getText();
                String Attitude = AttitudeField.getText();
                String Grade = GradeField.getText();
                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC","root","root");
//                    String sql = "update tb_line set Line_start = ? , Line_end = ? ,Line_days = ?,Line_sight = ?,Line_designer = ? where Line_no = ? ";
                    String sql = "insert into tb_ach(Emp_id,Emp_name,Ach_ach,Ach_ability,Ach_attitude,Ach_grade) Values(?,?,?,?,?,?) ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,EmpId);
                    pss.setObject(2,EmpName);
                    pss.setObject(3,Ach);
                    pss.setObject(4,Ability);
                    pss.setObject(5,Attitude);
                    pss.setObject(6,Grade);

                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        JOptionPane.showMessageDialog(null, "添加成功", "信息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
//                        JOptionPane.showMessageDialog(null , "添加成功");
                        dispose();
                        new EmpAchView();
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
        new EmpAchView();
    }
}
