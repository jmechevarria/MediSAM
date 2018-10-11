<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <body>
        <c:out value="${message}" />
        <c:out value="This is JSTL"/>

        <p>URI: ${message} <br/>
            User :  ${user} <br/>
            roles:  ${roles} <br/><br/>
            <a href="/admin/">/admin/</a><br/>
            <a href="/users/">/users/</a><br/>
            <a href="/others/">/others/</a><br/>
            <a href="/quests/">/quests/</a><br/><br/>
        </p>
        <form action="/logout" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <input type="submit" value="Logout">
        </form>
    </body>
</html>