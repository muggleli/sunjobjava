<%@ page import="com.pojo.UserInfo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hutlhj
  Date: 2020/7/15
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        List<UserInfo> list = (List<UserInfo>)request.getAttribute("list");
    %>

    <%for(UserInfo userInfo : list){%>
    <%= userInfo.getUsername() %>
    <%= userInfo.getPassword()%><br>
    <%}%>
</head>
<body>

</body>
</html>
