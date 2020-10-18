<html>
<body>
<form method="post" action="test.do">
 用户名：<input type="text" name="username" id="username" onclick="check()"><span id="flag"></span>
 密码：<input type="password" name="password" ><br>
 <input type="submit" value="登录">
</form>
</body>
</html>
<script>
 function check() {
 var username = document.getElementsById("username");

 var request = new XMLHttpRequest();

 request.open("get","test.do?p=check&username="+username);
 request.send(null);


 }
</script>
