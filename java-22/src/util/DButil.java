package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class DButil {

    //1.实例化连接池
    public static Vector<Connection> connectionPool = new Vector<Connection>();

    //2.初始化连接池    往连接池中添加连接对象
    static {// 类加载的时候完成

        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < 10; i++) {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "admin");
                connectionPool.add(connection);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //3.取连接
    public static Connection getConnection() {
        Connection connection = connectionPool.get(3);   // 不行
        connectionPool.remove(3);//为什么要学这句话？   从连接池移除   为什么移除    防止多个用户取到的是同一个连接
        return connection;

    }

    //4.还连接
    public static void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }

    //5.增删改
    public static int zsg(String sql, Object... p) {// Object...  一种数据类型   可以有多个Object类型的数据
        Connection connection = getConnection();
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            //怎么给问号赋值？
            for (int i = 0; i < p.length; i++) {//数组的长度就是问号的个数
                ps.setObject(i + 1, p[i]);
            }
            System.out.println(ps);


            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(connection);
        }

        return n;

    }
    //查询

    public static void main(String[] args) {
        //sql问号个数不确定  可能有   可能没有   可以是1个
        String sql = "update userinfo set password =? , role = ? where username = ? ";
        int n = DButil.zsg(sql, "gg", 1, "aa");//只是适用没有问号的情况
        System.out.println(n);

    }
}
