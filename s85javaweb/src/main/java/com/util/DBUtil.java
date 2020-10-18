package com.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DBUtil {
    //1.实例化连接池
    public static Vector<Connection> connectionPool = new Vector<Connection>();

    //2.初始化连接池
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i =0 ; i<10;i++){

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web001?useSSL=false&serverTimezone=UTC","root","root");
                connectionPool.add(connection);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //取连接
    public static Connection getConnection() {
        Connection connection = connectionPool.get(0);
        connectionPool.remove(0);
        return  connection;
    }

    public  static  void releaseConnection (Connection connection){
        connectionPool.add(connection);
    }

    public  static  int zsg(String sql,Object... p)
    {
        Connection connection =getConnection();
        int n =0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            if (p != null) {
                for (int i = 0; i < p.length; i++) {
                    ps.setObject(i + 1, p[i]);
                }
            }
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  n;
    }

    //查询Class c 对应的类
    public static List query(String sql, Class c, Object... p)
    {
        List list = new ArrayList();
        Connection connection = getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            if (p != null) {
                for (int i = 0; i < p.length; i++) {
                    ps.setObject(i + 1, p[i]);
                }
            }

            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            //sql语句到底查了哪些字段
            int count = rsmd.getColumnCount();//查找了几个字段
            while (rs.next()) {//循环一次  表示有一条记录   有一条记录就要有一个对象
                Object object = c.newInstance();//   所有属性都是默认值
                // 要把数据库查出来的数据   赋值给对象的属性
                for (int i = 1; i <= count; i++) {// 查了几个字段就要给几个属性赋值
                    //select username , password from userinfo
                    String fieldname = rsmd.getColumnLabel(i);//得到查询的列名
                    //  得到的是字段名   字段名和属性名要一样
                    Field field = c.getDeclaredField(fieldname);
                    field.setAccessible(true);
                    //赋值
                    field.set(object , rs.getObject(i));//把数据库查出来的值赋值给对象的属性
                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseConnection(connection);
        }

        return list ;

    }
    //聚合查询
    public static double uniqueQuery(String sql , Object...p){

        Connection connection = getConnection();
        double d = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            if(p!=null){
                for (int i = 0; i < p.length; i++) {
                    ps.setObject(i+1 , p[i]);
                }
            }

            ResultSet rs = ps.executeQuery();

            rs.next();

            d = rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(connection);
        }
        return d;

    }
}
