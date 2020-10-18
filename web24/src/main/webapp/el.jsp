<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%
        int a = 10;
        request.setAttribute("b" , a);
        session.setAttribute("b" , 20);
        application.setAttribute("b",30);
    %>

    ${b}${c}
    ${sessionScope.b}
</head>
<body>

</body>
</html>
