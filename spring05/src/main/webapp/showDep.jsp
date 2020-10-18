<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<select id="dep">

</select>
</body>

<script>

    onload = function(){

        var request = new XMLHttpRequest();

        request.open("get" , "dep.do?p=findajax");
        request.send(null);

        request.onreadystatechange = function(){

            if(request.readyState==4 && request.status==200){

                var ret = request.responseText;
                var json = eval(ret);
                for (var i = 0 ; i<json.length;i++){
                    var option = new Option(json[i].depname , json[i].depid);
                    document.getElementById("dep").append(option);
                }

            }


        }


    }



</script>

</html>
