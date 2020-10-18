package frame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class UserInfoView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private  JLabel idLabel = new JLabel("用户ID");
    private  JTextField idField = new JTextField();
    private  JLabel nameLabel = new JLabel("用户姓名");
    private  JTextField  nameField = new JTextField();
    private  JLabel passWordLabel = new JLabel("用户密码");
    private  JPasswordField passWordField = new JPasswordField();
    private  JLabel limitsLabel = new JLabel("用户权限");



    String[] listData = new String[]{"","1", "2", "3"};
    final JComboBox<String> limitsComboBox = new JComboBox<String>(listData);


    private  JTextField limitsField = new JTextField();
    private  JButton addButton = new JButton("添加");
    private  JButton alterButton = new JButton("修改");

    public UserInfoView() throws SQLException {
        this.setTitle("用户信息管理");
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
        idLabel.setBounds(600,30,50,30);
        idField.setBounds(680,35,100,20);
        idField.setEditable(false);
        nameLabel.setBounds(600,90,50,30);
        nameField.setBounds(680,95,100,20);
        passWordLabel.setBounds(600,150,50,30);
        passWordField.setBounds(680,155,100,20);

        limitsLabel.setBounds(600,210,50,30);
        limitsComboBox.setBounds(680,215,100,20);


        addButton.setBounds(600,280,70,30);
        alterButton.setBounds(710,280,70,30);

        //对的图标
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));




        this.add(idLabel);
        this.add(idField);
        this.add(nameLabel);
        this.add(nameField);
        this.add(passWordLabel);
        this.add(passWordField);
        this.add(limitsLabel);
        this.add(limitsComboBox);
        this.add(addButton);
        this.add(alterButton);
        //建立集合
        Vector<String> thVector = new Vector<String>();
        thVector.add("用户编号");
        thVector.add("用户姓名");
        thVector.add("用户密码");
        thVector.add("用户权限");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        //JDBC查出表哥的数据
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select User_id,User_name,User_pwd,User_right from tb_user  ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                vector.add(rs.getString(3));
                vector.add(rs.getString(4));
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

        //为表格添加事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                idField.setText((String) table.getValueAt(row,0));
                nameField.setText((String) table.getValueAt(row,1));
                passWordField.setText((String) table.getValueAt(row,2));
                if(table.getValueAt(row,3).equals("1"))
                limitsComboBox.setSelectedIndex(1);
                else if (table.getValueAt(row,3).equals("2"))
                    limitsComboBox.setSelectedIndex(2);
                else
                    limitsComboBox.setSelectedIndex(3);

            }
        });

        //为修改添加事件
        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String id = idField.getText();
                String name = nameField.getText();
                String passWord = passWordField.getText();
                String limits = (String) limitsComboBox.getSelectedItem();
             // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "update tb_user set User_name = ? , User_pwd = ? , User_right = ?  where User_id = ? ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,name);
                    pss.setObject(2,passWord);
                    pss.setObject(3,limits);
                    pss.setObject(4,id);
                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        table.setValueAt(id , table.getSelectedRow(),0);
                        table.setValueAt(name , table.getSelectedRow(),1);
                        table.setValueAt(passWord , table.getSelectedRow(),2);
                        table.setValueAt(limits , table.getSelectedRow(),3);
//                        JOptionPane.showMessageDialog(null , "修改成功");
                        JOptionPane.showMessageDialog(null, "修改成功","消息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );

                        idField.setText("");
                        nameField.setText("");
                        passWordField.setText("");
                        limitsComboBox.setSelectedIndex(0);
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

                String name = nameField.getText();
                String passWord = passWordField.getText();
                String limits = (String) limitsComboBox.getSelectedItem();
                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "insert into tb_user(user_name,user_pwd,user_right) Values(?,?,?) ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,name);
                    pss.setObject(2,passWord);
                    pss.setObject(3,limits);

                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        JOptionPane.showMessageDialog(null, "添加成功", "信息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
//                        JOptionPane.showMessageDialog(null , "添加成功");
                        dispose();
                         new UserInfoView();
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
        javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        new UserInfoView();
    }
}
