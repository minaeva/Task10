<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Faculty ${faculty.id} ${faculty.name}</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
    </head>
    <body>
        <div class ="container">
            <h1 class="title">Faculty ${faculty.id} ${faculty.name}</h1>
            <h2 class="subtitle">Groups</h2>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
            <c:forEach items="${groups}" var="group">
                <tr>
                    <td><a href="group?id=${group.id}">${group.id} </td>
                    <td>${group.name} </td>
                </tr>
                </c:forEach>
            </table>

            <h2 class="subtitle">Teachers</h2>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
            <c:forEach items="${teachers}" var="teacher">
                <tr>
                    <td><a href="teacher?id=${teacher.id}">${teacher.id} </td>
                    <td>${teacher.name} </td>
                </tr>
                </c:forEach>
            </table>

            <h2 class="subtitle">Subjects</h2>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
            <c:forEach items="${subjects}" var="subject">
                <tr>
                    <td><a href="subject?id=${subject.id}">${subject.id} </td>
                    <td>${subject.name} </td>
                </tr>
                </c:forEach>
            </table>

            <h2 class="subtitle">Auditoria</h2>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
            <c:forEach items="${auditoria}" var="auditorium">
                <tr>
                    <td><a href="auditorium?id=${auditorium.id}">${auditorium.id} </td>
                    <td>${auditorium.number} </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>