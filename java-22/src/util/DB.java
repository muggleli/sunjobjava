package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class DB {
    public  static  Vector<Connection> connectionPool  = new Vector<Connection>();
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i=0;i<10;i++)
            {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/s85?useSSL=false&serverTimezone=UTC", "root", "root");
                connectionPool.add(connection);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection connection = connectionPool.get(0);   // 不行
        connectionPool.remove(0);//为什么要学这句话？   从连接池移除   为什么移除    防止多个用户取到的是同一个连接
        return connection;
    }

    public  static  void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }
}
