<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${empty userinfo}" var="f">
    <a href="login.jsp">登录</a><a href="index.jsp">注册</a>
</c:if>

<c:if test="${not f}">欢迎:${userinfo.username}</c:if>
</body>
</html>
