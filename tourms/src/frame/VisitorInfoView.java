package frame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class VisitorInfoView extends JDialog {
    private  JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private  JTable table =new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private  JLabel idLabel = new JLabel("游客编号");
    private  JTextField idField = new JTextField();
    private  JLabel nameLabel = new JLabel("游客姓名");
    private  JTextField  nameField = new JTextField();

    private  JLabel sexLabel = new JLabel("游客性别");
   // private  JTextField sexField = new JTextField();
    private JRadioButton maleButton = new JRadioButton("男");
    private JRadioButton femaleButton = new JRadioButton("女");

    private  JLabel addressLabel = new JLabel("游客住址");
    private  JTextField addressField = new JTextField();
    private  JLabel telLabel = new JLabel("联系电话");
    private  JTextField telField = new JTextField();
    private  JLabel oderIdLabel = new JLabel("旅行班次");
    private  JTextField oderIdField = new JTextField();
    private  JComboBox<String> oderIdComboBox = new JComboBox<String>();


    private  JButton addButton = new JButton("添加");
    private  JButton alterButton = new JButton("修改");



    public VisitorInfoView() throws SQLException {
        this.setTitle("游客信息管理");
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
        idLabel.setBounds(20,330,50,30);
        idField.setBounds(100,338,100,20);
        nameLabel.setBounds(230,330,50,30);
        nameField.setBounds(310,338,100,20);
        sexLabel.setBounds(430,330,50,30);
        maleButton.setBounds(507,330,40,30);
        femaleButton.setBounds(577,330,50,30);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);

        addressLabel.setBounds(20,390,50,30);
        addressField.setBounds(100,398,100,20);
        telLabel.setBounds(230,390,50,30);
        telField.setBounds(310,398,100,20);
        oderIdLabel.setBounds(430,390,50,30);//tourSightsLabel tourSightsField
        oderIdComboBox.setBounds(510,398,100,20);


        addButton.setBounds(660,332,70,30);
        alterButton.setBounds(660,393,70,30);
        idField.setEditable(false);
        oderIdComboBox.addItem("");

        this.add(idLabel);
        this.add(idField);

        this.add(nameLabel);
        this.add(nameField);
        this.add(sexLabel);
        this.add(maleButton);
        this.add(femaleButton);

        this.add(addressLabel);
        this.add(addressField);
        this.add(telLabel);
        this.add(telField);
        this.add(oderIdLabel);
        this.add(oderIdComboBox);
        this.add(addButton);
        this.add(alterButton);

        //对的图标
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

        //建立集合
        Vector<String> thVector = new Vector<String>();
        thVector.add("游客编号");
        thVector.add("游客姓名");
        thVector.add("游客性别");
        thVector.add("联系电话");
        thVector.add("住址");
        thVector.add("所在班次");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        //为下拉框赋值
        Connection connection3 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
            String sql3 = "select Order_no from tb_order";
            PreparedStatement preparedStatement =connection3.prepareStatement(sql3);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                oderIdComboBox.addItem(resultSet.getString(1));

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection3.close();
        }

        //JDBC为表格添加数据
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select Visitors_no,Visitors_name,Visitors_sex,Visitors_phone,Visitors_address,Oder_no from tb_Visitor  ";
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

        //为表格添加事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                idField.setText((String) table.getValueAt(row,0));
                nameField.setText((String) table.getValueAt(row,1));
                if ( table.getValueAt(row,2).equals("男"))
                    maleButton.setSelected(true);
                else
                    femaleButton.setSelected(true);
                telField.setText((String) table.getValueAt(row,3));
                addressField.setText((String) table.getValueAt(row,4));

                for (int i = 0 ; i<oderIdComboBox.getItemCount();i++)
                {
                    if(oderIdComboBox.getItemAt(i).equals(table.getValueAt(row,5)))
                        oderIdComboBox.setSelectedIndex(i);
                }

            }
        });



        maleButton.setSelected(true);
        //为修改添加事件


        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String Id = idField.getText();
                String name = nameField.getText();
                String sex = "male";
                if (maleButton.isSelected())
                    sex="男";
                else
                    sex="女";
                String address = addressField.getText();
                String tel = telField.getText();
                String oderId = (String) oderIdComboBox.getSelectedItem();


                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
                    String sql = "update tb_visitor set  Visitors_name = ? ,Visitors_sex = ?,Visitors_phone = ?,Visitors_address = ?,Oder_no=? where Visitors_no = ? ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1,name);
                    pss.setObject(2,sex);
                    pss.setObject(3,tel);
                    pss.setObject(4,address);
                    pss.setObject(5,oderId);
                    pss.setObject(6,Id);


                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        table.setValueAt(Id , table.getSelectedRow(),0);
                        table.setValueAt(name , table.getSelectedRow(),1);
                        table.setValueAt(sex , table.getSelectedRow(),2);
                        table.setValueAt(tel , table.getSelectedRow(),3);
                        table.setValueAt(address , table.getSelectedRow(),4);
                        table.setValueAt(oderId , table.getSelectedRow(),5);


//                        JOptionPane.showMessageDialog(null , "修改成功");
                        JOptionPane.showMessageDialog(null, "修改成功","消息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );

                        idField.setText("");
                        nameField.setText("");
                        maleButton.setSelected(false);
                        femaleButton.setSelected(false);
                        addressField.setText("");
                        telField.setText("");
                        oderIdComboBox.setSelectedIndex(0);

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

        //为添加按钮添加事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                String name = nameField.getText();
                String sex = "male";
                if (maleButton.isSelected())
                    sex="男";
                else
                    sex="女";
                String address = addressField.getText();
                String tel = telField.getText();
                String oderId = (String) oderIdComboBox.getSelectedItem();

                // jdbc
                Connection connection =null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tourmdb?useSSL=false&serverTimezone=UTC","root","root");
//                    String sql = "update tb_line set Line_start = ? , Line_end = ? ,Line_days = ?,Line_sight = ?,Line_designer = ? where Line_no = ? ";
                    String sql = "insert into tb_visitor(Visitors_name,Visitors_sex,Visitors_phone,Visitors_address,Oder_no) Values(?,?,?,?,?) ";
                    PreparedStatement pss = connection.prepareStatement(sql);

                    pss.setObject(1,name);
                    pss.setObject(2,sex);
                    pss.setObject(3,tel);
                    pss.setObject(4,address);
                    pss.setObject(5,oderId);




                    int a = pss.executeUpdate() ;
                    if (a==1)
                    {
                        JOptionPane.showMessageDialog(null, "添加成功", "信息", JOptionPane.INFORMATION_MESSAGE,backgroundImg  );
//                        JOptionPane.showMessageDialog(null , "添加成功");
                        dispose();
                        new VisitorInfoView();
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
        new VisitorInfoView();
    }
}
