<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section>
    <h1>request域测试结果</h1>
    <p>request: <%=request.getAttribute("key-request")%>
    <p>request: <%=request.getAttribute("key-mv")%>
    <p>request: <%=request.getAttribute("key-model")%>
    <p>request: <%=request.getAttribute("key-model-map")%>
    <p>request: <%=request.getAttribute("key-map")%>
</section>
</body>
</html>
