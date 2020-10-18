package com.servlet;

import com.dao.DepDAO;
import com.pojo.Dep;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dep.do")
public class DepServlet extends HttpServlet {
   private  DepDAO depDAO = new DepDAO();

   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=utf-8");
      String p = request.getParameter("p");
      if ("findall".equals(p)) {
         doFindAll(request, response);
      }
   }

   private void doFindAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
    List<Dep> list = depDAO.findall();
    StringBuffer sb = new StringBuffer("[");
    for(Dep dep : list )
    {
       sb.append("{'depid':'").append(dep.getDepid()).append("','depname':'").append(dep.getDepname()).append("'},");
    }
    sb.replace(sb.length()-1,sb.length(),"]");
      response.getWriter().println(sb.toString());
   }
}
