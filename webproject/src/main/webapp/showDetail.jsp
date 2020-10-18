<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${goods.goodsid}
${goods.goodspic}
${goods.goodsname}
${goods.goodscount}
${goods.goodsprice}
<c:forEach items="${list}" var="pic">
    <img style="width: 200px; height: 200px;" src="image/${pic.pname}">
</c:forEach>
<br>
<input type="text" id="num"><a href="javascript:void(0)" onclick="addCar(${goods.goodsid})">加入购物车</a>
</body>
</html>
<script>
    function addCar(goodsid){
        var num = document.getElementById("num").value;
        location = "goods.do?p=addCar&goodsid="+goodsid+"&num="+num;
    }
</script>