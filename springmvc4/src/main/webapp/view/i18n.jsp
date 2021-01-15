<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><spring:message code="title"/></h1>
<form action="">
    <label><spring:message code="username"/></label>
    <label><input type="text"/></label><br/>
    <label><spring:message code="password"/></label>
    <label><input type="password"/></label><br/>
    <button><spring:message code="submit"/></button>
</form>
</body>
</html>
