<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <fmt:bundle basename="locale"/>
    <fmt:setLocale value="ru_RU"/>

    <fmt:message bundle="locale" key="db.driver"/>
    
    <c:set var="tree" value="40" scope="session"/>
    <c:set var="newtree" value="50" scope="request"/>

    <c:forTokens items="First,second,third" delims="," var="number">
        <c:out value="${number}"/>
    </c:forTokens>

    <c:url value="${pageContext.request.contextPath}/main">
        <c:param name="customParam" value="Hi"/>
    </c:url>

    <c:out value="${sessionScope.tree}"/>
    <c:out value="${requestScope.newtree}"/>

    <c:choose>
        <c:when test="${users.size() == 5}">
            List is five
        </c:when>
        <c:otherwise>
            List is not five
        </c:otherwise>
    </c:choose>

    <c:if test="${users != null}">
        <c:forEach var="user" items="${users}">
            ${user.id}
            ${user.username}
            ${user.password}
        </c:forEach>
    </c:if>
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
