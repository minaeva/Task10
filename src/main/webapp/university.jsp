<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>University</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
    </head>
    <body>
        <div class ="container">
            <h1 class="title">University</h1>
            <table class="table is-responsive is-bordered is-striped is-hoverable">
                <tr>
                    <td><a href="search?q=faculty&no=all">Faculties </td>
                    <td><a href="search?q=group&no=all">Groups </td>
                    <td><a href="search?q=student&no=all">Students </td>
                </tr>
            </table>
        </div>
    </body>
</html>