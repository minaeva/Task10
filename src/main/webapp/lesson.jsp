<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Lesson ${lesson.id}</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
    </head>
    <body>
        <div class ="container">
            <h1 class="title">Lesson ${lesson.id}</h1>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
                <tr>
                    <td>Lesson id</td>
                    <td>Start time</td>
                    <td>Group</td>
                    <td>Teacher</td>
                    <td>Subject</td>
                    <td>Auditorium</td>
                </tr>
                <tr>
                    <td>${lesson.id} </td>
                    <td>${lesson.startDateTime}</td>
                    <td>${lesson.group.name} </td>
                    <td>${lesson.teacher.name} </td>
                    <td>${lesson.subject.name} </td>
                    <td>${lesson.auditorium.number} </td>
                </tr>
            </table>
        </div>
    </body>
</html>
