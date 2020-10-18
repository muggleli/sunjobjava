package frame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class EmpInfoView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private  JLabel idLabel = new JLabel("员工编号");
    private  JTextField idField = new JTextField();
    private  JLabel nameLabel = new JLabel("员工姓名");
    private  JTextField  nameField = new JTextField();
    private  JLabel depLabel = new JLabel("员工部门");
    private  JTextField depField = new JTextField();
    private  JLabel rankLabel = new JLabel("员工职别");
    private  JTextField rankField = new JTextField();
    private  JLabel phoneLabel = new JLabel("联系方式");
    private  JTextField phoneField = new JTextField();
    private  JButton addButton = new JButton("添加");
    private  JButton alterButton = new JButton("修改");

    public EmpInfoView() throws SQLException {
        this.setTitle("员工信息管理");
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

        idLabel.setBounds(600,30,50,30);
        idField.setBounds(680,35,100,20);
        idField.setEditable(false);
        nameLabel.setBounds(600,90,50,30);
        nameField.setBounds(680,95,100,20);
        depLabel.setBounds(600,150,50,30);
        depField.setBounds(680,155,100,20);
        rankLabel.setBounds(600,210,50,30);
        rankField.setBounds(680,215,100,20);
        phoneLabel.setBounds(600,270,50,30);
        phoneField.setBounds(680,275,100,20);
        addButton.setBounds(600,340,70,30);
        alterButton.setBounds(710,340,70,30);
        //对的图标
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

        this.add(idLabel);
        this.add(idField);
        this.add(nameLabel);
        this.add(nameField);
        this.add(depLabel);
        this.add(depField);
        this.add(rankLabel);
        this.add(rankField);
        this.add(phoneField);
        this.add(phoneLabel);
        this.add(addButton);
        this.add(alterButton);
        Vector<String> thVector = new Vector<String>();
        thVector.add("员工编号");
        thVector.add("员工姓名");
        thVector.add("员工部门");
        thVector.add("用户职别");
        thVector.add("联系方式");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select Emp_id,Emp_name,Emp_dep,Emp_rank,Emp_phone from tb_emp  ";
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
        scrollPane.setBounds(20, 20, 540, 430);
        add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                idField.setText((String) table.getValueAt(row,0));
                nameField.setText((String) table.getValueAt(row,1));
                depField.setText((String) table.getValueAt(row,2));
                rankField.setText((String) table.getValueAt(row,3));
                phoneField.setText((String) table.getValueAt(row,4));
            }
        });

        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String id = idField.getText();
                String name = nameField.getText();
                String dep = depField.getText();
                String rank =  rankField.getText();
                String phone = phoneField.getText();
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "update tb_emp set Emp_name = ? , Emp_dep = ? , Emp_rank = ?,Emp_phone = ?  where Emp_id = ? ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,name);
                    pss.setObject(2,dep);
                    pss.setObject(3,rank);
                    pss.setObject(4,phone);
                    pss.setObject(5,id);
                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        table.setValueAt(id , table.getSelectedRow(),0);
                        table.setValueAt(name , table.getSelectedRow(),1);
                        table.setValueAt(dep , table.getSelectedRow(),2);
                        table.setValueAt(rank , table.getSelectedRow(),3);
                        table.setValueAt(phone , table.getSelectedRow(),4);
                        JOptionPane.showMessageDialog(null, "修改成功","消息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
                        idField.setText("");
                        nameField.setText("");
                        depField.setText("");
                        rankField.setText("");
                        phoneField.setText("");
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
                String name = nameField.getText();
                String dep = depField.getText();
                String rank =  rankField.getText();
                String phone = phoneField.getText();
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "insert into tb_Emp(Emp_name,Emp_dep,Emp_rank,Emp_phone) Values(?,?,?,?) ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,name);
                    pss.setObject(2,dep);
                    pss.setObject(3,rank);
                    pss.setObject(4,phone);
                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        JOptionPane.showMessageDialog(null, "添加成功", "信息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
                        dispose();
                         new EmpInfoView();
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
        setVisible(true);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        new EmpInfoView();
    }
}
