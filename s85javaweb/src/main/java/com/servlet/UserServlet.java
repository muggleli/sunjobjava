package com.servlet;

import com.dao.UserDAO;
import com.pojo.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p =request.getParameter("p");
        response.setContentType("text/html;charset=utf-8");
        if ("regist".equals(p))
        {
            doRegist(request , response);
        }
        if ("login".equals(p))
        {
            doLogin(request , response);
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserInfo userInfo = userDAO.login(username,password);

        if (userInfo==null){
            response.getWriter().println("<script>alert('用户名或密码错误！');location='login.jsp'</script> ");
            return ;
        }

        request.getSession().setAttribute("userinfo",userInfo);
        request.getRequestDispatcher("main.jsp").forward(request,response);
    }

    private void doRegist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        int n = userDAO.regist(username,password);

        if (n>0)
        {
            response.getWriter().println("<script>alert('注册成功！');location='login.jsp'</script> ");
        }
        else{
            response.getWriter().println("<script>alert('用户名已注册！');location='index.jsp'</script> ");
        }

    }
}
