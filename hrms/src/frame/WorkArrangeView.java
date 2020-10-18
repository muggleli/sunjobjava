package frame;

import util.DateChooser;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class WorkArrangeView extends JDialog {
    private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JLabel arrangeIdLabel = new JLabel("安排编号");
    private JTextField arrangeIdField = new JTextField();
    private JLabel startDateLabel = new JLabel("起始日期");
    private JTextField startDateField = new JTextField();
    private JLabel arrangeContentLabel = new JLabel("工作内容");
    private JTextField arrangeContentField = new JTextField();
    private JLabel arrangeDepLabel = new JLabel("负责部门");
    private JTextField arrangeDepField = new JTextField();
    private JLabel endDateLabel = new JLabel("完成日期");
    private JTextField endDateField = new JTextField();
    private JLabel stateLabel = new JLabel("工作状态");
    private JTextField stateField = new JTextField();
    private JButton addButton = new JButton("添加");
    private JButton alterButton = new JButton("修改");

    private static void adjustTableColumnWidths(JTable table) {
        JTableHeader header = table.getTableHeader();     //表头
        int rowCount = table.getRowCount();     //表格的行数
        TableColumnModel cm = table.getColumnModel();     //表格的列模型
        for (int i = 0; i < cm.getColumnCount(); i++) {     //循环处理每一列
            TableColumn column = cm.getColumn(i);                     //第i个列对象
            int width =
                    (int) header.getDefaultRenderer().getTableCellRendererComponent(table,
                            column.getIdentifier(), false, false, -1,
                            i).getPreferredSize().getWidth();     //用表头的绘制器计算第i列表头的宽度
            for (int row = 0; row < rowCount; row++) {     //循环处理第i列的每一行，用单元格绘制器计算第i列第row行的单元格宽度
                int preferedWidth = (int) table.getCellRenderer(row,
                        i).getTableCellRendererComponent(table, table.getValueAt(row, i),
                        false, false, row, i).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);     //取最大的宽度
            }
            column.setPreferredWidth(width + table.getIntercellSpacing().width);     //设置第i列的首选宽度
        }

        table.doLayout();       //按照刚才设置的宽度重新布局各个列
    }
    public WorkArrangeView() throws SQLException {
        this.setTitle("工作安排管理");
        this.setSize(830, 500);//设置窗体的大小
        this.setLocationRelativeTo(null);//设置界面居中
        this.setResizable(false);//设置大小是否可以改变
        this.setLayout(null);
        this.setModal(true);//设置为模式对话框
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        arrangeIdLabel.setBounds(20, 330, 50, 30);
        arrangeIdField.setBounds(100, 338, 100, 20);
        startDateLabel.setBounds(230, 330, 50, 30);
        startDateField.setBounds(310, 338, 100, 20);
        arrangeContentLabel.setBounds(430, 330, 50, 30);
        arrangeContentField.setBounds(510, 338, 100, 20);
        arrangeDepLabel.setBounds(20, 390, 50, 30);
        arrangeDepField.setBounds(100, 398, 100, 20);
        endDateLabel.setBounds(230, 390, 50, 30);
        endDateField.setBounds(310, 398, 100, 20);
        stateLabel.setBounds(430, 390, 50, 30);
        stateField.setBounds(510, 398, 100, 20);

        addButton.setBounds(660, 332, 70, 30);
        alterButton.setBounds(660, 393, 70, 30);

        //对的图标
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\hutlhj\\Desktop\\javaswing项目\\img\\nice.jpg");
        backgroundImg.setImage(backgroundImg.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        //添加时间控件
        DateChooser startDateChooser = DateChooser.getInstance("yyyy-MM-dd");
        startDateChooser.register(startDateField);
        DateChooser endDateChooser = DateChooser.getInstance("yyyy-MM-dd");
        endDateChooser.register(endDateField);

        this.add(arrangeIdLabel);
        this.add(arrangeIdField);
        this.add(startDateLabel);
        this.add(startDateField);
        this.add(arrangeContentLabel);
        this.add(arrangeContentField);
        this.add(arrangeDepLabel);
        this.add(arrangeDepField);
        this.add(endDateLabel);
        this.add(endDateField);
        this.add(stateLabel);
        this.add(stateField);
        this.add(addButton);
        this.add(alterButton);
        Vector<String> thVector = new Vector<String>();
        thVector.add("安排编号");
        thVector.add("起始日期");
        thVector.add("工作内容");
        thVector.add("负责部门");
        thVector.add("完成日期");
        thVector.add("工作状态");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select Arrange_id,Arrange_date,Arrange_content,Arrange_dep,Arrange_enddate,Arrange_state from tb_arrange  ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
        DefaultTableModel defaultTableModel = new DefaultTableModel(dataVector, thVector);
        table.setModel(defaultTableModel);
        scrollPane.getViewport().add(table);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        scrollPane.setBounds(20, 20, 790, 280);
        add(scrollPane);
        this.adjustTableColumnWidths(table);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                arrangeIdField.setText((String) table.getValueAt(row, 0));
                startDateField.setText((String) table.getValueAt(row, 1));
                arrangeContentField.setText((String) table.getValueAt(row, 2));
                arrangeDepField.setText((String) table.getValueAt(row, 3));
                endDateField.setText((String) table.getValueAt(row, 4));
                stateField.setText((String) table.getValueAt(row, 5));
            }
        });

        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String arrangeId = arrangeIdField.getText();
                String startDate = startDateField.getText();
                String arrangeContent = arrangeContentField.getText();
                String arrangeDep = arrangeDepField.getText();
                String endDate = endDateField.getText();
                String state = stateField.getText();

                Connection connection = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC", "root", "root");
                    String sql = "update tb_arrange set Arrange_date = ? ,Arrange_content = ? ,Arrange_dep = ? ,Arrange_enddate = ? ,Arrange_state  = ? where Arrange_id = ? ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1, startDate);
                    pss.setObject(2, arrangeContent);
                    pss.setObject(3, arrangeDep);
                    pss.setObject(4, endDate);
                    pss.setObject(5, state);
                    pss.setObject(6, arrangeId);
                    int a = pss.executeUpdate();
                    if (a == 1) {
                        table.setValueAt(arrangeId, table.getSelectedRow(), 0);
                        table.setValueAt(startDate, table.getSelectedRow(), 1);
                        table.setValueAt(arrangeContent, table.getSelectedRow(), 2);
                        table.setValueAt(arrangeDep, table.getSelectedRow(), 3);
                        table.setValueAt(endDate, table.getSelectedRow(), 4);
                        table.setValueAt(state, table.getSelectedRow(), 5);
                        JOptionPane.showMessageDialog(null, "修改成功", "消息", JOptionPane.INFORMATION_MESSAGE, backgroundImg);
                        arrangeIdField.setText("");
                        startDateField.setText("");
                        arrangeContentField.setText("");
                        arrangeDepField.setText("");
                        endDateField.setText("");
                        stateField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "修改失败");
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
                String arrangeId = arrangeIdField.getText();
                String startDate = startDateField.getText();
                String arrangeContent = arrangeContentField.getText();
                String arrangeDep = arrangeDepField.getText();
                String endDate = endDateField.getText();
                String state = stateField.getText();
                // jdbc
                Connection connection = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmsdb?useSSL=false&serverTimezone=UTC", "root", "root");
                    String sql = "insert into tb_arrange(Arrange_id,Arrange_date,Arrange_content,Arrange_dep,Arrange_enddate,Arrange_state) Values(?,?,?,?,?,?) ";
                    PreparedStatement pss = connection.prepareStatement(sql);
                    pss.setObject(1, arrangeId);
                    pss.setObject(2, startDate);
                    pss.setObject(3, arrangeContent);
                    pss.setObject(4, arrangeDep);
                    pss.setObject(5, endDate);
                    pss.setObject(6, state);

                    int a = pss.executeUpdate();
                    if (a == 1) {
                        JOptionPane.showMessageDialog(null, "添加成功", "信息", JOptionPane.INFORMATION_MESSAGE, backgroundImg);
//                        JOptionPane.showMessageDialog(null , "添加成功");
                        dispose();
                        new WorkArrangeView();
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败");
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
        new WorkArrangeView();
    }
}
