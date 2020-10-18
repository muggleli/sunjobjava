package test;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Test extends JFrame {

    private JLabel usernameLabel = new JLabel("用户名");
    private JTextField usernameField = new JTextField();

    private JLabel passwordLabel = new JLabel("密码");
    // 把密码框内容隐藏
    private JTextField passwordField = new JPasswordField();

    private JButton resetButton = new JButton("重置");
    private JButton loginBUtton = new JButton("登录");


    public void init() { //把控件添加到界面中

        //设置位置
        usernameLabel.setBounds(40 ,40 , 60 , 20);
        usernameField.setBounds(160 , 40 ,100 , 20);

        passwordLabel.setBounds(40 ,100 , 60 , 20);
        passwordField.setBounds(160 , 100 , 100 , 20);

        resetButton.setBounds(40 , 160 , 60 , 20);
        loginBUtton.setBounds(200 , 160 , 60  , 20);


        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(resetButton);
        this.add(loginBUtton);

        //给按钮添加监听事件    匿名内部类       写了一个类 实现了ActionListener接口  重写了接口中的方法   只是这个类没有名字
        //1.resetButton.addActionListener(new ResetButtonListener());
        //2.
        resetButton.addActionListener(new ActionListener() {
            @Override//这个方法指的是   点击了重置按钮执行的方法
            public void actionPerformed(ActionEvent e) {
                //这个方法做:
                usernameField.setText("");
                passwordField.setText("");

            }
        });


    }


    public static void main(String[] args) {//主方法是静态的
        Test Test = new Test();//得到界面对象  界面有一个属性Visible  默认值是false
        //给窗体设置大小
        Test.setSize(600, 500);//设置窗体的大小
        Test.setLocationRelativeTo(null);//设置界面居中
        Test.setResizable(false);//设置大小是否可以改变

        //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
        Test.setLayout(null);


        Test.init();//这个方法就是把控件加进去







        //添加控件
        // Test.add(usernameLabel);


        Test.setVisible(true);// 把界面设为可见  这句话写最后
    }

}
