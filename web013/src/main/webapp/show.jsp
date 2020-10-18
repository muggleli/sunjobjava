<%@ page import="java.util.List" %>
<%@ page import="com.pojo.UserInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%
        List<UserInfo> list = (List<UserInfo>)request.getAttribute("list");

    %>

    <%for(UserInfo userinfo : list){%>
    <c:set var="x" value="userinfo.getUsername()" ></c:set>

    <%=userinfo.getUsername()%>${userinfo.getUsername()}
    <%=userinfo.getPassword()%><a href="user.do?p=deletebyusername&username=<%=userinfo.getUsername()%>">删除</a><br>
    <%}%>

    <c:forEach items="${list}" var="str" varStatus="s">
        ${s.index}/${s.count}/${str} <br/>
    </c:forEach>


</head>
<body>

</body>
</html>
