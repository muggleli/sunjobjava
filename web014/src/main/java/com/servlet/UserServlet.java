package com.servlet;

import com.dao.UserDAO;
import com.pojo.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String p =request.getParameter("p");
        if ("findall".equals(p))
        {
            doFenye(request,response);
        }

    }

    private void doFenye(HttpServletRequest request, HttpServletResponse response) {
        //如果用户没有指定
        int page = 1;
        int size = 4;

        String pageString = request.getParameter("page");
        if (pageString!=null&&pageString.trim().length()>0)
        {


        }

        String sizeString = request.getParameter("size");

    }


}
