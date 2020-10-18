package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginView.do")
public class LoginViewServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<form method='post' action='user.do?p=login'>");
        pw.println("username:<input type='text' name='username'><br>");
        pw.println("password:<input type='password' name='password'><br>");
        pw.println("<input type='submit' value='login'>");
        pw.println("</form>");
        pw.println("</html>");
    }
}
