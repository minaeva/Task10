<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Teachers</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
    </head>
    <body>
        <div class ="container">
            <h1 class="title">Teachers</h1>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
            <c:forEach items="${teachers}" var="teacher">
                <tr>
                    <td><a href="teacher?id=${teacher.id}">${teacher.id} </td>
                    <td>${teacher.name} </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>