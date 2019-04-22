<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Teacher ${teacher.id}</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
    </head>
    <body>
        <div class ="container">
            <h1 class="title">Teacher ${teacher.id} ${teacher.name}</h1>
            <h2 class="title">Schedule</h2>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
            <tr>
                <td>Lesson id</td>
                <td>Weekday</td>
                <td>Start time</td>
                <td>Group</td>
                <td>Subject</td>
                <td>Auditorium</td>
            </tr>
            <c:forEach items="${lessons}" var="lesson">
            <tr>
                <td><a href="lesson?id=${lesson.id}">${lesson.id} </td>
                <td>
                </td>
                <td>${lesson.startDateTime} </td>
                <td>${lesson.group.name} </td>
                <td>${lesson.subject.name} </td>
                <td>${lesson.auditorium.number} </td>
            </tr>
            </c:forEach>
            </table>
        </div>
    </body>
</html>