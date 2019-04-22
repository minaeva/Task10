<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="en" />

<html>
    <head>
        <title>Student ${student.id}</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
    </head>
    <body>
        <div class ="container">
            <h1 class="title">Student ${student.id} ${student.name}</h1>

<c:if test="${not empty lessons}">
            <h2 class="title">Schedule</h2>
            <p class="subtitle is-3">Today's date: <%= (new java.util.Date()).toLocaleString()%></p>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
            <tr>
                <td>Lesson id</td>
                <td>Weekday</td>
                <td>Start time</td>
                <td>Teacher</td>
                <td>Subject</td>
                <td>Auditorium</td>
            </tr>
            <c:forEach items="${lessons}" var="lesson">
            <tr>
                <td><a href="lesson?id=${lesson.id}">${lesson.id} </td>
                <td>
                </td>
                <td>${lesson.startDateTime} </td>
                <td>${lesson.teacher.name} </td>
                <td>${lesson.subject.name} </td>
                <td>${lesson.auditorium.number} </td>
            </tr>
            </c:forEach>
            </table>
</c:if>

        </div>
    </body>
</html>