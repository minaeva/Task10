<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Groups</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
    </head>
    <body>
        <div class ="container">
            <h1 class="title">Groups</h1>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
            <c:forEach items="${groups}" var="group">
                <tr>
                    <td><a href="search?q=group&no=${group.id}">${group.id} </td>
                    <td>${group.name} </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>