<%@page isELIgnored="false" %>
<html>
<body>

<!--数学运算 -->
${1+2}<br>
${1/2}<br>
${"11"+"22"}

<br>

<!--\禁用单个的EL-->
\${"a"+"b"}
<!-- 关系运算-->
${1>2}
${"11">"2"}
${"ad"<"ac"}<br>

${1 gt 2}
${1 ge 2}
${1 eq 2}
${1 lt 2}
${1 le 2}
${1 ne 2}

<br>
${1 gt 2}
${1 ge 2}
${1 eq 2}
${1 lt 2}
${1 le 2}
${1 ne 2}

<br>
${true && false}

${empty ""}<!-- 对象为空    对象不为空  但是内容为空 -->

${1>2 ? 1 :2}

</body>
</html>
