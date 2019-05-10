<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage = "true" %>
<html>
    <head>
        <title>Error 404</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
    </head>
    <body>
        <div class ="container">
            <h1 class="title is-1">Error 400</h1>
            <h2 class="subtitle">Bad Request</h2>

            <p class="title is-5 is-spaced">You can:</p>
            <button class="button" name="Back" onclick="history.back()">Go Back</button>
            <button class="button" name="Homepage" onclick="window.location.href='/university/'">Go to Homepage</button>
         </div>
    </body>
</html>
