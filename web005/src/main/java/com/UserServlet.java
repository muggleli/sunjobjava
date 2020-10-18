package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String p = request.getParameter("p");

         if("login".equals(p))
         {
             doLogin(request , response);
         }
         if ("regist".equals(p))
         {
             doRegist(request , response);
         }
    }

    private void doRegist(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("做注册");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("做登录");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
    }
}
