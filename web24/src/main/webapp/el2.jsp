<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>

</head>
<body>
<%
    String[] strings = new String[]{"aa" , "bb" , "cc"};
    session.setAttribute("strings" , strings);
%>

${strings[0]}
</body>
</html>
