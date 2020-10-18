<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${map}" var="m">
    ${m.key.goodsid}${m.key.goodsname}<a href="javascript:void(0)" onclick="change('-')">-<a><input type="text" value="${m.value}" id="num"><a href="javascript:void(0)" onclick="change('+')">+</a><a href="goods.do?p=deletefromcar&goodsid=${m.key.goodsid}">删除</a><br>
    </c:forEach>

</body>
</html>

<script>

    function change(op){

        if(op=="-"){
            //减
            var num = document.getElementById("num").value;
            if(num==1){

                if(confirm("真的要删除吗?")){
                    //做删除

                }

            }


        }else{
            //加

        }




    }

</script>




