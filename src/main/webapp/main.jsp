<%--
  Created by IntelliJ IDEA.
  User: vielen
  Date: 5/19/20
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main jsp</title>
</head>
<body>
    <h2>I'am main page</h2>
    ${users}
    <form action="${pageContext.request.contextPath}/main" method="post">
        <input type="text" name="username" placeholder="Insert your username">
        <input type="password" name="password" placeholder="Insert your password">
        <input type="submit" value="Sign-Up">
    </form>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="text" name="username" placeholder="Insert your username">
        <input type="password" name="password" placeholder="Insert your password">
        <input type="submit" value="Login">
    </form>
</body>
</html>
