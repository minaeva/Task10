<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Group ${group.id} ${group.name}</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
    </head>
    <body>
        <div class ="container">
            <h1 class="title">Group ${group.id} ${group.name}</h1>

 <c:if test="${not empty students}">
            <h2 class="subtitle">Students</h2>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
            <c:forEach items="${students}" var="student">
                <tr>
                    <td><a href="student?id=${student.id}">${student.id} </td>
                    <td>${student.name} </td>
                </tr>
                </c:forEach>
            </table>
 </c:if>

        </div>
    </body>
</html>
