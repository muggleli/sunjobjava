package com.servlet;

import javax.servlet.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

public class LoginAction implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web001?useSSL=false&serverTimezone=UTC","root","root");
            String sql = "Select username , password from userinfo where username = ? and password = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,username);
            ps.setObject(2,password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                System.out.println("登入成功");
            }else
            {
                System.out.println("登入失败");
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

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
