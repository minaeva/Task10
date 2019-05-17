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

            <c:if test="${not empty student}"> <!--UPDATE STUDENT -->
                <form id="saveStudent" method = "post" action = "student">
                    <div class="column is-one-quarter">
                      <label class="label">Name</label>
                      <div class="control">
                                <input class="input" type="text" id="name" name="name" value="${student.name}">
                      </div>
                    </div>

                    <div class="column is-one-quarter">
                        <label class="label">Group</label>
                        <div class="control">
                            <div class="select">
                                <select name="group">
                                    <c:forEach var="group" items="${groups}">
                                        <option value="${group.id}"
                                                <c:if test="${group.id eq studentGroup.id}">selected="selected"</c:if>
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
            </c:if>

            <c:if test="${empty student}"> <!--CREATE STUDENT -->
                <form id="saveStudent" method = "post" action = "students">
                    <div class="column is-one-quarter">
                      <label class="label">Name</label>
                      <div class="control">
                                <input class="input" type="text" id="name" name="name">
                      </div>
                    </div>

                    <div class="column is-one-quarter">
                        <label class="label">Group</label>
                        <div class="control">
                            <div class="select">
                                <select name="group">
                                    <c:forEach var="group" items="${groups}">
                                        <option value="${group.id}">${group.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="column is-one-quarter">
                        <div class="control">
                         <input type="hidden" name="doPost" value="0">
                           <button class ="button" type="submit">Save</button>
                        </div>
                    </div>
                </form>
            </c:if>

        </div>
    </body>
</html>
