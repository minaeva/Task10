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
    </head>
    <body>
        <div class ="container">

            <form id="saveStudent" method = "post" action = "students">

                <div class="column is-one-quarter">
                  <label class="label">Name</label>
                  <div class="control">
                        <c:if test="${not empty student}">
                            <input type="hidden" name="action" value ="update">
                            <input class="input" type="text" id="name" name="name" value="${student.name}">
                        </c:if>
                        <c:if test="${empty student}">
                            <input type="hidden" name="action" value ="create">
                            <input class="input" type="text" id="name" name="name">
                        </c:if>
                  </div>
                </div>

                <div class="column is-one-quarter">
                    <label class="label">Group</label>
                    <div class="control">
                        <div class="select">
                            <select name="group">
                                <c:forEach var="group" items="${groups}">
                                    <option value="${group.id}"
                                        <c:if test="${not empty student}">
                                            <c:if test="${group.id eq studentGroup.id}">selected="selected"</c:if>
                                        </c:if>
                                        >
                                        ${group.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="column is-one-quarter">
                    <input type="hidden" name="id" value="${student.id}">

                    <div class="control">
                        <button class ="button" type="submit">Save</button>
                    </div>
                </div>
            </form>

        </div>
    </body>
</html>
