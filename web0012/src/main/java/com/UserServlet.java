package com;

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

    private UserDAO userDAO = new UserDAO();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String p = request.getParameter("p");

        if("findall".equals(p)){
            doFindAll(request , response);
        }




    }

    private void doFindAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查找所有用户
        List<UserInfo> list = userDAO.findall();
        request.setAttribute("list" , list);//存
        //
        request.getRequestDispatcher("show.jsp").forward(request , response);
    }
}
