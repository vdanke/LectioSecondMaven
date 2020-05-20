<html>
<body>
<jsp:useBean id="date" class="java.util.Date"/>
<h2>Hello World!</h2>
    ${sessionScope.userInSession.username}
    <a href="${pageContext.request.contextPath}/main">Go to main page</a>
<jsp:setProperty name="dateName" property="date" value="${date}"/>
<%--<jsp:include page="main.jsp"--%>

<%--<%--%>
<%--    HttpSession session1 = request.getSession();--%>
<%--    --%>
<%--    session1.setAttribute("");--%>
<%--%>--%>
</body>
</html>
