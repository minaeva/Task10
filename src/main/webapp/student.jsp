<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="en" />

<html>
    <head>
        <title>Student ${student.id}</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
        $(function() {
            $( "#dateFrom" ).datepicker({
              changeMonth: true,
              changeYear: true,
            });
            $( "#dateTo" ).datepicker({
              changeMonth: true,
              changeYear: true,
            });
        });
        </script>
    </head>
    <body>
        <div class ="container">
            <h1 class="title">Student ${student.id} ${student.name}</h1>

        <form method = "get" action = "student">
            <p class="subtitle is-5">
            	<c:if test="${not empty from}">
                    From: <input type="text" id="dateFrom" name="from" value="<%=request.getAttribute("from")%>">
            	</c:if>
            	<c:if test="${empty from}">
                    From: <input type="text" id="dateFrom" name="from">
            	</c:if>

               	<c:if test="${not empty to}">
                    To: <input type="text" id="dateTo" name="to" value="<%=request.getAttribute("to")%>">
            	</c:if>
               	<c:if test="${empty to}">
                    To: <input type="text" id="dateTo" name="to">
            	</c:if>
                <input type="hidden" name="id" value="${student.id}"><br>
            </p>
            <button type="submit">Apply</button>
        </form>

<c:if test="${not empty lessons}">
            <h2 class="title">Schedule</h2>
            <p class="subtitle is-3">Today's date: <%= (new java.util.Date()).toLocaleString()%></p>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
            <tr>
                <td>Lesson id</td>
                <td>Start time</td>
                <td>Teacher</td>
                <td>Subject</td>
                <td>Auditorium</td>
            </tr>
            <c:forEach items="${lessons}" var="lesson">
            <tr>
                <td><a href="lesson?id=${lesson.id}">${lesson.id} </td>
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
