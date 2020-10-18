<%@ page import="com.pojo.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>

</head>
<body>
<%
    Student student = new Student();
    student.setStuid(1);
    student.setStuname("海涛");

    Student student2 = new Student();
    student2.setStuid(2);
    student2.setStuname("肖葵");

    Student student3 = new Student();
    student3.setStuid(3);
    student3.setStuname("李昊景");

    List list = new ArrayList<Student>();
    list.add(student);
    list.add(student2);
    list.add(student3);

    request.setAttribute("list" , list);
%>
<!--调用属性的get方法-->
${list[0].stuid}${list[0].getStuname()}
</body>
</html>
