<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section>
    <h1>session域测试结果</h1>
    <p>request: <%=request.getAttribute("id")%>
    <p>request: <%=request.getAttribute("name")%>
    <p>request: <%=request.getAttribute("gender")%>
    <p>request: <%=request.getAttribute("age")%>
    <p>request: <%=request.getAttribute("info")%>
    <p>session: <%=session.getAttribute("id")%>
    <p>session: <%=session.getAttribute("name")%>
    <p>session: <%=session.getAttribute("gender")%>
    <p>session: <%=session.getAttribute("age")%>
    <p>session: <%=session.getAttribute("info")%>
</section>
</body>
</html>
