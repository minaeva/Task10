<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Students</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
    </head>
    <body>
        <div class ="container">
            <h1 class="title">Students</h1>
            <table class="table is-responsive is-bordered is-striped is-hoverable is-narrow">
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td><a href="student?id=${student.id}">${student.id} </td>
                        <td>
                            ${student.name}
                        </td>
                        <td>
                            <div class="field is-grouped">
                              <div class="control">
                                <form method = "get" action = "editStudent">
                                    <input type="hidden" name="id" value="${student.id}">
                                    <input type="hidden" name="action" value ="update">
                                    <button type="submit" class="button is-small">Edit</button>
                                </form>
                              </div>
                              <div class="control">
                                <form method = "post" action = "students">
                                    <input type="hidden" name="id" value="${student.id}">
                                    <input type="hidden" name="action" value ="delete">
                                    <button type="submit" class="button is-small">Delete</button>
                                </form>
                              </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="control">
                <form method = "get" action = "editStudent">
                    <input type="hidden" name="action" value ="create"><br>
                    <button type="submit" class="button is-small">Create new</button>
                </form>
            </div>

        </div>
    </body>
</html>
